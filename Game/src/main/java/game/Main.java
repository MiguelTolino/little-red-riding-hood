/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import guis.PanelConImagen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.*;

/**
 *
 * @author migue
 */
public class Main {

    public static void main(String[] args) throws Exception {

        PanelConImagen initWindow = new PanelConImagen();
        int choice = initWindow.getSelection();
        GameEditor_4 ge4;
        Game_3 g3;
        Game_2 g2;

        while ((choice = initWindow.getSelection()) < 0) {
            System.out.println(choice);
        }
        switch (choice) {
            case 0:
                System.out.println("Modo manual");
                g2 = new Game_2();
                break;
            case 1:
                System.out.println("Modo automatico");
                g3 = new Game_3();
                break;
            case 2:
                ge4 = new GameEditor_4();
                break;
            default:
                System.out.println("Default");
        }
        initWindow.dispatchEvent(new WindowEvent(initWindow, WindowEvent.WINDOW_CLOSING));

    }

}
