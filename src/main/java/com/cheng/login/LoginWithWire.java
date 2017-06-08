package com.cheng.login;

import com.cheng.helper.ProperityHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by login on 2017/6/6.
 */
public class LoginWithWire extends Login {
    public String sendPostWire(Map attrs) throws Exception {

        String username;
        String password;

        String url = "http://172.20.3.90:804/include/auth_action.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //�������ͷ
        con.setRequestMethod("POST");
        con.setConnectTimeout(500);

//        password = Utils.decode(password.getBytes());

//        username = "Y30150635";
//        password = "d2FuZ2NoZW5n";

        username = ProperityHelper.readValue("username");
        password = ProperityHelper.readValue("password");

        String urlParameters = "action=login&username=" + username + "@free&password={B}" + password + "&ac_id="+attrs.get("ac_id")+"&user_ip="+attrs.get("user_ip")+"&nas_ip=''&user_mac=''&ajax=1&ip="+attrs.get("ip");

        //����Post����
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        try {
            wr.close();
        } catch (IOException e) {
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (Exception e) {
        }

        return response.toString();
    }

    public InputStream getInputStream(String urlNameString) {
        String result = null;

        BufferedReader in = null;
        InputStream inputStream = null;
        try {
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
            inputStream = connection.getInputStream();
            return inputStream;
//            if (connection.getResponseCode() == 200) {
//                InputStream inputStream = connection.getInputStream();
//                byte[] data = StreamTool.read(inputStream);
//                String html = new String(data, "UTF-8");
//                return html;
//            }

        } catch (Exception e) {
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {

            }
        }

        return inputStream;
    }
}
