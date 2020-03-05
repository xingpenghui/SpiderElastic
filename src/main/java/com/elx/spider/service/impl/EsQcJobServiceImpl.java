package com.elx.spider.service.impl;

import com.alibaba.fastjson.JSON;
import com.elx.spider.dao.EsQcJobDao;
import com.elx.spider.entity.QcJob;
import com.elx.spider.model.EsQcJob;
import com.elx.spider.service.EsQcJobService;
import com.elx.spider.util.StrUtil;
import com.elx.spider.vo.R;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 16:04
 */
@Service
public class EsQcJobServiceImpl implements EsQcJobService {
    @Autowired
    private EsQcJobDao jobDao;
    @Autowired
    private ElasticsearchTemplate template;
    @Override
    public R saveBatch(List<QcJob> jobList) {
        List<EsQcJob> jobs=new ArrayList<>();
        for(QcJob j:jobList){
            EsQcJob job=new EsQcJob();
            job.setCname(j.getComname());
            job.setJname(j.getJobname());
            List<Integer> list= StrUtil.parseMoney(j.getSalaryrang());
            job.setMinmoney(list.get(0));
            job.setMaxmoney(list.get(1));
            job.setId(j.getId());
            jobs.add(job);
        }
        jobDao.saveAll(jobs);
        return R.ok();
    }

    @Override
    public R queryMoneyRange(int min, int max) {
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.rangeQuery("minmoney").gte(min)).
                should(QueryBuilders.rangeQuery("maxmoney").lte(max));
        return R.ok(jobDao.search(boolQueryBuilder));
    }

    //重构 Spring Data ES 的查询 获取高亮标记
    @Override
    public R queryHight(String job) {
        NativeSearchQuery query=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("jname",job))
                .withHighlightFields(new HighlightBuilder.Field("jname")).build();
        Page<EsQcJob> page=template.queryForPage(query, EsQcJob.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //自己解析结果
                List<EsQcJob> list=new ArrayList<>();
                SearchHits hits=searchResponse.getHits();
                if(hits!=null && hits.totalHits>0){
                    //遍历每一行数据
                    for(SearchHit sh:hits) {
                        //获取高亮信息
                        String hs=sh.getHighlightFields().get("jname").fragments()[0].toString();
                        System.out.println(hs);
                        //为属性赋值
                        EsQcJob esJobb = JSON.parseObject(sh.getSourceAsString(),EsQcJob.class);
                        esJobb.setJname(hs);
                        list.add(esJobb);
                    }
                    return (AggregatedPage<T>) new PageImpl<EsQcJob>(list);
                }
                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        return R.ok(page.getContent());
    }
}
