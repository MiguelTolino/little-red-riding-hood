/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import guis.FirstWindow;
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

        //Choose Game
        FirstWindow initWindow = new FirstWindow();
        int choice = -1;
        GameEditor ge;
        AutoGame ag;
        ManualGame mg;

        while ((choice = initWindow.getSelection()) < 0) {
            System.out.println(choice);
        }
        switch (choice) {
            case 0:
                System.out.println("Modo manual");
                mg = new ManualGame();
                break;
            case 1:
                System.out.println("Modo automatico");
                ag = new AutoGame();
                break;
            case 2:
                System.out.println("Game Editor");
                ge = new GameEditor();
                break;
        }
        initWindow.dispatchEvent(new WindowEvent(initWindow, WindowEvent.WINDOW_CLOSING));

    }

}
