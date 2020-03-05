package com.elx.spider.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import javax.persistence.Id;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 15:36
 */
@Document(indexName = "esqcjob",type = "qcjob")
@Data
public class EsQcJob {
    @Id
    private Integer id;
    //修饰字段，并且指定分词
    @Field(analyzer = "ik",searchAnalyzer = "ik",type = FieldType.Text)
    private String jname;
    private String cname;
    private int minmoney;
    private int maxmoney;
}