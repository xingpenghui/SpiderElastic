package com.elx.spider.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpiderElastic
 * @description:
 * @author: Feri
 * @create: 2020-03-05 16:27
 */
public class StrUtil {
    /**
     * 返回值 第一个元素 最小值
     * 第二个元素 最大值*/
    public static List<Integer> parseMoney(String m){
        List<Integer> list=new ArrayList<>();
        String m1=m.substring(0,m.indexOf('-'));
        String m2=m.substring(m.indexOf('-')+1,m.indexOf('/')-1);
        String m3=m.substring(m.indexOf('/')-1,m.indexOf('/'));
        int c=1;
        switch (m3){
            case "千":c=1000;break;
            case "万":c=10000;break;
        }
        list.add((int)(Double.parseDouble(m1)*c));
        list.add((int)(Double.parseDouble(m2)*c));
        return list;
    }
    String regex="(?:[1-9]\\d*|0)(?:\\.\\d+)?";
}
