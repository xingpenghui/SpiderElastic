package com.elx.spider.spider;

import com.elx.spider.entity.QcJob;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 10:08
 */
@Component
public class JobPage implements PageProcessor {

    private Site site=Site.me().setSleepTime(200).setTimeOut(5000).setRetryTimes(2);
    //页面内容处理
    @Override
    public void process(Page page) {
        List<Selectable> list=page.getHtml().css("div#resultList div.el").nodes();
        if(list!=null && list.size()>0){
            List<QcJob> jobs=new ArrayList<>();
            list.remove(0);
            for(Selectable s:list){
                s.links().get();
                jobs.add(new QcJob(s.css("p.t1 span a","title").get(),
                        Integer.parseInt(s.css("p.t1 input.checkbox","value").get()),
                        s.css("span.t2 a","text").get(),
                        s.css("span.t3","text").get(),
                        s.css("span.t4","text").get(),
                        s.css("span.t5","text").get(),
                        s.css("p.t1 span a","href").get()));
            }
            //将结果 发送到 对应的结果处理器
            page.putField("jobs",jobs);
        }
        //校验当前是否为第一页
        if(page.getUrl().get().contains("1.html?")){
            //如果是第一页，那么需要 把剩余页面的数据，添加到爬取计划
           String tc=page.getHtml().css("input#hidTotalPage","value").get();
           if(tc!=null && tc.length()>0){
               int c=Integer.parseInt(tc);
               List<String> targetUrls=new ArrayList<>();
               for(int i=2;i<=c;i++){
                  targetUrls.add("https://search.51job.com/list/170200,000000,0000,00,9,99,Java,2,"+i+".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");
               }
               //添加继续爬取的页面
               page.addTargetRequests(targetUrls);
           }
        }
    }
    //站点信息
    @Override
    public Site getSite() {
        return site;
    }
}