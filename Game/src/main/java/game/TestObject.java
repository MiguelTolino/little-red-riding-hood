/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.json.JSONObject;

/**
 *
 * @author migue
 */
public class TestObject {

    public static void testToJSON(AbstractGameObject o1, AbstractGameObject o2) {
        System.out.println(o1.toJSONObject().toString());
        System.out.println(o2.toJSONObject().toString());

    }

    public static void testConstructor() {
        Bee b = new Bee(new Position(5, 5), 3, 5);
        Bee sp = new Bee(new Position(2, 2), 2, 1);
        Bee bsm = new Bee(new Position(1, 0), 0, 7);
        Bee f = new Bee(new Position(0, 0), 6, 6);

        JSONObject[] json = new JSONObject[4];
        json[0] = b.toJSONObject();
        json[1] = sp.toJSONObject();
        json[2] = bsm.toJSONObject();
        json[3] = f.toJSONObject();

        Bee obj_list[] = new Bee[4];
        for (int i = 0; i < 4; i++) {
            obj_list[i] = new Bee(json[i]);
            System.out.println("Obj created in Json: " + json[i].toString());
            System.out.println("Bee created using a Json: " + obj_list[i].toString());
        }
    }

    public static void testDistances() {

        AbstractGameObject gObj[] = new AbstractGameObject[4];
        gObj[0] = new Bee(new Position(5, 5), 3, 5);
        gObj[1] = new Spider(new Position(2, 2), 2, 1);
        gObj[2] = new Blossom(new Position(1, 0), 0, 7);
        gObj[3] = new Fly(new Position(0, 0), 6, 6);

        Bee b1 = new Bee(new Position(7, 7), 7, 1);
        IGameObject b2 = AbstractGameObject.getClosest(b1, gObj);
        System.out.println("B1: " + b1.toString() + "\n" + "B2: " + b2.toString());
        System.out.println("Closest Object: " + AbstractGameObject.getDistance(b1, b2));

        Double distances1[][] = new Double[4][4];
        Double distances2[][] = new Double[4][4];
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                if (i == j) {
                    continue;
                }
                System.out.println("[" + i + ", " + j + "]");
                distances1[i][j] = AbstractGameObject.getDistance(gObj[i], gObj[j]);
                distances2[i][j] = AbstractGameObject.distance(gObj[i].getPosition(), gObj[j].getPosition());
                System.out.println("GetDistance: " + distances1[i][j]);
                System.out.println("Distance: " + distances2[i][j]);
            }

        }
    }

    public static void testDowncasting() {
        AbstractGameObject gObj[] = new AbstractGameObject[4];
        gObj[0] = new Bee(new Position(5, 5), 3, 5);
        gObj[1] = new Spider(new Position(2, 2), 2, 1);
        gObj[2] = new Blossom(new Position(1, 0), 0, 7);
        gObj[3] = new Fly(new Position(0, 0), 6, 6);

        for (AbstractGameObject obj : gObj) {
            obj.printGameObject();
        }
    }

    public static void main(String[] args) {

        Bee b1 = new Bee(new Position(20, 20), 3, 1);
        Spider sp1 = new Spider(new Position(20, 20), 5, 3);

        //testToJSON(b1, sp1);
        //testConstructor();
        //testDistances();
        testDowncasting();

    }

}
