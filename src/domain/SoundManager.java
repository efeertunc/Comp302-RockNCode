package domain;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import java.net.URL;
public class SoundManager {
    private Clip clip;
    private FloatControl floatControl;
    private int volumeScale = 3;
    private float volume;
    private final URL[] soundUrl = new URL[30];

    public SoundManager() {

        soundUrl[0] = getClass().getResource("/sounds/coin.wav");
        soundUrl[1] = getClass().getResource("/sounds/Dungeon.wav");
        soundUrl[2] = getClass().getResource("/sounds/pop.wav");
        soundUrl[3] = getClass().getResource("/sounds/key.wav");
        soundUrl[4] = getClass().getResource("/sounds/MOP.wav");


    }
    public void setFile(int index) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[index]);
            clip =AudioSystem.getClip();
            clip.open(ais);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
    public void playSoundEffect(int index) {
        this.setFile(index);
        this.play();
    }
    public void playMusic(int index) {
        setFile(index);
        play();
        loop();
    }

    public void stopMusic() {
        this.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }

        floatControl.setValue(volume);
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public SoundManager setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
        return this;
    }
}
