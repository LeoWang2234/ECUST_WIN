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

    //根据key读取value
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

    //读取properties的全部信息
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

    //写入properties信息
    public static void writeProperties(String parameterName, String parameterValue) {
        Properties prop = new Properties();
        try {

//            System.out.println(filePath);
            InputStream fis = new FileInputStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            //以适合使用 load 方法加载到 Properties 表中的格式，
            //将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
        }
    }

    public static void main(String[] args) {

        String path =  url.getPath();

        //获取classpath路径
//        System.out.println("classpath路径： "+ProperityHelper.class.getClassLoader().getResource("").getPath());

        //获取当前类的加载路径
//        System.out.println("当前类加载路径： "+ProperityHelper.class.getResource("").getPath());

        // 读取文件resources目录中文件的若干种方法
        // 方法一：从classpath路径出发读取
//        String path1 = ProperityHelper.class.getClassLoader().getResource("user/User.properties").getPath();
        // 方法二：从类加载路径出发，相当于使用绝对路径
//        String path2 = ProperityHelper.class.getResource("/user/User.properties").getPath();
        // 方法三：从类加载路径出发，相当于使用相对路径
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

