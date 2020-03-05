package com.elx.spider.controller;

import com.elx.spider.service.QcJobService;
import com.elx.spider.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/qcjob/all.do")
    public R all(){
        return service.all();
    }
}
