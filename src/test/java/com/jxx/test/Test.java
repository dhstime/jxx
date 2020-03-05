package com.jxx.test;


import com.jxx.Jsoup.Pojo.Article;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String [] args) throws NoSuchFieldException, IllegalAccessException {
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
//        LocalDate localDate1 = localDate.minusDays(30);
//        System.out.println(localDate1);
        Article article = new Article();
        article.setUrl("121124");
        Class<? extends Article> aClass = article.getClass();
        Field url = aClass.getDeclaredField("url");
        url.setAccessible(true);
        String o = (String) url.get(article);
//        System.out.println(o);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            Date date = sdf.parse("2018.03.12");
            System.out.println(date);
        }catch (Exception e){

        }

    }

}
