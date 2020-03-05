package com.elx.spider.task;

import com.elx.spider.spider.JobPage;
import com.elx.spider.spider.QcJobPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 14:55
 */
@Component
public class QcJobTask {
    private String uhz="https://search.51job.com/list/080200,000000,0000,00,9,99,Java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private String unj="https://search.51job.com/list/070200,000000,0000,00,9,99,Java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private String ush="https://search.51job.com/list/020000,000000,0000,00,9,99,Java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Autowired
    private JobPage page;
    @Autowired
    private QcJobPipeline pipeline;
    //每日2点
    @Scheduled(cron = "0 0 2 * * ?")
    public void startBatch(){
        new Spider(page).addPipeline(pipeline).addUrl(uhz,unj,ush).thread(5).start();
    }
}
