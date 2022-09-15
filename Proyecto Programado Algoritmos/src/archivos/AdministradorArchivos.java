/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import excepcionesPersonalizadas.ExcepcionArchivos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Esta clase se encarga de controlar los métodos relacionados con el
 * manejo de archivos. Utiliza JFileChooser y archivos File para lograrlo. 
 * 
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class AdministradorArchivos {

    private static final JFileChooser selector = new JFileChooser();

    /**
     * Método para obtener y guardar la ruta del archivo seleccionado
     * con JFileChooser. Envía una excepción si no se realiza una selección.
     * 
     * @return la ruta del archivo seleccionado
     * @throws ExcepcionArchivos la excepción si no hay selección
     */
    public static String obtenerRuta() throws ExcepcionArchivos {
        int seleccion = selector.showOpenDialog(null);

        if (seleccion == selector.APPROVE_OPTION) {
            File ruta = selector.getSelectedFile();
            return ruta.getAbsolutePath();
        }
        throw new ExcepcionArchivos("No se seleccion\u00F3 ning\u00FAn archivo."); //excepcion
    }

    /**
     * Extrae el contenido del archivo dado en un String. Almacena por línea, y
     * guarda los cambio de línea. Además envía excepciones en casos de errores.
     * 
     * @param archivo el archivo al cual extraer el contenido
     * @return el conteido del archivo
     * @throws FileNotFoundException excepción de archivo inválido
     * @throws ExcepcionArchivos excepción si el archivo es menor a dos caracteres
     */
    public static String extraerContenido(File archivo) throws FileNotFoundException, ExcepcionArchivos {
        String contenido = "";
        Scanner scanner = new Scanner(archivo);

        while (scanner.hasNext()) {
            contenido += scanner.nextLine();
            //En caso de un "enter"
            if (scanner.hasNextLine()) {
                contenido += "\n";
            }
        }

        if (contenido.length() == 1) {
            throw new ExcepcionArchivos("El archivo debe contener m\u00EDnimo 2 caracteres.");
        }
        return contenido;
    }

    /**
     * Método para guardar un archivo con contenido dado. Se selecciona una ruta
     * para almacenarlo, y se escribe usando el método guardarFichero. Envía una
     * excepción si no se realiza una selección.
     * 
     * @param contenido el contenido a escribir en el archivo.
     * @throws IOException excepción al escribir el archivo.
     * @throws ExcepcionArchivos excepción al seleccionar la ruta.
     */
    public static void guardarComo(String contenido) throws IOException, ExcepcionArchivos {
        int seleccion = selector.showSaveDialog(null);
        if (seleccion == selector.APPROVE_OPTION) {
            selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            File archivo = selector.getSelectedFile();
            if (archivo.exists()) {
                archivo = new File(archivo.getAbsolutePath());
            }
            guardarFichero(contenido, archivo);
        } else {
            throw new ExcepcionArchivos("No se seleccion\u00F3 ning\u00FAn archivo para guadar el contenido.");
        }
    }

    /**
     * Método para escribir contenido en un archivo dado usando FileWriter.
     * 
     * @param contenidoArchivo el contenido a escribir en el archivo.
     * @param archivo el archivo en el cual escribir.
     * @throws IOException excepción al escribir el archivo.
     */
    private static void guardarFichero(String contenidoArchivo, File archivo) throws IOException {
        FileWriter escribir;
        escribir = new FileWriter(archivo, true);
        escribir.write(contenidoArchivo);
        escribir.close();
    }
}
