
package jogocg;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Musica {
    Clip clip;
    URL soundURL[] = new URL[30];
    
    public Musica(){
        soundURL[0] = getClass().getResource("/musica/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/musica/coin.wav");
        soundURL[2] = getClass().getResource("/musica/powerup.wav");
        soundURL[3] = getClass().getResource("/musica/unlock.wav");
        soundURL[4] = getClass().getResource("/musica/fanfare.wav");
    }
    
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
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
