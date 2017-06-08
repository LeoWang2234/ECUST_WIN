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
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//                if (key!=null && key.equals("Connection")){
//                    if (map.get(key).get(0).equals("close")) {
//                        System.out.println(map.get(key).get(0));
//                        System.out.println("������");
//                        connected = false;
//                        break;
//                    }else {
//                        connected = true;
//                        System.out.println(map.get(key).get(0));
//                        System.out.println(map.get(key));
//                        System.out.println("���ߡ���");
//                        break;
//                    }
//                }

//            }
//             ����BufferedReader����������ȡURL����Ӧ
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
//            System.out.println("����GET��������쳣��" + e);
        }finally {
            try {
                in.close();
                // ֮ǰ��� catch �� IOException �����Ƿ��ֶ�ʱ����ҵ��������������׳��� NullPointerException
                // �����޸�Ϊ Exception
            } catch (Exception e) {
            }
        }
        // ʹ��finally�����ر�������
        return false;
    }



}