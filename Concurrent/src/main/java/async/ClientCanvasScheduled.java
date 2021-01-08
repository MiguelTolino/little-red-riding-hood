/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package async;

import game.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.Timer;
import views.AbstractGameView;
import views.IAWTGameView;
import views.IViewFactory;
import views.boxes.BoxesFactory;

/**
 *
 * @author juanangel
 */
public class ClientCanvasScheduled extends JPanel implements Runnable {

    IViewFactory viewFactory = new BoxesFactory();

    int editCol, editRow;
    int canvasEdge = 400;
    int squareEdge = 20;
    boolean squareOn = true;
    int xOffset, yOffset;

    ConcurrentLinkedQueue<ArrayList<IGameObject>> frames = new ConcurrentLinkedQueue<>();
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public ClientCanvasScheduled() {
    }

    public ClientCanvasScheduled(int canvasEdge, int squareEdge) {
        this.squareEdge = squareEdge;
        this.canvasEdge = canvasEdge;
    }

    public void playMovie(ArrayList<GameFrame> movie) {
        if (movie != null) {
            for (GameFrame f : movie) {
                frames.add(f.getObjects());
            }
            //We set a 2s delay and 1s speed movement 
            executor.scheduleAtFixedRate(this, 2, 1, TimeUnit.SECONDS);
        }
    }

    private void drawGrid(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.lightGray);
        int nLines = canvasEdge / squareEdge;

        for (int i = 0; i <= nLines; i++) {
            g.drawLine(i * squareEdge, 0, i * squareEdge, canvasEdge);
            g.drawLine(0, i * squareEdge, canvasEdge, i * squareEdge);
        }
        g.setColor(c);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        ArrayList<IGameObject> frame = frames.poll();
        if (frame == null) {
            return;
        }
        for (IGameObject gObj : frame) {
            if (gObj != null) {
                IAWTGameView v;
                try {
                    v = AbstractGameView.getView(gObj, squareEdge, viewFactory);
                    v.draw(g);
                } catch (Exception ex) {
                }
            }
        }
    }

    @Override
    public void run() {
        repaint();
        if (frames.isEmpty()) {
            System.out.println("ClientCanvas. End of Movie");
            executor.shutdown();
            executor = Executors.newScheduledThreadPool(1);
        }
    }
}
