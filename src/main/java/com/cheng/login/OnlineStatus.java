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
            // �򿪺�URL֮�������
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");

            // ����ʵ�ʵ�����
            conn.connect();

//            System.out.println(statusCode);
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = conn.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
//             ����BufferedReader����������ȡURL����Ӧ

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
        // ʹ��finally�����ر�������
        return false;
    }



}
