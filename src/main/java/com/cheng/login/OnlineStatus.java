package com.cheng.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by login on 2017/6/7.
 */
public class OnlineStatus {

    public static volatile boolean connected = true;
    public static URL realUrl;

    static {
        try {
            realUrl = new URL("http://www.baidu.com");
        } catch (Exception e) {
        }
    }

    public static Boolean sendGet() {
        HttpURLConnection conn = null;
        try {
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");

            // 建立实际的连接
            conn.connect();

//            System.out.println(statusCode);
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                if(key!=null && key.equals("Content-Length")){
                    String s = map.get(key).toString();
                    System.out.println(s);
                    int length = Integer.valueOf(s.substring(1,s.length()-1));
                    if ( length<5000){
                        return false;
                    }
                }
            }
            return true;

        } catch (Exception e) {
        }finally {
            try {
            } catch (Exception e) {
            }
        }
        return false;
    }



}
