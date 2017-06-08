package com.cheng.helper;

import com.cheng.helper.FileHelper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class ProperityHelper {

    private static URL url;
    private static String filePath;
    private static InputStream in;
    static {
//        url = ProperityHelper.class.getResource("/user/User.properties");
        if (!FileHelper.exists()) {
            System.out.println("file not exists");
            try {
                FileHelper.makeFile();
            } catch (IOException e) {
            }
        }

        filePath = "C:\\User.properties";
//        filePath = "/Users/cheng/Desktop/User.properties";

    }

    //����key��ȡvalue
    public static String readValue(String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty(key);
//            System.out.println(key + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //��ȡproperties��ȫ����Ϣ
    public static void readProperties(String c) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = props.getProperty(key);
//                System.out.println(key + Property);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //д��properties��Ϣ
    public static void writeProperties(String parameterName, String parameterValue) {
        Properties prop = new Properties();
        try {

//            System.out.println(filePath);
            InputStream fis = new FileInputStream(filePath);
            //���������ж�ȡ�����б�����Ԫ�ضԣ�
            prop.load(fis);
            //���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
            //ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            //���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
            //���� Properties ���е������б�����Ԫ�ضԣ�д�������
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
        }
    }

    public static void main(String[] args) {

        String path =  url.getPath();

        //��ȡclasspath·��
//        System.out.println("classpath·���� "+ProperityHelper.class.getClassLoader().getResource("").getPath());

        //��ȡ��ǰ��ļ���·��
//        System.out.println("��ǰ�����·���� "+ProperityHelper.class.getResource("").getPath());

        // ��ȡ�ļ�resourcesĿ¼���ļ��������ַ���
        // ����һ����classpath·��������ȡ
//        String path1 = ProperityHelper.class.getClassLoader().getResource("user/User.properties").getPath();
        // ���������������·���������൱��ʹ�þ���·��
//        String path2 = ProperityHelper.class.getResource("/user/User.properties").getPath();
        // ���������������·���������൱��ʹ�����·��
//        String path3 = (ProperityHelper.class.getResource("../../../user/User.properties").getPath());

//        readValue(path1, "username");
//        writeProperties(path, "age", "21");
//        writeProperties(path, "age", "22");
//        writeProperties(path, "age", "23");
//        writeProperties(path, "age", "24");
//        writeProperties(path);
        readProperties(path);
        System.out.println("OK");
    }
}

