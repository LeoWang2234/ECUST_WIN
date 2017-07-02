package com.cheng.audio;

import javazoom.jl.player.*;
import java.io.FileInputStream;

public class PlayMusic {

    public static void playAudio(String filePath){
        PlayMusic.Play(filePath);
    }
    //²¥·ÅÒôÆµÎÄ¼þ
    public static void Play(String filePath){
        try{
            try {
                FileInputStream fis = new FileInputStream(filePath);
                Player playMP3 = new Player(fis);

                playMP3.play();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
        catch (Exception e){}
    }
}