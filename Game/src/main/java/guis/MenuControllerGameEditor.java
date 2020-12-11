package guis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * - Crea una ventana visible. - A�ade una Barra de Men� que a su vez incluye -
 * Dos men�s, uno de ellos con submen�s. - 2 Botones.
 *
 * @author TIC-LSI
 */
public class MenuControllerGameEditor extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = 5110341197899182147L;

    //JMenuBar barraDelMenu;
    JMenu menu_1, menu_2, menu_3, options;
    JMenuItem item_1, item_2, item_3, item_4, item_5, square_size;
    JButton b1, b2, b3;

    public MenuControllerGameEditor() {
        super();
        construirMenus();
    }

    private void construirMenus() {

        //barraDelMenu = new JMenuBar();
        menu_1 = new JMenu("File");
        menu_2 = new JMenu("View");
        menu_3 = new JMenu("Game");
        options = new JMenu("Options");

        // Creamos entradas de menus y suscribimos la ventana
        // a los eventos producidos en dichas entradas.
        item_1 = new JMenuItem("Save");
        item_1.addActionListener(this);
        item_2 = new JMenuItem("Load");
        item_2.addActionListener(this);
        item_3 = new JMenuItem("Start");
        item_3.addActionListener(this);
        item_4 = new JMenuItem("Stop");
        item_4.addActionListener(this);
        item_5 = new JMenuItem("Game editor");
        item_5.addActionListener(this);
        square_size = new JMenuItem("Square Size(px)");
        square_size.addActionListener(this);

        // Creamos un manejador especifico para los botones.   
        //Manejador m = new Manejador();

        // Creamos los botones y suscribimos el manejador 'm' a los
        // eventos producidos por dichos botones.


        // Creamos el botón b3, un manejador anónimo para el mismo y
        // suscribimos el manejador anónimo a su botón.
        b3 = new JButton("b3");
        b3.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("b3 !!!!!!!!!!!!!!!!!: ");
            }
        });

        // A�adimos elementos a barra de men�.
        this.add(menu_1);
        this.add(menu_2);
        this.add(menu_3);
        this.add(options);

        // A�adimos elementos a men� 1.
        menu_1.add(item_1);
        menu_1.addSeparator();

        // A�adimos elementos a men� 3.
        menu_1.add(item_2);

        // A�adimos elementos a men� 2.              
        menu_3.add(item_3);
        menu_3.addSeparator();
        menu_3.add(item_4);
        menu_3.addSeparator();
        menu_3.add(item_5);
        
        options.add(square_size);

        // Le ponemos un borde a la barra de men� y lo a�adimos a la ventana.
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == item_1) {
            System.out.println("item_1 seleccionado.");
        } else if (evento.getSource() == item_2) {
            System.out.println("item_2 seleccionado.");
        } else if (evento.getSource() == item_3) {
            System.out.println("item_3 seleccionado.");
        } else if (evento.getSource() == item_4) {
            System.out.println("item_4 seleccionado.");
        } else if (evento.getSource() == item_5) {
            System.out.println("item_5 seleccionado.");
        }
    }

}
