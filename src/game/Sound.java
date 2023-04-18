package game;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    /*
    *Kalau Mau nambah audio sendiri bisa upload audio file format wav ke folder package sound
    * kalau mau insert ke URL nya bisa pakai "/sound/[nama file audio].wav
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/red velvet - peek a boo (instrumental).wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
