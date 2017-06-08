package com.cheng.login;

import com.cheng.helper.JsoupHelper;

import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    private String name = "";
    private String password = "";

    public static int loginWireLess() {

        LoginWireLess loginWireLess = new LoginWireLess();
        String result = null;
        try {
            result = loginWireLess.sendGet("http://login.ecust.edu.cn/&arubalp=07ab5286-6c88-4ee7-997d-1563e8afb4");

        } catch (Exception e) {
            System.out.println("登录失败");
        }

//        System.out.println(result);
        String[] params = loginWireLess.getMacAndIP(result);

        // 如果拿到的请求里没有需要的参数值，那么需要走有线登录
        if (params[0].equals("")) {
            return LoginStatus.ALREADY_LOGINED.getIndex();
        }

        // 无线登录
        // 我觉得可以这样，先走无线，因为不管是连没脸线，走无线都能比较快地给到反馈
        try {
            String response = loginWireLess.sendPostWireless(params);
//            System.out.println(response);
            // 判断返回的字符串中是否有 login_ok 字样，若有则登录成功
            int loginStatus = response.indexOf("login_ok");
            if (loginStatus == -1) {
                return LoginStatus.FAILED.getIndex();
            }else {
                return LoginStatus.SUCCESS.getIndex();
            }
        } catch (Exception e) {
            return LoginStatus.FAILED.getIndex();
        }
    }

    public static int loginWithWire() {
        String result;//
        // 说明无线登录走不通了，可能有线已经登录了，也可能没有连接无线
        LoginWithWire loginWithWire = new LoginWithWire();

        Map<String, String> attrs = new HashMap();

        attrs = JsoupHelper.prase("http://172.20.3.90:803/srun_portal_pc.php?ac_id=7&");
        try {
            String response = loginWithWire.sendPostWire(attrs);

            // 判断返回的字符串中是否有 login_ok 字样，若有则登录成功
            int loginStatus = response.indexOf("login_ok");

//            System.out.println(response);
            if (loginStatus == -1) {
                return LoginStatus.FAILED.getIndex();
            }else {
                return LoginStatus.SUCCESS.getIndex();
            }
        } catch (Exception e) {
            return LoginStatus.FAILED.getIndex();
        }
    }


}






