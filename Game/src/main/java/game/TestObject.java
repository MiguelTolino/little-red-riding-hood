/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author migue
 */
public class TestObject {
    
    public static void testToJSON(AbstractGameObject o1, AbstractGameObject o2) {
        System.out.println(o1.toJSONObject().toString());
        System.out.println(o2.toJSONObject().toString());
        
    }

    public static void main(String[] args) {

        Bee b1 = new Bee(new Position(20, 20), 3, 1);
        Spider sp1 = new Spider(new Position(20, 20), 5, 3);
        
        testToJSON(b1, sp1);
       
            
    }

}
