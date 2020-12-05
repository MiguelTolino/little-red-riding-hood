/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import game.Polyline;
import game.Position;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author juanangel
 */
public class FileUtilities {

    /**
     * Crea un directorio si no existe.
     *
     * @param pathname nombre del directorio.
     */
    public static void createDirectory(String pathname) throws SecurityException {

        File directorio = new File(pathname);

        if (!directorio.exists()) {
            System.out.println("crearDirectorio: directorio ha creado en " + pathname);
            directorio.mkdir();
        } else {
            System.out.println("crearDirectorio: directorio ya existe en " + pathname);
        }
    }

    /**
     * Escribe un string en un fichero de texto.
     *
     * @param s
     * @param fichero fiechero destino
     * @throws java.io.FileNotFoundException
     */
    public static void writeToFile(String s, String fichero) throws FileNotFoundException {

        if (s == null || fichero == null) {
            return;
        }

        PrintWriter streamWriter;

        try {
            streamWriter = new PrintWriter(new FileOutputStream(fichero));
            streamWriter.println(s);
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
            throw e;
        }
    }

    /**
     * Escribe un array de jsons en un fichero de texto, cada uno en una línea
     * diferente.
     *
     * @param jsons array de JSONObjects
     * @param fileName fichero destino.
     */
    public static void writeJsonsToFile(JSONObject[] jsons, String fileName) {

        PrintWriter streamWriter;

        // Test arguments.
        if (jsons == null || fileName == null) {
            return;
        }

        // Write each json object in a new line.
        try {
            streamWriter = new PrintWriter(new FileOutputStream(fileName));
            for (JSONObject item : jsons) {
                streamWriter.println(item.toString());
            }
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
        }
    }

    /**
     * Lee objetos json de un fichero de TEXTO en donde han sido previamente
     * guardados usando writeJsonsToFile.
     *
     * @param fileName ruta del fichero
     * @return contenido del fichero
     */
    public static JSONArray readJsonsFromFile(String fileName) throws IOException {

        String line;
        JSONArray jArray = new JSONArray();
        BufferedReader br = null;
        FileReader fr;

        // TO DO ....
        try {
            File archivo = new File(fileName);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        while ((line = br.readLine()) != null) {
            JSONObject obj = new JSONObject(line);
            jArray.put(obj);
        }

        return jArray;
    }

    public static void main(String[] args) {

        // Prueba creación directorrio.
        String dirName = "src/main/resources/jsons";
        createDirectory(dirName);

        // Prueba escritura en fichero.
        // Creamos objetos
        Position p1 = new Position(0, 0);
        Position p2 = new Position(0, 1);
        Position p3 = new Position(1, 1);
        Position p4 = new Position(1, 2);
        Position pA1[] = {p1, p2, p3, p4};
        Position pA2[] = {p4, p3, p2, p1};
        Polyline pl1 = new Polyline(pA1);
        Polyline pl2 = new Polyline(pA2);

        // Los introducimos en un array
        JSONObject jsons[] = {
            p1.toJSONObject(),
            p2.toJSONObject(),
            p3.toJSONObject(),
            p4.toJSONObject(),
            pl1.toJSONObject(),
            pl2.toJSONObject()
        };

        // Prueba escritura: Escribimos los arrays en un fichero
        writeJsonsToFile(jsons, dirName + "/testWriteFile");

        // Prueba Lectura .........................................................
        JSONArray jArray = null;
        try {
            jArray = readJsonsFromFile(dirName + "/testWriteFile");
        } catch (IOException ex) {
            Logger.getLogger(FileUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < jArray.length(); i++) {
            String json = jArray.getJSONObject(i).toString();
            System.out.println(json);
        }
    }

}
