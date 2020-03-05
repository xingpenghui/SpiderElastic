package com.elx.spider.service;

import com.elx.spider.entity.QcJob;
import com.elx.spider.vo.R;

import java.util.List;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 16:03
 */
public interface EsQcJobService {
    R saveBatch(List<QcJob> list);
    // 薪资范围 搜索
    R queryMoneyRange(int min,int max);

    //高亮显示查询
    R queryHight(String job);

}
