package com.elx.spider.spider;

import com.elx.spider.entity.QcJob;
import com.elx.spider.service.QcJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:09
 */
@Component
public class QcJobPipeline implements Pipeline {
    @Autowired
    private QcJobService service;
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取接收的对象
        List<QcJob> list=resultItems.get("jobs");
        //批量新增到数据库
        service.saveBatch(list);
    }
}
