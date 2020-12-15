/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.icons;

import game.Bee;
import game.Blossom;
import game.Fly;
import game.IGameObject;
import game.RidingHood_1;
import game.RidingHood_2;
import game.RidingHood_3;
import game.Spider;
import views.IAWTGameView;
import views.IViewFactory;

/**
 *
 * @author juanangel
 */
public class IconsFactory implements IViewFactory {

    @Override
    public IAWTGameView getView(IGameObject gObj, int length) throws Exception {

        IAWTGameView view = null;

        if (gObj instanceof Fly) {
            view = new VIcon(gObj, "src/main/resources/images/fly.png", length);
        } else if (gObj instanceof Bee) {
            view = new VIcon(gObj, "src/main/resources/images/bee.png", length);
        } else if (gObj instanceof RidingHood_2 || gObj instanceof RidingHood_3) {
            view = new VIcon(gObj, "src/main/resources/images/red-riding-hood.png", length);
        } else if (gObj instanceof Spider) {
            view = new VIcon(gObj, "src/main/resources/images/spider.png", length);
        } else if (gObj instanceof Blossom) {
            if (gObj.getValue() < 10) {
                view = new VIcon(gObj, "src/main/resources/images/dandelion.png", length);
            } else {
                view = new VIcon(gObj, "src/main/resources/images/clover.png", length);
            }
        }
        return view;
    }

}
