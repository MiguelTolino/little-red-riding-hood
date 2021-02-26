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
        ManualGame mg;
        while ((choice = initWindow.getSelection()) < 0) {
            Thread.sleep(100);
        }
        switch (choice) {
            case 0:
                System.out.println("Modo manual");
                mg = new ManualGame();
                break;
        }
        initWindow.dispatchEvent(new WindowEvent(initWindow, WindowEvent.WINDOW_CLOSING));

    }

}
