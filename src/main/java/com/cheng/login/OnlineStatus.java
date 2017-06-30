package com.cheng.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

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
        String result = "";
        BufferedReader in = null;
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
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
                System.out.println(key + "--->" + map.get(key));
            }
//             定义BufferedReader输入流来读取URL的响应

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }

            if (result.length() < 5000) {
                return false;
            }else {
                return true;
            }

        } catch (Exception e) {
        }finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
        // 使用finally块来关闭输入流
        return false;
    }



}
