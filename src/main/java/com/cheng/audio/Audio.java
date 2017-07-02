package com.cheng.audio;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by cheng on 2017/7/2.
 */
public class Audio {

    public static String getAudio(String input) {
        try {

            // ��ȡ������������
            URL ur = new URL("http://dict.youdao.com/dictvoice?audio=" + input);
            HttpURLConnection connection = (HttpURLConnection) ur.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inStream = connection.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            // ��ȡ���ص����ݣ�д�뵽�������
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            byte[] output = outStream.toByteArray();

            String fileName = input + ".mp3";
//            final String filePath = "/Users/cheng/Desktop/" + fileName;
            final String filePath = "C:\\Users\\Administrator\\Desktop\\" + fileName;

            // д�뵽�ļ���
            if (null != output && output.length > 0) {
                System.out.println("��ȡ����" + output.length + " �ֽ�");

                File file = new File(filePath);
                FileOutputStream fops = new FileOutputStream(file);
                fops.write(output);
                fops.flush();
                fops.close();
                System.out.println("ͼƬ�Ѿ�д�뵽����");

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                        }
                        deleteFile(filePath);
                    }
                }).start();

            }
            System.out.println(filePath);
            return filePath;
        } catch (Exception e) {
            System.out.println("��ȡ��Ƶʧ��:" + e);
        }
        return "";
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }
}
