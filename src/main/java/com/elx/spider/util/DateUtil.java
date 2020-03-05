package com.elx.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:17
 */
public class DateUtil {
    public static Date parseDate(String md){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        if(md!=null && md.matches("[0-9]{2}[-][0-9]{2}")){
        if(md!=null && md.length()>0){
            String m=new SimpleDateFormat("yyyy").format(new Date())+"-"+md;
            try {
                return sdf.parse(m);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }else {
            return new Date();
        }
    }
}
