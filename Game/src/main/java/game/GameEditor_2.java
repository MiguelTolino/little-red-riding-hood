/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import guis.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import views.AbstractGameView;

/**
 *
 * @author juanangel
 */
public class GameEditor_2 extends JFrame implements KeyListener {

    public static final int UP_KEY = 38;
    public static final int DOWN_KEY = 40;
    public static final int RIGTH_KEY = 39;
    public static final int LEFT_KEY = 37;

    public static final int NUM_ELEMENTS = 10;

    public static final int CANVAS_WIDTH = 480;

    int boxSize = 40;
    int row, col;

    Canvas canvas;
    JPanel canvasFrame, south_panel, button_panel;
    JLabel positionLabel;
    JButton b1, b2, b3;
    AbstractGameObject[] gArray;
    int g_counter;

    public GameEditor_2() throws Exception {

        super("Game Editor v2");

        positionLabel = new JLabel("[" + col + ", " + row + "]");
        positionLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        positionLabel.setPreferredSize(new Dimension(120, 40));
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        canvas = new Canvas(CANVAS_WIDTH, boxSize);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_WIDTH));
        canvas.setBorder(BorderFactory.createLineBorder(Color.blue));

        canvasFrame = new JPanel();
        canvasFrame.setPreferredSize(new Dimension(CANVAS_WIDTH + 40, CANVAS_WIDTH + 40));
        canvasFrame.add(canvas);
        getContentPane().add(canvasFrame);

        //Init AbstractGameObject
        gArray = new AbstractGameObject[NUM_ELEMENTS];
        g_counter = 0;

        //Init buttons
        b1 = new JButton("Clover");
        b2 = new JButton("Dandelion");
        b3 = new JButton("Spider");
        button_panel = new JPanel(new FlowLayout());
        button_panel.add(b1);
        button_panel.add(b2);
        button_panel.add(b3);

        south_panel = new JPanel(new GridLayout(2, 1));
        south_panel.add(positionLabel);
        south_panel.add(button_panel);
        getContentPane().add(south_panel, BorderLayout.SOUTH);

        setSize(CANVAS_WIDTH + 40, CANVAS_WIDTH + 80);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
        b1.addActionListener(new EventManager());
        b2.addActionListener(new EventManager());
        b3.addActionListener(new EventManager());
        System.out.println(this.getFocusableWindowState());
        this.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    // Version 1
    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        System.out.println("code --> " + tecla);
        switch (tecla) {
            case UP_KEY:
                System.out.println("UP_KEY");
                row--;
                break;
            case DOWN_KEY:
                System.out.println("DOWN_KEY");
                row++;
                break;
            case RIGTH_KEY:
                System.out.println("RIGTH_KEY");
                col++;
                break;
            case LEFT_KEY:
                System.out.println("LEFT_KEY");
                col--;
                break;
        }
        positionLabel.setText("[" + col + ", " + row + "]");
        setInLimits();
        canvas.setSquareCoordinates(col, row);
    }

    private void setInLimits() {

        int lastBox = (CANVAS_WIDTH / boxSize) - 1;

        if (col < 0) {
            col = 0;
        } else if (col > lastBox) {
            col = lastBox;
        }

        if (row < 0) {
            row = 0;
        } else if (row > lastBox) {
            row = lastBox;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    class Canvas extends JPanel {

        int size, boxSize;
        int pX, pY;

        public Canvas(int size, int boxSize) {
            this.size = size;
            this.boxSize = boxSize;
        }

        public void setSquareCoordinates(int x, int y) {
            pX = x;
            pY = y;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGrid(g);
            /*    g.setColor(Color.BLUE);
            g.fillRect(pX * boxSize + 4, pY * boxSize + 4, boxSize - 8, boxSize - 8);*/
            drawSquare(6, 7, g);
        }

        private void drawGrid(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.LIGHT_GRAY);
            int nLines = size / boxSize;
            System.out.println("---- " + nLines);
            for (int i = 1; i < nLines; i++) {
                g.drawLine(i * boxSize, 0, i * boxSize, size);
                g.drawLine(0, i * boxSize, size, i * boxSize);
            }
            g.setColor(c);
        }

    }

    class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isEmpthy()) {
                System.out.println("Game array is full");
                return;
            }
            JButton event = (JButton) e.getSource();
            if (event.getText().equals("Clover")) {
                gArray[g_counter] = new Blossom();
            }
            if (event.getText().equals("Dandelion")) {
                gArray[g_counter] = new Bee();
            }
            if (event.getText().equals("Spider")) {
                gArray[g_counter] = new Spider();
            }
            g_counter++;
            printGameItems();
            requestFocusInWindow();
        }

        private boolean isEmpthy() {
            if (g_counter < 10) {
                return (true);
            } else {
                return (false);
            }
        }

        private void printGameItems() {
            for (AbstractGameObject gObj : gArray) {
                if (gObj != null) {
                    System.out.println(gObj.toString());
                }
            }
            System.out.println();

        }

    }

    private void drawSquare(int pX, int pY, Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(pX * boxSize, pY * boxSize, boxSize, boxSize);
    }

    public static void main(String[] args) throws Exception {
        GameEditor_2 gui = new GameEditor_2();
    }
}
