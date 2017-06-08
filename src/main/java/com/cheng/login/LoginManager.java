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
            System.out.println("��¼ʧ��");
        }

//        System.out.println(result);
        String[] params = loginWireLess.getMacAndIP(result);

        // ����õ���������û����Ҫ�Ĳ���ֵ����ô��Ҫ�����ߵ�¼
        if (params[0].equals("")) {
            return LoginStatus.ALREADY_LOGINED.getIndex();
        }

        // ���ߵ�¼
        // �Ҿ��ÿ����������������ߣ���Ϊ��������û���ߣ������߶��ܱȽϿ�ظ�������
        try {
            String response = loginWireLess.sendPostWireless(params);
//            System.out.println(response);
            // �жϷ��ص��ַ������Ƿ��� login_ok �������������¼�ɹ�
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
        // ˵�����ߵ�¼�߲�ͨ�ˣ����������Ѿ���¼�ˣ�Ҳ����û����������
        LoginWithWire loginWithWire = new LoginWithWire();

        Map<String, String> attrs = new HashMap();

        attrs = JsoupHelper.prase("http://172.20.3.90:803/srun_portal_pc.php?ac_id=7&");
        try {
            String response = loginWithWire.sendPostWire(attrs);

            // �жϷ��ص��ַ������Ƿ��� login_ok �������������¼�ɹ�
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






