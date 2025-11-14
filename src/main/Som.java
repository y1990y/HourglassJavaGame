package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Som {

    Clip clip;
    URL somURL[] = new URL[30];

    public Som() {

        somURL[0] = getClass().getResource("/sons/BackgroundMusic.wav");
        somURL[1] = getClass().getResource("/sons/PegaMoeda.wav");
        somURL[2] = getClass().getResource("/sons/AbrePorta.wav");
        somURL[3] = getClass().getResource("/sons/PegaPoder.wav");
    }

    public void defineArquivo(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(somURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {

        }
    }

    public void playSFX() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-25.0f); // Reduz o volume em 20 decibéis.
        clip.start();
    }

    public void playMusica() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-25.0f); // Reduz o volume em 30 decibéis.
        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }
}