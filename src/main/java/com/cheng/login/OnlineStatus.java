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
    public static void main(String[] args) throws Exception {
        Boolean result = sendGet("http://www.baidu.com");
//        System.out.println(result);

    }
    public static Boolean sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();

            int statusCode = conn.getResponseCode();

//            System.out.println(statusCode);
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//                if (key!=null && key.equals("Connection")){
//                    if (map.get(key).get(0).equals("close")) {
//                        System.out.println(map.get(key).get(0));
//                        System.out.println("掉线啦");
//                        connected = false;
//                        break;
//                    }else {
//                        connected = true;
//                        System.out.println(map.get(key).get(0));
//                        System.out.println(map.get(key));
//                        System.out.println("在线。。");
//                        break;
//                    }
//                }

//            }
//             定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
            System.out.println(result.length());

            if (result.length() < 5000) {
                return false;
            }else {
                return true;
            }

        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
        }finally {
            try {
                in.close();
                // 之前这个 catch 的 IOException ，但是发现定时器会挂掉，后来发现是抛出的 NullPointerException
                // 所以修改为 Exception
            } catch (Exception e) {
            }
        }
        // 使用finally块来关闭输入流
        return false;
    }



}
