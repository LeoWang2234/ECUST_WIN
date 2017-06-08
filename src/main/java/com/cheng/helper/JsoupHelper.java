package com.cheng.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by login on 2017/6/6.
 */
public class JsoupHelper {


    public static Map<String,String> prase(String url) {

        Map<String, String> attrs = new HashMap<String, String>();

        try {
            //解析Url获取Document对象
            Document document = Jsoup.connect(url).get();
            //获取网页源码文本内容
//            System.out.println(document.toString());

//            Elements elements = document.select("input");

            Elements elements = document.getElementsByAttributeValue("type", "hidden");

            for(Element inputTag : elements)
            {
               attrs.put(inputTag.attr("name"),inputTag.attr("value"));
            }

            System.out.println(attrs.toString());



        } catch (Exception e) {
//            System.out.println("解析出错！");
//            e.printStackTrace();
        }

        return attrs;
    }


}
