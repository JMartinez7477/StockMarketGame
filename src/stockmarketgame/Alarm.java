/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author S531749
 */
public class Alarm {
    Clip clip;
    
    public Alarm(String filePath){
        try{
            File file = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }catch(Exception e){
            System.out.println(e);
        }
        
        clip.start();
    }
    
    public void stop(){
        clip.stop();
    }
}
