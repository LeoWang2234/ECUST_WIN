package com.cheng.cheng;

import java.io.File;
import java.io.IOException;

/**
 * Created by cheng on 2017/6/6.
 */
public class FileHelper {

    public static boolean exists() {
        boolean exists = false;
        File file = new File("C:\\");
//        File file = new File("/Users/cheng/Desktop/");

        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                if (files[i].getName().equals("User.properties")) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    public static void makeFile() throws IOException {
        File file = new File("C:\\User.properties");
//        File file = new File("/Users/cheng/Desktop/User.properties");
        file.createNewFile();
    }
}
