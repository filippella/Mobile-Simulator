/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class SoundManager {

    private static SoundManager instance;
    private Clip clip;

    public synchronized static SoundManager getSoundManager() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * This method takes a sound url as parameter and plays it if is valid
     *
     * @param url the sound path
     */
    public void playSound(String url) {
        try {
            clip = AudioSystem.getClip();
            InputStream audioSrc = getClass().getResourceAsStream(url);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.err.println("Error --> " + e.getMessage());
        }
    }

    /**
     * This method will stop the current playing sound
     */
    public synchronized void stopSound() {
        if (clip.isActive()) {
            clip.stop();
        }
    }

    protected void decreaseSound() {
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-5.0f);
    }

    protected void increaseSound() {
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(5.0f);
    }
}
