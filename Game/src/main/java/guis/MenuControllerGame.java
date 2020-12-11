package guis;

import common.FileUtilities;
import common.IToJsonObject;
import game.AutoGame;
import game.GameEditor;
import game.ManualGame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import org.json.JSONObject;

public class MenuControllerGame extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = 5110341197899182147L;
    private ManualGame mGame;
    private AutoGame aGame;

    //JMenuBar barraDelMenu;
    JMenu file, view, game, options;
    JMenuItem save, load, start, stop, square_size, exit;
    JButton geditor;

    public MenuControllerGame(ManualGame mg) {
        super();
        this.mGame = mg;
        construirMenus();
    }

    public MenuControllerGame(AutoGame ag) {
        super();
        aGame = ag;
        construirMenus();
    }

    private void construirMenus() {

        //barraDelMenu = new JMenuBar();
        file = new JMenu("File");
        view = new JMenu("View");
        game = new JMenu("Game");
        options = new JMenu("Options");

        // Creamos entradas de menus y suscribimos la ventana
        // a los eventos producidos en dichas entradas.
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);
        start = new JMenuItem("Start");
        start.addActionListener(this);
        stop = new JMenuItem("Stop");
        stop.addActionListener(this);
        geditor = new JButton("Game Editor");
        geditor.addActionListener(this);
        square_size = new JMenuItem("Square Size(px)");
        square_size.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        // Creamos un manejador especifico para los botones.   
        HandlerEvent h = new HandlerEvent();

        // Creamos los botones y suscribimos el manejador 'm' a los
        // eventos producidos por dichos botones.
        // Creamos el botón b3, un manejador anónimo para el mismo y
        // suscribimos el manejador anónimo a su botón.
        // A�adimos elementos a barra de men�.
        this.add(file);
        this.add(view);
        this.add(game);
        this.add(options);
        this.add(geditor);

        // A�adimos elementos a men� 1.
        file.add(save);
        file.addSeparator();

        // A�adimos elementos a men� 3.
        file.add(load);
        file.addSeparator();
        file.add(exit);

        // A�adimos elementos a men� 2.              
        game.add(start);
        game.addSeparator();
        game.add(stop);
        game.addSeparator();

        options.add(square_size);

        // Le ponemos un borde a la barra de men� y lo a�adimos a la ventana.
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == save) {
            System.out.println("Save seleccionado.");
        } else if (evento.getSource() == load) {
            System.out.println("load seleccionado.");
        } else if (evento.getSource() == start) {
            System.out.println("Start seleccionado.");
            mGame.getTimer().start();
        } else if (evento.getSource() == stop) {
            System.out.println("Stop seleccionado.");
            mGame.getTimer().stop();
        } else if (evento.getSource() == geditor) {
            System.out.println("geditor seleccionado.");
            mGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mGame.dispatchEvent(new WindowEvent(mGame, WindowEvent.WINDOW_CLOSING));
            try {
                GameEditor ge = new GameEditor();
            } catch (Exception ex) {
                Logger.getLogger(MenuControllerGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evento.getSource() == square_size) {
            //TODO: Change box size mg.boxSize = Integer.parseInt(JOptionPane.showInputDialog("Insert box size"));
        } else if (evento.getSource() == exit) {
            System.exit(0);
        }
    }

}

class HandlerEvent implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evento) {
        System.out.println("Pulsado bot�n: " + evento.getActionCommand());
    }
}
