package com.elx.spider.service;

import com.elx.spider.entity.QcJob;
import com.elx.spider.vo.R;

import java.util.List;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:05
 */
public interface QcJobService {
    R all();
    R save(QcJob job);
    R saveBatch(List<QcJob> list);
}