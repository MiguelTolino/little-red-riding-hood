/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import common.FileUtilities;
import static common.IToJsonObject.TypeLabel;
import guis.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public final class ManualGame extends JFrame implements KeyListener, ActionListener {

    // KeyBoard
    public static final int UP_KEY = 38;
    public static final int DOWN_KEY = 40;
    public static final int RIGTH_KEY = 39;
    public static final int LEFT_KEY = 37;
    public static final int SPACE_KEY = 32;

    //Resources
    public static final String NAME = "Little Red Riding Hood Game";
    public static final String ICON = "C:\\Users\\migue\\UPCT\\PIT\\practicas\\Game\\src\\main\\resources\\images\\";
    int lastKey = RIGTH_KEY;

    // Game Panel and 
    public static final int CANVAS_WIDTH = 480;
    int boxSize = 40;
    int row, col;
    GameCanvas canvas;
    JPanel canvasFrame;
    JLabel dataLabel;
    MenuControllerGame menu;
    ImageIcon img;

    // Timer
    Timer timer;
    int tick = 200;

    // Game Variables
    ConcurrentLinkedQueue<IGameObject> gObjs = new ConcurrentLinkedQueue<>();
    RidingHood_2 ridingHood = new RidingHood_2(new Position(0, 0), 1, 2);
    public int screenCounter = 0;

    public ManualGame() throws Exception {

        super(NAME);

        row = CANVAS_WIDTH / boxSize;
        col = row;
        // Game Initializations.
        gObjs.add(ridingHood);
        loadNewBoard(0);

        // Window initializations.
        dataLabel = new JLabel(ridingHood.toString());
        dataLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //dataLabel.setPreferredSize(new Dimension(120, 40));
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);

        canvas = new GameCanvas(CANVAS_WIDTH, boxSize);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_WIDTH));
        canvas.setBorder(BorderFactory.createLineBorder(Color.blue));

        canvasFrame = new JPanel();
        canvasFrame.setPreferredSize(new Dimension(CANVAS_WIDTH + 40, CANVAS_WIDTH));
        canvasFrame.add(canvas);
        getContentPane().add(canvasFrame);
        getContentPane().add(dataLabel, BorderLayout.SOUTH);

        //Set menu bar
        menu = new MenuControllerGame(this);
        setJMenuBar(menu);

        //Set Icon
        img = new ImageIcon(ICON + "icon.png");
        this.setIconImage(img.getImage());

        setSize(CANVAS_WIDTH + 40, CANVAS_WIDTH + 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(tick, this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    // Version 1
    @Override
    public void keyPressed(KeyEvent ke) {
        lastKey = ke.getKeyCode();
        if (lastKey == SPACE_KEY) {
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    /**
     * Se invoca en cada tick de reloj
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        // Actions on Caperucita
        setDirection(lastKey);

        // Moving Caperucita
        ridingHood.moveToNextPosition();

        // Check if Caperucita is in board limits
        setInLimits();

        //BugsMovement
        BugsMovement();

        // Logic to change to a new screen.
        if (processCell() == 1) {
            screenCounter++;
            ridingHood.incLifes(1);
            clearObjs();
            loadNewBoard(screenCounter);
        }

        // Updating graphics and labels
        dataLabel.setText(ridingHood.toString());
        canvas.drawObjects(gObjs);
    }

    /*
    Procesa la celda en la que se encuentra caperucita.
    Si Caperucita está sobre un blossom añade su valor al de Caperucita
    y lo elimina del tablero.
    Devuelve el número de blossoms que hay en el tablero.
     */
    private int processCell() {
        Position rhPos = ridingHood.getPosition();
        int end = 0;
        for (IGameObject gObj : gObjs) {
            if (gObj != ridingHood && rhPos.isEqual(gObj.getPosition())) {
                if (gObj instanceof Blossom) {
                    int v = ridingHood.getValue() + gObj.getValue();
                    ridingHood.setValue(v);
                }
                gObjs.remove(gObj);
            }
        }
        for (IGameObject gObj : gObjs) {
            if (gObj instanceof Blossom || gObj instanceof Bee) {
                end++;
            }
        }
        if (end == 0) {
            return (1);
        }
        return gObjs.size();
    }

    private void clearObjs() {
        for (IGameObject objs : gObjs) {
            if (!(objs instanceof RidingHood_2)) {
                gObjs.remove(objs);
            }
        }
    }

    /*
    Fija la dirección de caperucita.
    Caperucita se moverá en esa dirección cuando se invoque
    su método moveToNextPosition.
     */
    private void setDirection(int lastKey) {
        switch (lastKey) {
            case UP_KEY:
                ridingHood.moveUp();
                break;
            case DOWN_KEY:
                ridingHood.moveDown();
                break;
            case RIGTH_KEY:
                ridingHood.moveRigth();
                break;
            case LEFT_KEY:
                ridingHood.moveLeft();
                break;
        }
    }

    /*
    Comprueba que Caperucita no se sale del tablero.
    En caso contrario corrige su posición
     */
    private void setInLimits() {

        int lastBox = (CANVAS_WIDTH / boxSize) - 1;

        if (ridingHood.getPosition().getX() < 0) {
            ridingHood.position.x = 0;
        } else if (ridingHood.getPosition().getX() > lastBox) {
            ridingHood.position.x = lastBox;
        }

        if (ridingHood.getPosition().getY() < 0) {
            ridingHood.position.y = 0;
        } else if (ridingHood.getPosition().getY() > lastBox) {
            ridingHood.position.y = lastBox;
        }
    }

    /*
    Carga un nuevo tablero
     */
    public void loadNewBoard(int counter) {
        switch (counter) {
            case 0:
                Screen s1 = new Screen(row, 2, 4, new Fly(), new Blossom());
                for (int i = 0; i < s1.getN1(); i++) {
                    Position p1 = new Position((int) ((Math.random() * row)), (int) (Math.random() * row));
                    s1.getL1().add(new Fly(p1, 10, 10));
                }
                for (int i = 0; i < s1.getN2(); i++) {
                    Position p2 = new Position((int) (Math.random() * row), (int) (Math.random() * row));
                    s1.getL2().add(new Blossom(p2, (int) (Math.random() * 20), 10));
                }
                s1.setObjs(gObjs);
                break;
            case 1:
                Screen s2 = new Screen(row, 2, 4, new Bee(), new Blossom());
                for (int i = 0; i < s2.getN1(); i++) {
                    Position p1 = new Position((int) ((Math.random() * row)), (int) (Math.random() * row));
                    s2.getL1().add(new Bee(p1, 10, 10));
                }
                for (int i = 0; i < s2.getN2(); i++) {
                    Position p2 = new Position((int) (Math.random() * row), (int) (Math.random() * row));
                    s2.getL2().add(new Blossom(p2, (int) (Math.random() * 20), 10));
                }
                s2.setObjs(gObjs);
                break;
            case 2:
                String path = "src/main/resources/games/game.txt";
                System.out.println("Loading objects");
                JSONArray jArray = FileUtilities.readJsonsFromFile(path);
                if (jArray != null) {
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        String typeLabel = jObj.getString(TypeLabel);
                        gObjs.add(GameObjectsJSONFactory.getGameObject(jObj));
                    }
                }
                break;
            default:
                gObjs.add(new Bee(new Position(3, 2), 10, 10));
                gObjs.add(new Blossom(new Position(2, 8), 4, 10));
                gObjs.add(new Blossom(new Position(8, 8), 10, 10));
                gObjs.add(new Blossom(new Position(8, 2), 4, 10));
        }
    }

    public ConcurrentLinkedQueue<IGameObject> getObjs() {
        return gObjs;
    }

    public Timer getTimer() {
        return (timer);
    }

    public GameCanvas getCanvas() {
        return (canvas);
    }

    public static void main(String[] args) throws Exception {
        ManualGame gui = new ManualGame();
    }

    public void BugsMovement() {
        for (IGameObject gObj : gObjs) {
            if (gObj instanceof Fly) {
                ((Fly) gObj).moveFly(row);
                if (gObj.getPosition().isEqual(ridingHood.getPosition())) {
                    int value = ridingHood.getValue();
                    ridingHood.setValue(value - gObj.getValue());
                }
            } else if (gObj instanceof Bee) {
                ((Bee) gObj).moveBee(gObjs);
                if (gObj.getPosition().isEqual(ridingHood.getPosition())) {
                    int value = ridingHood.getValue();
                    ridingHood.setValue(value - gObj.getValue());
                }
                if (gObj.getPosition().getX() >= row || gObj.getPosition().getX() < 0) {
                    gObjs.remove(gObj);
                }
            } else if (gObj instanceof Spider) {
                ((Spider) gObj).moveSpider(ridingHood);
                if(gObj.getPosition().isEqual(ridingHood.getPosition()))
                    ridingHood.incLifes(-1);
            }

        }
    }
}
