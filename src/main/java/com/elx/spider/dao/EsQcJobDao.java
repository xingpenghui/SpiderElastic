package com.elx.spider.dao;

import com.elx.spider.model.EsQcJob;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 15:38
 */
public interface EsQcJobDao extends ElasticsearchRepository<EsQcJob,Integer> {
}
