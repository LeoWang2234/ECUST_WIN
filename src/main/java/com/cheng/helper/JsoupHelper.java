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
            //����Url��ȡDocument����
            Document document = Jsoup.connect(url).get();
            //��ȡ��ҳԴ���ı�����
//            System.out.println(document.toString());

//            Elements elements = document.select("input");

            Elements elements = document.getElementsByAttributeValue("type", "hidden");

            for(Element inputTag : elements)
            {
               attrs.put(inputTag.attr("name"),inputTag.attr("value"));
            }

            System.out.println(attrs.toString());



        } catch (Exception e) {
//            System.out.println("��������");
//            e.printStackTrace();
        }

        return attrs;
    }


}
