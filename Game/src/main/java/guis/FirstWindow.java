package guis;

import static game.ManualGame.ICON;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * - Crea una ventana visible. - A�ade dos paneles modelados como clases
 * internas. - En uno de ellos coloca una imagen. - En el otro 6 botones
 * colocados seg�n un GridLayout. - Se utilizan diferentes gestores de dise�o.
 *
 *
 * @author TIC-LSI
 */
public class FirstWindow extends JFrame {
    

    PanelGrafica panelGrafica;
    PanelButtons panelBotones;
    int selection;

    public FirstWindow() {

        super("Little Red Riding Hood Game");
        selection = -1;

        // Construimos y a�adimos paneles.
        construirPaneles();

                //Set Icon
        this.setIconImage(new ImageIcon(ICON + "icon.png").getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void construirPaneles() {

        // Creamos los dos paneles.
        panelGrafica = new PanelGrafica();
        panelBotones = new PanelButtons();

        // Creamos bordes y se los a�adimos a los paneles.
        //panelGrafica.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // A�adimos paneles a la ventana (a su contentPane).
        getContentPane().add(panelGrafica);
        panelGrafica.add(panelBotones);
    }

    public static void main(String[] args) {
        FirstWindow gui = new FirstWindow();
    }

    /**
     * Clase interna: Utiliza layout por defecto.
     *
     */
    class PanelGrafica extends JPanel {

        JLabel lb;
        JButton play, game_editor, exit;

        // Creamos la imagen a partir de un fichero.
        String path = "src/main/resources/images/caperucita_roja.jpg";
        String icon_play = "src/main/resources/images/play.png";
        ImageIcon imi = new ImageIcon(path);
        Image miImagen = imi.getImage();
        int height = imi.getIconHeight();
        int width = imi.getIconWidth();

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(miImagen, 0, 0, this);
        }
    }

    class PanelButtons extends JPanel implements ActionListener {

        JButton play, game_editor, exit;
        ImageIcon icon;

        public PanelButtons() {

            icon = new ImageIcon("main/resources/images/play.png", "Play Icon");

            setLayout(new GridLayout(3, 1));
            play = new JButton("Play", new ImageIcon("src/main/resources/images/play3.png"));
            play.setHorizontalAlignment(SwingConstants.LEFT);
            game_editor = new JButton("Game Editor", new ImageIcon("src/main/resources/images/edit.png"));
            game_editor.setHorizontalAlignment(SwingConstants.LEFT);
            exit = new JButton("Exit Game", new ImageIcon("src/main/resources/images/exitgame.png"));
            exit.setHorizontalAlignment(SwingConstants.LEFT);

            add(play);
            add(game_editor);
            add(exit);
            play.addActionListener(this);
            game_editor.addActionListener(this);
            exit.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Play")) {
                String options[] = {"Manual mode", "Automatic mode"};
                selection = JOptionPane.showOptionDialog(null, "Choose Game Mode", "Game Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (b.getText().equals("Game Editor")) {
                selection = 2;
                //dispose();
            }
            if (b.getText().equals("Exit Game")) {
                System.exit(0);
            }
            System.out.println("Selection : " + selection);

        }

    }

    public int getSelection() {
        return selection;
    }
}
