package com.cheng.cheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/6/7.
 */
public class OnlineTest {

    public static volatile boolean connected = true;
    public static void main(String[] args) throws Exception {
        String result = sendGet("http://www.baidu.com");
        System.out.println(result);

    }
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url;
            URL realUrl = new URL(urlName);
            // �򿪺�URL֮�������
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // ����ʵ�ʵ�����
            conn.connect();

            int statusCode = conn.getResponseCode();

//            System.out.println(statusCode);
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = conn.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
                if (key!=null && key.equals("Connection")){
                    if (map.get(key).get(0).equals("close")) {
                        System.out.println(map.get(key).get(0));
//                        System.out.println("������");
                        connected = false;
                        break;
                    }else {
                        connected = true;
                        System.out.println(map.get(key).get(0));
//                        System.out.println(map.get(key));
//                        System.out.println("���ߡ���");
                        break;
                    }
                }
//
            }
//             ����BufferedReader����������ȡURL����Ӧ
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += "/n" + line;
//            }
//            System.out.println(result);
        } catch (Exception e) {
//            System.out.println("����GET��������쳣��" + e);
        }
        // ʹ��finally�����ر�������
        return result;
    }



}
