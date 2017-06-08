package com.cheng.helper;

import java.io.File;
import java.io.IOException;

/**
 * Created by login on 2017/6/6.
 *
 * 存放用户名和密码的配置文件
 */
public class FileHelper {

//    static String filePath = "C:\\";
    static String filePath = "/Users/cheng/Desktop";
    static String fileName = "User.properties";

    public static boolean exists() {
        boolean exists = false;
        File file = new File(filePath);

        File[] files = file.listFiles();
        if (files != null) {
            System.out.println("files not null");
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()) {
                    if (files[i].getName().equals(fileName)) {
                        exists = true;
                        break;
                    }
                }
            }
        }
        return exists;
    }

    public static void makeFile() throws IOException {
        File file = new File(filePath+"/"+fileName);
        System.out.println(filePath+"/"+fileName);
        file.createNewFile();
    }
}
