package com.cheng.cheng;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/6/6.
 */
public class LoginWireLess extends Login {

    public static String username;
    public static String password;

    public String[] getMacAndIP(String s) {
        String mac = "";
        String ip = "";
        // 拿到了返回的重定向的地址
        String[] results = s.split("&");
        for (String value : results) {
            String[] key_values = value.split("=");
            if (key_values[0] != null && key_values[0].equals("mac")) {
                mac = key_values[1];
            }
            if (key_values[0] != null && key_values[0].equals("ip")) {
                ip = key_values[1];
            }
        }
        return new String[]{mac, ip};
    }

    // HTTP POST请求
    public String sendPostWireless(String[] params) throws Exception {
        String mac = params[0];
        String ip = params[1];
        String url = "http://172.20.3.81:801/include/auth_action.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setConnectTimeout(500);

//        username = "Y30150635";
//        password = "d2FuZ2NoZW5n";
        username = ProperityHelper.readValue("username");
        password = ProperityHelper.readValue("password");


//        password = Utils.decode(password.getBytes());

        String urlParameters =
                "action=login&username=" + username + "@free&password={B}" + password + "&ac_id=3&user_ip=" + ip + "&nas_ip=''&user_mac=" + mac + "&ajax=1";

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
        }
        return response.toString();
    }
}
