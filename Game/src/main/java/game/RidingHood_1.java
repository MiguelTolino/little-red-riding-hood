/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public class RidingHood_1 extends AbstractGameObject {

    private Blossom[] blossoms;

    public RidingHood_1() {
    }

    public RidingHood_1(Position position) {
        super(position);
    }

    public RidingHood_1(Position position, int value) {
        super(position, value, 1);
    }

    public RidingHood_1(Position position, int value, int life) {
        super(position, value, life);
    }

    public RidingHood_1(JSONObject obj) {
        super(obj);
    }

    public RidingHood_1(Position position, int value, int life, Blossom[] blossoms) {
        super(position, value, life);
        this.blossoms = blossoms;

    }

    private void moveDiagonal() {
        int x = position.getX() + 1;
        int y = position.getY() + 1;

        position.setX(x);
        position.setY(y);
    }

    private void approachTo(Position p) {

        int x = position.getX();
        int y = position.getY();

        if (x < p.getX()) {
            x++;
        }
        if (x > p.getX()) {
            x--;
        }
        if (y > p.getY()) {
            y--;
        }
        if (y < p.getY()) {
            y++;
        }
        position.setX(x);
        position.setY(y);
    }
    

    public Position moveToNextPosition() {
        Position p;
        for (int i = 0; i < blossoms.length; i++) {
            Blossom b = (Blossom)getClosest(position, blossoms);
            while(!position.isEqual(b.getPosition()))
                this.approachTo(b.getPosition());
        }
        moveDiagonal();
        p = position;
        return (p);
    }
}
