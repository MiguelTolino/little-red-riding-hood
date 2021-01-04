/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package async;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AsyncClient extends JFrame {

    public static final int CANVAS_WIDTH = 240;
    public static final int BOX_SIZE = 20;

    static int fcounter = 0;
    int row, col;

    AsyncClientPanel client[] = new AsyncClientPanel[4];

    ExecutorService fileLoader;

    JMenuBar barraMenu;
    JMenu mSeleccionEjecutor;
    JMenu mFixedPool;
    JMenuItem itThreeThreads, itFiveThreads;
    JMenuItem itFlexiblePool, itSingleThreaded, itResizablePool;

    public AsyncClient() throws Exception {

        super("Cliente v3");

        //Set Menu bar
        setMenuBar();
        getContentPane().setLayout(new GridLayout(1, 4));

        for (int i = 0; i < client.length; i++) {
            client[i] = new AsyncClientPanel(i, CANVAS_WIDTH, BOX_SIZE);
            getContentPane().add(client[i]);
        }
        setSize(4 * (CANVAS_WIDTH + 20), 2 * (CANVAS_WIDTH + 40));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(this.getFocusableWindowState());
        this.setFocusable(true);

        // Set default loader for panels.
        ExecutorService fileLoader = Executors.newSingleThreadExecutor();
        for (AsyncClientPanel panel : client) {
            try {
                panel.setLoader(fileLoader);
            } catch (InterruptedException ex) {
                Logger.getLogger(AsyncClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void setMenuBar() {
        barraMenu = new JMenuBar();
        this.setJMenuBar(barraMenu);

        mSeleccionEjecutor = new JMenu("Load File Executor ...");
        mFixedPool = new JMenu("Fixed Pool");
        itThreeThreads = new JMenuItem("Three Threads");
        itFiveThreads = new JMenuItem("Five Threads");
        itFlexiblePool = new JMenuItem("Flexible Pools");
        itSingleThreaded = new JMenuItem("Single Threaded");
        itResizablePool = new JMenuItem("Resizable Pool");

        barraMenu.add(mSeleccionEjecutor);

        mSeleccionEjecutor.add(itSingleThreaded);
        mSeleccionEjecutor.add(mFixedPool);
        mSeleccionEjecutor.add(itResizablePool);

        mFixedPool.add(itThreeThreads);
        mFixedPool.add(itFiveThreads);
        
        ThreadAction ae = new ThreadAction();
        itSingleThreaded.addActionListener(ae);
        itThreeThreads.addActionListener(ae);
        itFiveThreads.addActionListener(ae);
        itResizablePool.addActionListener(ae);
    }
    
    class ThreadAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == itSingleThreaded)
                fileLoader = Executors.newSingleThreadExecutor();
            if (e.getSource() == itThreeThreads)
                fileLoader = Executors.newFixedThreadPool(3);
            if (e.getSource() == itFiveThreads)
                fileLoader = Executors.newFixedThreadPool(5);
            if (e.getSource() == itResizablePool)
                fileLoader = Executors.newCachedThreadPool();
            //System.out.println();
        }
        
    }

    public static void main(String[] args) throws Exception {
        AsyncClient gui = new AsyncClient();
    }
}
