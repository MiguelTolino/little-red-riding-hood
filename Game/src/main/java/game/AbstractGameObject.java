/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import common.IToJsonObject;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public abstract class AbstractGameObject implements IGameObject, IToJsonObject {

    protected Position position;
    int value;
    int lifes = 1;
    int mode = 0;

    public AbstractGameObject() {
        position = new Position();
    }

    public AbstractGameObject(Position position) {
        this.position = position;
    }

    public AbstractGameObject(Position position, int value, int life) {
        this(position);
        this.value = value;
        this.lifes = life;
    }

    public AbstractGameObject(JSONObject obj) {

        this.lifes = obj.getInt("lifes");
//        this.position = positionPosition (obj.get("position"));
        this.value = obj.getInt("value");
        //this.mode = obj.getInt("mode");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", "
                + position
                + ", value: " + value + ", lifes: " + lifes + ", mode: " + mode;
    }

    @Override
    public JSONObject toJSONObject() {
        return (new JSONObject(this));
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position moveToNextPosition() {
        return position;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getLifes() {
        return lifes;
    }

    @Override
    public void incLifes(int value) {
        lifes += value;
    }

    @Override
    public void setGameMode(int mode) {
        this.mode = mode;
    }

    public static double distance(Position p1, Position p2) {
        double distance;
        distance = Math.sqrt(Math.pow((double)(p2.getX() - p1.getX()), 2) + Math.pow((double)(p2.getY() - p1.getY()), 2));
        return (distance);
    }

    public static double getDistance(IGameObject jObj1, IGameObject jObj2) {
        double distance = distance(jObj1.getPosition(), jObj2.getPosition());
        return distance;
    }

    public static IGameObject getClosest(Position p, IGameObject jObjs[]) {
        int obj = 0;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < jObjs.length ; i++) {
            double d = distance(p, jObjs[i].getPosition());
            if(d < distance) {
                distance = d;
                obj = i;
            }
        }
        if (distance == Double.MAX_VALUE)
            return null;
        return (jObjs[obj]);
    }

    public static IGameObject getClosest(IGameObject jObj, IGameObject jObjs[]) {
        IGameObject gObj = getClosest(jObj.getPosition(), jObjs);
        return gObj;
    }
    
    public void printGameObject() {
        System.out.println(this.toJSONObject().toString());
    }
}
