package com.elx.spider.config;

import com.elx.spider.spider.JobPage;
import com.elx.spider.spider.QcJobPipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:26
 */
//@Configuration
public class SpiderConfig {
    private String url="https://search.51job.com/list/170200,000000,0000,00,9,99,Java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
//    @Bean
//    public Spider createBean(JobPage page, QcJobPipeline pipeline){
//        System.out.println("********");
//        Spider spider=new Spider(page);
//        spider.addPipeline(pipeline).addUrl(url).thread(3).start();
//        return spider;
//    }
}