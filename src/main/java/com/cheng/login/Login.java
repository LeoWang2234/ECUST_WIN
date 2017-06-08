package com.cheng.login;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by login on 2017/6/6.
 *
 * 有线登录 和 无线登录的 父类
 *
 *
 */
public class Login {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            // 关闭重定向
            connection.setInstanceFollowRedirects(false);

            // 设置200毫秒超时时间
            connection.setConnectTimeout(1000);

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                if (key != null && key.equals("Location")) {
                    result = String.valueOf(map.get(key));
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {

            } finally {
            }
        }
        return result;
    }
}
