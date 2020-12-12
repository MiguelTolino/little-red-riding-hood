/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author migue
 */
public class FirstScreen {

    private int n_square, n_flies, n_blossoms;
    private Fly flies[];
    private Blossom blossoms[];

    public FirstScreen(int row, int num, int num2) {
        n_square = row;
        n_flies = num;
        n_blossoms = num2;

        flies = new Fly[n_flies];
        blossoms = new Blossom[n_blossoms];

        for (int i = 0; i < n_flies; i++) {
            Position p1 = new Position((int) ((Math.random() * n_square)), (int) (Math.random() * n_square));
            flies[i] = new Fly(p1, 10, 10);
        }
        for (int i = 0; i < n_blossoms; i++) {
            Position p2 = new Position((int) (Math.random() * n_square), (int) (Math.random() * n_square));
            blossoms[i] = new Blossom(p2, (int)(Math.random() * 20), 10);
        }
    }

    public void setObjs(ConcurrentLinkedQueue<IGameObject> gObjs) {
        for (int i = 0; i < n_flies; i++) {
            gObjs.add(flies[i]);
        }
        for (int i = 0; i < n_blossoms; i++) {
            gObjs.add(blossoms[i]);
        }

    }

}
