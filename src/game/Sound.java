package game;
import javax.sound.sampled.*;
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
        setVolume(-5f);
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void setVolume(float amount){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float newVolume = amount;
        if (newVolume < gainControl.getMinimum()) {
            newVolume = gainControl.getMinimum();
        }
        else if (newVolume > gainControl.getMaximum()){
            newVolume = gainControl.getMaximum();
        }
        gainControl.setValue(newVolume);
    }
}
