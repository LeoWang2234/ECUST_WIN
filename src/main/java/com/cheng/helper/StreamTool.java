package com.cheng.helper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Jay on 2015/9/7 0007.
 */
public class StreamTool {
    //�����ж�ȡ����
    public static byte[] read(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}