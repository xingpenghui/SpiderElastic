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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
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
                must(QueryBuilders.rangeQuery("maxmoney").lte(max));
        return R.ok(jobDao.search(boolQueryBuilder));
    }

    //重构 Spring Data ES 的查询 获取高亮标记
    @Override
    public R queryHight(String job,int p,int size) {
        //设置页码
        Pageable pageable= PageRequest.of(p-1,size);
        //原生查询
        NativeSearchQuery query=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("jname",job))
                .withHighlightFields(new HighlightBuilder.Field("jname")
                        .preTags("<span style='color:red;font-size:30px'>").//设置高亮的标记 起始标签 默认<em>
                                postTags("</span>")// 设置高亮标记 结束标签
                       ).build();
        //设置分页
        query.setPageable(pageable);
        //自定义查询处理
        Page<EsQcJob> page=template.queryForPage(query, EsQcJob.class,
                new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //自己解析结果
                //记录最终的查询结果
                List<EsQcJob> list=new ArrayList<>();
                //获取查询结果
                SearchHits hits=searchResponse.getHits();
                //校验是否查询到内容
                if(hits!=null && hits.totalHits>0){
                    //遍历每一行数据
                    for(SearchHit sh:hits) {
                        //获取高亮信息
                        System.out.println("高亮标记："+sh.getHighlightFields());
                        //获取具体字段的高亮标记
                        String hs=sh.getHighlightFields().get("jname").fragments()[0].toString();
                        //解析结果
                        EsQcJob esJobb=JSON.parseObject(sh.getSourceAsString(),EsQcJob.class);
                        //重置属性
                        esJobb.setJname(hs);
                        list.add(esJobb);
                    }
                }
                return new AggregatedPageImpl(list);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        return R.ok(page.getContent());
    }
}
