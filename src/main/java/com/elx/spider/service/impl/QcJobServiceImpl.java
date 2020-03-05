package com.elx.spider.service.impl;


import com.elx.spider.dao.QcJobDao;
import com.elx.spider.entity.QcJob;
import com.elx.spider.service.QcJobService;
import com.elx.spider.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:05
 */
@Service
public class QcJobServiceImpl implements QcJobService {
    @Autowired
    private QcJobDao dao;
    @Override
    public R all() {
        return R.ok(dao.findAll());
    }

    @Override
    public R save(QcJob job) {
        job.setCtime(new Date());
        if(dao.save(job)!=null){
            return R.ok();
        }else {
            return R.fail();
        }
    }

    @Override
    public R saveBatch(List<QcJob> list) {
        dao.saveAll(list);
        return R.ok();
    }
}