package com.elx.spider.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 16:06
 */
public class StrTest {
    @Test
    public void t1(){
        String str1="1-1.5万/月";
        String str2="6-8千/月";
        int min=10000;
        int max=15000;
        if(str2.indexOf('-')>-1){
            double m1=Double.parseDouble(str2.substring(0,str2.indexOf('-')));
            double m2=Double.parseDouble(str2.substring(str2.indexOf('-')+1,str2.indexOf('/')-1));
            System.out.println(m1+"---->"+m2);
        }
        String[] arr=str2.split("\\d");
        System.out.println(Arrays.toString(arr));
        //正则
        //正则表达式对象
        Pattern pattern=Pattern.compile("(\\d+)-(\\d+|[0-9]\\.[1-9]+)([\\u4e00-\\u9fa5]{1})*");
//        //匹配对象
//        Matcher matcher=pattern.matcher(str2);
//        int i=matcher.groupCount();
//        System.out.println(i);
//        if(matcher.find()){
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//        }
    }
}
