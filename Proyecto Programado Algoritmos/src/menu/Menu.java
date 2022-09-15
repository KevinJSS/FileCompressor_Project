package Menu;

import compresorArchivos.ArbolHuffman;
import archivos.AdministradorArchivos;
import excepcionesPersonalizadas.ExcepcionArbol;
import excepcionesPersonalizadas.ExcepcionArchivos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * En esta clase se maneja el menú, donde se interactúa directamente con
 * el usuario. Se utilizan las demás clases para cumplir con la funcionalidad.
 *
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class Menu {

    /**
     * Método para correr el programa. En él se almacena todo el menú, y se 
     * interactúa con el ususario y utilizan métodos de otras clases.
     * 
     * @param args los argumentos de la línea de comando
     */
    public static void main(String[] args) {

        int opcion = 0;
        ArbolHuffman arbol = new ArbolHuffman();
        File archivo = null, archivoComprimido = null;

        do {
            opcion = PanelMensajes.solicitarEntero("COMPRESION DE ARCHIVOS HUFFMAN\n"
                    + "\n1) Cargar archivo"
                    + "\n2) Comprimir archivo"
                    + "\n3) Abrir archivo comprimido"
                    + "\n4) Descomprimir archivo"
                    + "\n5) Salir");

            switch (opcion) {
                case 1: //CARGAR ARCHIVO
                    try {
                        archivo = new File(AdministradorArchivos.obtenerRuta());
                        
                        PanelMensajes.mostrarMensaje("Archivo cargado correctamente.");
                        System.out.println("\nCONTENIDO DEL ARCHIVO\n" + AdministradorArchivos.extraerContenido(archivo));
                    } catch (ExcepcionArchivos | FileNotFoundException ex) {
                        PanelMensajes.mostrarMensaje(ex.getMessage());
                    }
                    break;


                case 2: //COMPRIMIR ARCHIVO
                    if (archivo != null) {
                        try {
                            PanelMensajes.mostrarMensaje(arbol.comprimirArchivo(archivo));
                        } catch (IOException | ExcepcionArchivos | ExcepcionArbol ex) {
                            PanelMensajes.mostrarMensaje(ex.getMessage());
                        }
                    } else {
                        PanelMensajes.mostrarMensaje("Primero debe cargar el archivo a comprimir.");
                    }
                    break;

                case 3: //ABRIR ARCHIVO COMPRIMIDO
                    try {
                        archivoComprimido = new File(AdministradorArchivos.obtenerRuta());

                        PanelMensajes.mostrarMensaje("Archivo cargado correctamente.");
                        PanelMensajes.mostrarPanel(arbol.getTableroCodigos(archivoComprimido));
                    } catch (ExcepcionArchivos | FileNotFoundException | ExcepcionArbol ex) {
                        PanelMensajes.mostrarMensaje(ex.getMessage());
                    }
                break;

                case 4: //DESCOMPRIMIR ARCHIVO
                    if (archivoComprimido != null) {
                        try {
                            arbol.descomprimirArchivo(archivoComprimido);
                        } catch (IOException | ExcepcionArchivos | ExcepcionArbol ex) {
                            PanelMensajes.mostrarMensaje(ex.getMessage());
                        }
                    } else {
                        PanelMensajes.mostrarMensaje("Primero debe cargar el archivo a descomprimir.");
                    }
                    break;

                case 5: //SALIR
                    PanelMensajes.mostrarMensaje("Ejecuci\u00F3n finalizada.");
                    break;

                default:
                    PanelMensajes.mostrarMensaje("Opci\u00F3n incorrecta, intente nuevamente.");
            }
        } while (opcion != 5);
    }
}
