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

    Blossom[] blossoms;
    int blossomCounter = 0;
    

    RidingHood_1(Position position) {
        super(position);    
    }
    
    RidingHood_1(Position position, int value, int life ) {
        super(position, value, life);    
    }
    
    RidingHood_1(JSONObject jObj) {
        super(jObj);    
    }
     
    RidingHood_1(Position position, int value, int life, Blossom [] blossoms) {
        super(position, value, life);   
        this.blossoms = blossoms;
    }    
    
    /**
     * Cada vez que se invoca se dirige hacia el blossom más cercano, 
     * moviéndose una posición en x y otra en y.
     * Cuando ha pasado por todos los blossoms avanza en diagonal 
     * hacia abajo a las derecha.
     * @return posición en la que se encuentra después de ejecutarse el
     * método.
     */
    @Override
    public Position moveToNextPosition(){
                
        if (blossoms != null && blossoms.length != 0 && blossomCounter < blossoms.length){
                approachTo(blossoms[blossomCounter].position);
                if (position.isEqual(blossoms[blossomCounter].position)){
                    blossomCounter++;
                }
        }
        else if (position.x < 10 && position.y < 10){
             moveDiagonal();
        }
        System.out.println(position);        
        return position;       
    }  
    
    private void moveDiagonal(){
        position.x++;
        position.y++;
    }
    
    private void approachTo(Position p){
        if (position.x != p.x){
            position.x = position.x > p.x? position.x-1:position.x+1;
        }
        if (position.y != p.y){
            position.y = position.y > p.y? position.y-1:position.y+1;
        }
    }    
}
