package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public Sound() {
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/blocked.wav");
        soundURL[2] = getClass().getResource("/sound/dooropen.wav");
        soundURL[3] = getClass().getResource("/sound/powerup.wav");
    }
    public void setFile(int i) throws UnsupportedAudioFileException, IOException {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
