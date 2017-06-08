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

        //添加请求头
        con.setRequestMethod("POST");
        con.setConnectTimeout(500);

//        password = Utils.decode(password.getBytes());

//        username = "Y30150635";
//        password = "d2FuZ2NoZW5n";

        username = ProperityHelper.readValue("username");
        password = ProperityHelper.readValue("password");

        String urlParameters = "action=login&username=" + username + "@free&password={B}" + password + "&ac_id="+attrs.get("ac_id")+"&user_ip="+attrs.get("user_ip")+"&nas_ip=''&user_mac=''&ajax=1&ip="+attrs.get("ip");

        //发送Post请求
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
        // 使用finally块来关闭输入流
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {

            }
        }

        return inputStream;
    }
}
