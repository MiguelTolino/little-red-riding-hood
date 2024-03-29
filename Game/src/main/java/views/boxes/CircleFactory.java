/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.boxes;

import game.Bee;
import game.Blossom;
import game.Fly;
import game.IGameObject;
import game.RidingHood_1;
import game.Spider;
import java.awt.Color;
import views.IAWTGameView;
import views.IViewFactory;

/**
 *
 * @author juanangel
 */
public class CircleFactory implements IViewFactory {
    
    @Override
    public IAWTGameView getView(IGameObject gObj, int length) throws Exception {
        
        IAWTGameView view = new VNumberedCircle(gObj, length);
                
        if (gObj instanceof Fly){
           view = new VNumberedCircle(gObj, length, Color.gray, "Fly");
        }
        else if (gObj instanceof Bee){
           view = new VNumberedCircle(gObj, length, Color.YELLOW, "Bee"); 
        }
        else if (gObj instanceof Spider){
           view = new VNumberedCircle(gObj, length, Color.black, "Spider");
        }
        else if (gObj instanceof RidingHood_1){
           view = new VNumberedCircle(gObj, length, Color.red, "Hood");
        } 
        else if (gObj instanceof Blossom){
            if (gObj.getValue() < 10){
                view = new VNumberedCircle(gObj, length, Color.pink, "DLion");
            }
            else {
                view = new VNumberedCircle(gObj, length, Color.GREEN, "Clover");
            }
        }
        return view; 
    }
    
}
