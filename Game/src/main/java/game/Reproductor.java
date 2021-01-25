/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author migue
 */
public class Reproductor {

    private String nombreSonido;
    Clip sonido;

    public Reproductor(String nombreSonido) {
        this.nombreSonido = nombreSonido;
        if (this.nombreSonido.equals("bosque.wav")) {
            try {
                sonido = AudioSystem.getClip();
                File a = new File(nombreSonido);
                sonido.open(AudioSystem.getAudioInputStream(a));
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException tipoError) {
                System.out.println("" + tipoError);
            }
        } else {
            {
                try {
                    sonido = AudioSystem.getClip();
                    File a = new File(nombreSonido);
                    sonido.open(AudioSystem.getAudioInputStream(a));
                    sonido.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException tipoError) {
                    System.out.println("" + tipoError);
                }

            }

        }
    }

    public void startMusic() {
        sonido.start();
        sonido.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        sonido.stop();
    }

    public void start() {
        sonido.start();
        sonido.flush();
    }
}
