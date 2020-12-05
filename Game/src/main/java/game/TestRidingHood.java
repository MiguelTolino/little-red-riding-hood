/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import common.FileUtilities;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public class TestRidingHood {
    
    // Práctica 3. Ejercicio 2.1
    /**
     * Crea dos objetos RidingHoog.
     * Comprueba paso a JSON y constructor con Jobjeto JSON
     */
    public static void testConstructorAndToJson(){
        
        RidingHood_1 r1 = new RidingHood_1(new Position(3, 3), 2, 5);
        RidingHood_1 r2 = new RidingHood_1(new Position(2, 2), 1, 1);
        
        System.out.println(r1.toJSONObject().toString());
        System.out.println(r2.toJSONObject().toString());
        
        System.out.println("testConstructorAndToJson");
        
    }
    
    /**
     * Se ejecuta el método moveToNextPosition hasta que se alcanzan todos los targets o
     * se realizan n llamadas.
     * @param gObj  objetos en el que se realizan las invocaciones
     * @param targets objetos a alcanzar
     * @param  n número de llamadas al método
     * @return posiciones por las que ha pasado el objeto
     */
    public static Position [] testMoveToNextPosition(IGameObject gObj, Blossom targets[], int n){
        
        System.out.println("testMoveToNextPosition");
        
        // Array para guardar posiciones trayectoria objeto RidingHood
        Position trace[] = new Position[n];
        // Invocamos moveToNextPosition sobre el último objeto
        // RidingHood creado un número de veces sudiciente para
        // pasar por todos los blossoms.
        // Guardamos las posiciones en trace.
        for (int i = 0; i < trace.length; i++){
            gObj.moveToNextPosition();
            trace[i] = new Position(gObj.getPosition());
        }
        System.out.println("-----------------------------------------------");

        return trace;
    }
    
    /**
     * Guarda un array de posiciones en un fichero, las lee del fichero y las muestra 
     * por consola.
     * @param fileName
     * @param trace 
     */
    public static void testSaveAndLoad(String fileName, Position [] trace) {
        
        System.out.println("testSaveAndLoad");
        
        JSONObject [] jObjs = new JSONObject[trace.length];
        // Guardamos las posiciones por las que ha pasado el objeto RidingHood 
        // en formato JSON en un fichero.
        for (int i = 0; i <  trace.length; i++)
            jObjs[i] = new JSONObject(trace[i]);
        FileUtilities.writeJsonsToFile(jObjs, fileName);
        // TO DO
         
        // Leer posiciones guardadas en el fichero anterior y mostrarlas
        // en consola.
        try {
        FileUtilities.readJsonsFromFile(fileName);
        }
        catch (IOException io) {
            io.printStackTrace();
        }
        // TO DO.
        
        System.out.println("-----------------------------------------------");
    }
    
    
    public static void main(String [] args){
        
        testConstructorAndToJson();

        // Crear array de Blossoms
        Blossom blossoms[] = {new Blossom(new Position(1,1)),
                       new Blossom(new Position(6,1)),
                       new Blossom(new Position(3,3)),
                       new Blossom(new Position(6,6)),
                       new Blossom(new Position(1,6)),
                       new Blossom(new Position(1,1)),
                       new Blossom(new Position(1,4))
                      };        
        // Crear nuevo objeto RidingHood pasándole el array de blossoms.
        RidingHood_1 r3 = new RidingHood_1(new Position(1, 1), 3, 2, blossoms);
        
        Position [] traces = testMoveToNextPosition(r3, blossoms, 20);
        for (Position p: traces)
            System.out.println(p.toString());
        FileUtilities.createDirectory("src/main/resources/tests");
        //Invocar testSaveAndLoad sobre el directorio creado
        testSaveAndLoad("src/main/resources/jsons/traces", traces);
    }       
}
