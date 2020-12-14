/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public class Bee extends AbstractGameObject {
    
    public Bee(){}
    
    public Bee(Position position) {
        super(position);    
    }
      
    public Bee(Position position, int value){
        super(position, value, 1);
    }
    
    public Bee(Position position, int value, int life){
        super(position, value, life);
    }
    
    public Bee(JSONObject obj){
        super(obj);
    }    
    
    public void printBee(){
        System.out.println(this.toJSONObject());
    }
    
    public void moveBee(ConcurrentLinkedQueue<IGameObject> gObjs) {
        
            IGameObject closest = getClosestObject(gObjs);
            if(closest != null) 
                moveToNextPosition(closest.getPosition());
            else {
                if (position.x > 5)
                    position.x++;
                else
                    position.x--;
            }
    }
    
    public void moveToNextPosition(Position pos) {
        if ((int) (Math.random() * 10) < 4)
        {
        if (position.x < pos.getX())
            position.x++;
        if (position.x > pos.getX())
            position.x--;
        if (position.y < pos.getY())
            position.y++;
        if (position.y > pos.getY())
            position.y --;
        }
    }
    
    public IGameObject getClosestObject(ConcurrentLinkedQueue<IGameObject> gObjs) {
        double distance = Double.MAX_VALUE;
        IGameObject closest = null;
        for (IGameObject obj : gObjs) {
            if (obj instanceof Blossom)
            {
                double d = distance(position, obj.getPosition());
                if (distance > d)
                {
                    distance = d;
                    closest = obj;
                }
                if (obj.getPosition().isEqual(position))
                    gObjs.remove(obj);
            }
        }
        return(closest);
            
            
        }
    }
