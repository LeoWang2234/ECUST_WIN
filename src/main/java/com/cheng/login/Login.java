package com.cheng.login;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by login on 2017/6/6.
 *
 * ���ߵ�¼ �� ���ߵ�¼�� ����
 *
 *
 */
public class Login {

    /**
     * ��ָ��URL����GET����������
     *
     * @return URL ������Զ����Դ����Ӧ���
     */
    public String sendGet(String url) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            // �ر��ض���
            connection.setInstanceFollowRedirects(false);

            // ����200���볬ʱʱ��
            connection.setConnectTimeout(1000);

            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                if (key != null && key.equals("Location")) {
                    result = String.valueOf(map.get(key));
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        // ʹ��finally�����ر�������
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
