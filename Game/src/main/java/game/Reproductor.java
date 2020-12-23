/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;

/**
 *
 * @author migue
 */
public class Reproductor {
    
    private String nombreSonido;

    public Reproductor(String nombreSonido) {
         this.nombreSonido = nombreSonido;
         try {
            Clip sonido = AudioSystem.getClip();
            File a = new File(nombreSonido);
            sonido.open(AudioSystem.getAudioInputStream(a));
            sonido.start();
            sonido.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception tipoError) {
            System.out.println("" + tipoError);
        }
        
    }
}
