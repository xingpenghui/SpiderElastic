package com.elx.spider.controller;

import com.elx.spider.entity.QcJob;
import com.elx.spider.service.EsQcJobService;
import com.elx.spider.service.QcJobService;
import com.elx.spider.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:28
 */
@RestController
public class QcJobController {
    @Autowired
    private QcJobService service;
    @Autowired
    private EsQcJobService jobService;
    @GetMapping("/api/qcjob/all.do")
    public R all(){
        return service.all();
    }

    //数据同步
    @GetMapping("/api/qcjob/syncdata.do")
    public R data(){
        return jobService.saveBatch((List<QcJob>)service.all().getData());
    }
}
