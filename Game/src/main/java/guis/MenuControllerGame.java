package guis;

import common.FileUtilities;
import common.IToJsonObject;
import game.AbstractGameObject;
import game.AutoGame;
import game.GameCanvas;
import game.GameEditor;
import game.ManualGame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import views.boxes.*;
import views.icons.*;
import views.rounded.*;
import views.squared.*;

public class MenuControllerGame extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = 5110341197899182147L;
    private ManualGame mGame;
    private AutoGame aGame;

    //JMenuBar barraDelMenu;
    JMenu file, view, game, options;
    JMenuItem save, load, start, stop, square_size, exit, box, circle, icons, r_clover, s_clover;
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

        view.add(box = new JMenuItem("Box"));
        box.addActionListener(this);
        view.add(circle = new JMenuItem("Circle"));
        circle.addActionListener(this);
        view.add(icons = new JMenuItem("Icons"));
        icons.addActionListener(this);
        

        // Le ponemos un borde a la barra de men� y lo a�adimos a la ventana.
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Timer timer = mGame.getTimer();
        if (evento.getSource() == save) {
            System.out.println("Save seleccionado.");
            saveGame(timer);

        } else if (evento.getSource() == load) {
            System.out.println("load seleccionado.");
            loadGame();
        } else if (evento.getSource() == start) {
            System.out.println("Start seleccionado.");
            timer.start();
        } else if (evento.getSource() == stop) {
            System.out.println("Stop seleccionado.");
            timer.stop();
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
            int boxSize = Integer.parseInt(JOptionPane.showInputDialog("Insert box size"));
            mGame.getCanvas().setSquareEdge(boxSize);
        } else if (evento.getSource() == exit) {
            System.exit(0);
        }
        handlerViews(evento.getSource());
    }

    private void saveGame(Timer timer) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/resources/games"));
        int seleccion;
        if (timer.isRunning()) {
            timer.stop();
        }
        int size = mGame.getObjs().size();
        JSONObject[] Jobjs = new JSONObject[size];
        Object objs[];
        objs = mGame.getObjs().toArray();
        for (int i = 0; i < size; i++) {
            //Jobjs[i] = objs[i].toJSONObject();
            System.out.println(objs[i].toString());
        }
        seleccion = fileChooser.showSaveDialog(mGame.getContentPane());
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            //TODO: Save File     FileUtilities.writeJsonsToFile(Jobjs, file.getName());
        }
    }

    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        JSONArray jArray;
        fileChooser.setCurrentDirectory(new File("src/main/resources/games"));
        int seleccion = fileChooser.showOpenDialog(mGame.getContentPane());
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            jArray = FileUtilities.readJsonsFromFile(fichero.getPath());
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);
                //TODO: Load objects and repaint  mGame.getObjs().add(jObj);
                System.out.println(jObj.toString());
            }
        }
    }
    
    private void handlerViews(Object obj) {
        GameCanvas canvas = mGame.getCanvas();
        if (obj == box) {
            canvas.setViewsFamily(new BoxesFactory());
        }
        if (obj == circle) {
            canvas.setViewsFamily(new CircleFactory());
        }
        if (obj == icons) {
            canvas.setViewsFamily(new IconsFactory());
        }
    }

}

class HandlerEvent implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evento) {
        System.out.println("Pulsado bot�n: " + evento.getActionCommand());
    }
}
