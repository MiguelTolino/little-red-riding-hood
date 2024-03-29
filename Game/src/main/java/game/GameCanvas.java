
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import views.AbstractGameView;
import views.IAWTGameView;
import views.IViewFactory;
import views.boxes.BoxesFactory;
import views.boxes.CircleFactory;
import views.icons.IconsFactory;

public class GameCanvas extends JPanel {

    IViewFactory viewFactory = new IconsFactory();

    int editCol, editRow;
    int canvasEdge = 400;
    int squareEdge = 20;
    boolean squareOn = true;
    private Image background;
    private Image pause;
    public boolean is_pause = false;
    public static final String PAUSE_PATH = "C:\\Users\\migue\\UPCT\\PIT\\practicas\\Game\\src\\main\\resources\\images\\pause.jpg";

    ConcurrentLinkedQueue<IGameObject> gObjects = new ConcurrentLinkedQueue<>();

    public GameCanvas() {
    }

    public GameCanvas(int canvasEdge, int squareEdge, String background) {
        this.squareEdge = squareEdge;
        this.canvasEdge = canvasEdge;
        this.setOpaque(false);
        this.background = new ImageIcon(background).getImage();
        this.pause = new ImageIcon(PAUSE_PATH).getImage();
        repaint();
    }

    public void setSquareEdge(int squareEdge) {
        this.squareEdge = squareEdge;
        repaint();
    }

    public void drawObjects(ConcurrentLinkedQueue<IGameObject> gObjects) {
        if (gObjects != null) {
            this.gObjects = gObjects;
        }
        repaint();
    }

    public void refresh() {
        repaint();
    }

    public void setViewsFamily(IViewFactory viewFactory) {
        if (viewFactory != null) {
            this.viewFactory = viewFactory;
        }
        repaint();
    }

    private void drawGrid(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.lightGray);
        int nLines = canvasEdge / squareEdge;

        for (int i = 1; i < nLines; i++) {
            g.drawLine(i * squareEdge, 0, i * squareEdge, canvasEdge);
            g.drawLine(0, i * squareEdge, canvasEdge, i * squareEdge);
        }
        g.setColor(c);
    }

    public void paintComponent(Graphics g) {
        int width = this.getSize().width;
        int height = this.getSize().height;

        // Mandamos que pinte la imagen en el panel
        if (this.background != null) {
            g.drawImage(this.background, 0, 0, width, height, null);
        }
    //    if (this.pause != null && is_pause == true) {
    //       g.drawImage(this.pause, 0, 0, width, height, null);
  //  }
        super.paintComponent(g);
        // drawGrid(g);
        for (IGameObject gObj : gObjects) {
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
}
