/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.ManualGame.ICON;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author migue
 */
public class Screen<T extends IGameObject, K extends IGameObject> {

    Block b[] = new Block[2];

    public int getN_square() {
        return n_square;
    }

    public void setN_square(int n_square) {
        this.n_square = n_square;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public ArrayList<T> getL1() {
        return l1;
    }

    public void setL1(ArrayList<T> l1) {
        this.l1 = l1;
    }

    public ArrayList<K> getL2() {
        return l2;
    }

    public void setL2(ArrayList<K> l2) {
        this.l2 = l2;
    }

    private int n_square, n1, n2;
    private ArrayList<T> l1;
    private ArrayList<K> l2;

    public Screen(int row, int n1, int n2, T obj1, K obj2) {
        n_square = row;
        this.n1 = n1;
        this.n2 = n2;

        l1 = new ArrayList<T>();
        l2 = new ArrayList<K>();

    }

    public Block generateBlock() {
        Block b;
        int x, y;
        x = (int) (Math.random() * n_square);
        y = (int) (Math.random() * n_square);
        Position p = new Position(x, y);
        b = new Block(p);
        return (b);
    }

    public void setObjs(ConcurrentLinkedQueue<IGameObject> gObjs) {

        b[0] = generateBlock();
        b[1] = generateBlock();
        for (int i = 0; i < n1; i++) {
            gObjs.add(l1.get(i));
            for (int j = 0; j < b.length; j++) {
                if (b[j].getPosition().isEqual(l1.get(i).getPosition())) {
                    b[j] = generateBlock();
                }
            }
        }
        for (int i = 0; i < n2; i++) {
            gObjs.add(l2.get(i));
            for (int j = 0; j < b.length; j++) {
                if (b[j].getPosition().isEqual(l2.get(i).getPosition())) {
                    b[j] = generateBlock();
                }
            }
            gObjs.add(b[0]);
            gObjs.add(b[1]);
        }
    }

}
