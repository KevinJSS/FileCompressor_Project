package compresorArchivos;

import ListaEnlazada.Lista;
import archivos.AdministradorArchivos;
import excepcionesPersonalizadas.ExcepcionArbol;
import excepcionesPersonalizadas.ExcepcionArchivos;
import excepcionesPersonalizadas.ExceptionLista;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import nodo.Nodo;

/**
 * En esta clase está el árbol de Huffman donde se mantienen los métodos 
 * necesarios para realizar la compresión de archivos. Además utiliza 
 * otras clases para hacerlo, por ejemplo, AdministradorArchivos o Lista
 *
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class ArbolHuffman {

    private Nodo raiz;
    private Lista listaNodos = new Lista();
    private HashMap<Character, Integer> mapaCaracteres = new HashMap<>();
    private HashMap<String, String> mapaCodigos = new HashMap<>();

    private static final String SEPARADOR_TABLA = "\u076F";
    private static final String SEPARADOR_HILERA = "\u279B";
    private static final String SEPARADOR_CODIGO = "\u232A";

    /**
     * Método principal para comprimir un archivo. Extrae el contenido del mismo,
     * cuenta la cantidad de veces que se repiten los caracteres y los carga a 
     * nodos con el método cargarNodos, luego genera un árbol con los primeros
     * dos nodos de la lista, genera la hilera de códigos y cifra el texto con 
     * el método cifrarContenido. Al final se guarda, se imprime el texto 
     * comprimido y se devuelven los números relacionados a bits en el 
     * texto original, el comprimido y el porcentaje de compresión.
     * 
     * @param archivo el archivo a comprimir
     * @return los datos de resultados de compresión
     * @throws IOException excepción al escribir el archivo y extraer contenido.
     * @throws ExcepcionArchivos excepción de archivo inválido.
     * @throws ExcepcionArbol excepción si el archivo es muy pequeño
     */
    public String comprimirArchivo(File archivo) throws IOException, ExcepcionArchivos, ExcepcionArbol {
        String contenidoArchivo = AdministradorArchivos.extraerContenido(archivo);
        cargarNodos(contenidoArchivo);

        raiz = generarArbol();
        String hileraCodigos = generarHileraCodigos(raiz, "");
        String textoComprimido = cifrarContenido(contenidoArchivo, hileraCodigos.split(SEPARADOR_HILERA));

        AdministradorArchivos.guardarComo(hileraCodigos + SEPARADOR_TABLA + textoComprimido);
        System.out.println("\nCONTENIDO COMPRIMIDO\n" + textoComprimido);
        return resultadosCompresion(contenidoArchivo);
    }

    /**
     * Método principal para descomprimir un archivo. Extrae el contenido del mismo,
     * genera un mapa de códigos. Se descomprime al sustituir los códigos que coincidan
     * del mapa con el caracter apropiado. Al final se guarda, y se imprime el texto 
     * descomprimido.
     * 
     * @param archivo el archivo a descomprimir
     * @throws IOException excepción al escribir el archivo y extraer contenido.
     * @throws ExcepcionArchivos excepción de archivo inválido.
     * @throws ExcepcionArbol excepción si el archivo es muy pequeño
     */
    public void descomprimirArchivo(File archivo) throws IOException, ExcepcionArchivos, ExcepcionArbol {
        String[] infoArchivoComprimido = AdministradorArchivos.extraerContenido(archivo).split(SEPARADOR_TABLA);        
        generarMapaCodigos(infoArchivoComprimido[0].split(SEPARADOR_HILERA), 1, 0);

        //Descifrar el contenido del archivo
        String codigoTemp = "";
        String textoDescomprimido = "";

        for (char digito : infoArchivoComprimido[1].toCharArray()) {
            codigoTemp += (digito + "");

            if (mapaCodigos.get(codigoTemp) != null && mapaCodigos.get(codigoTemp).length() == 1) {
                textoDescomprimido += mapaCodigos.get(codigoTemp);
                codigoTemp = "";
            }
        }

        AdministradorArchivos.guardarComo(textoDescomprimido);
        System.out.println("\nCONTENIDO DESCOMPRIMIDO\n" + textoDescomprimido);
    }

    /**
     * Método que imprime el contenido comprimido en un archivo dado, además de
     * devolver un String con el mapa de códigos usando el método generarMapaCodigos
     * 
     * @param ArchivoComprimido el archivo dado
     * @return el mapa de códigos generado
     * @throws FileNotFoundException excepción de archivo inválido
     * @throws ExcepcionArchivos excepción si el archivo es menor a dos caracteres
     * @throws ExcepcionArbol excepción si el archivo es muy pequeño
     */
    public String getTableroCodigos(File ArchivoComprimido) throws FileNotFoundException, ExcepcionArchivos, ExcepcionArbol {
        String[] infoArchivoComprimido = AdministradorArchivos.extraerContenido(ArchivoComprimido).split(SEPARADOR_TABLA);
        System.out.println("\nCONTENIDO DEL ARCHIVO (COMPRIMIDO)\n" + infoArchivoComprimido[1]);
        return generarMapaCodigos(infoArchivoComprimido[0].split(SEPARADOR_HILERA), 0, 1);
    }

    /**
     * Método que cuenta la cantidad de veces que se repiten los caracteres de
     * un String dado, los agrega a un HashMap de caracteres y repetición, y 
     * finalmente almacena todos estos en una lista.
     * 
     * @param contenidoArchivo el contenidoa analizar
     */
    private void cargarNodos(String contenidoArchivo) {
        mapaCaracteres.clear();
        
        //Cuenta la cantidad de repeticiones de un caracter
        for (char caracter : contenidoArchivo.toCharArray()) {
            if (!mapaCaracteres.containsKey(caracter)) {
                mapaCaracteres.put(caracter, 1);
            } else {
                mapaCaracteres.put(caracter, mapaCaracteres.get(caracter) + 1);
            }
        }

        //Carga los nodos a lista
        mapaCaracteres.entrySet().forEach(entry -> {
            listaNodos.insertar(new Nodo(entry.getKey(), entry.getValue()));
        });
    }

    /**
     * Método para remover los dos primeros nodos de la lista y generar un árbol.
     * @return el primer nodo en caso de solo quedar uno en la lista.
     */
    private Nodo generarArbol() {
        Nodo nodo1 = null, nodo2 = null;
        while (true) {
            try {
                nodo1 = listaNodos.removerInicio();
                nodo2 = listaNodos.removerInicio();
                listaNodos.insertar(crearArbol(nodo1, nodo2));

            } catch (ExceptionLista ex) {
                return nodo1;
            }
        }
    }

    /**
     * Método recursivo que genera la hilera de códigos. Si se avanza la izquierda
     * se agrega un 0 al código, a la derecha se agrega un 1, y al llegar a un nodo
     * que contiene un char se agrega el mismo, los separadores correspondientes y
     * se continúa con el proceso.
     * 
     * @param nodo un nodo del cual iniciar el análisis
     * @param codigo la hilera de códigos que se tiene
     * @return la hilera de códigos
     */
    private String generarHileraCodigos(Nodo nodo, String codigo) {
        if (nodo != null) {
            if (nodo.getCaracter() == null) {
                return generarHileraCodigos(nodo.getIzquierdo(), (codigo + "0"))
                        + generarHileraCodigos(nodo.getDerecho(), (codigo + "1"));
            }
            return nodo.getCaracter() + SEPARADOR_CODIGO + codigo + SEPARADOR_HILERA
                    + generarHileraCodigos(nodo.getIzquierdo(), (codigo + "0"))
                    + generarHileraCodigos(nodo.getDerecho(), (codigo + "1"));
        }
        return "";
    }

    /**
     * Carga datos al mapa de códigos con el método generarMapaCodigos, y luego
     * realiza el cifrado sustituyendo los caracteres en el texto por sus 
     * respectivos códigos.
     * 
     * @param contenido el String a cifrar
     * @param tablaCodigos usada para cargar datos al mapa de codigos
     * @return el texto cifrado
     * @throws ExcepcionArbol excepción si el archivo es muy pequeño
     */
    private String cifrarContenido(String contenido, String[] tablaCodigos) throws ExcepcionArbol {
        //Cargar datos al mapa de codigos
        generarMapaCodigos(tablaCodigos, 0, 1);

        //Generar cifrado
        String contenidoCrifrado = "";
        for (char caracter : contenido.toCharArray()) {
            contenidoCrifrado += mapaCodigos.get(caracter + "");
        }
        return contenidoCrifrado;
    }

    /**
     * Método para generar un mapa de códigos ordenado en base a la tabla de códigos 
     * dada, y devolver un String con el mismo en un formato entendible y adecuado.
     * 
     * @param tablaCodigos la tabla de códigos a a usar
     * @param primero el puntero primero, para la primera parte de un código
     * @param segundo el puntero segundo, para la segunda parte de un código
     * @return el mapa de códigos ordenado final
     * @throws ExcepcionArbol excepción si el archivo es muy pequeño
     */
    private String generarMapaCodigos(String[] tablaCodigos, int primero, int segundo) throws ExcepcionArbol {
        if (tablaCodigos.length == 1) {
            throw new ExcepcionArbol("El archivo no posee la extensi\u00F3n m\u00EDnima requerida para ser comprimido");
        }
        mapaCodigos.clear();

        //Cargando los valores "Caracter/Codigo" o viceversa
        String tableroCodigos = "TABLERO DE CODIFICACION";
        for (String codigo : tablaCodigos) {
            String[] caracterCodigo = codigo.split(SEPARADOR_CODIGO);
            mapaCodigos.put(caracterCodigo[primero], caracterCodigo[segundo]);

            tableroCodigos += "\n\"" + caracterCodigo[primero] + "\": " + caracterCodigo[segundo];
        }
        return tableroCodigos;
    }

    /**
     * Método para crear un árbol con dos nodos dados. El nodo1 se vuelve el hijo
     * izquierdo, el 2 el derecho, y la raíz es la suma de las repeticiones en ambos nodos.
     * @param nodo1 un nodo dado, se vuelve el hijo izquierdo
     * @param nodo2 un nodo dado, se vuelve el hijo derecho
     * @return el nodo raíz
     */
    private Nodo crearArbol(Nodo nodo1, Nodo nodo2) {
        Nodo raizTemporal = new Nodo(null, (nodo1.getRepeticiones() + nodo2.getRepeticiones()));
        raizTemporal.setIzquierdo(nodo1);
        raizTemporal.setDerecho(nodo2);
        return raizTemporal;
    }

    /**
     * Método para calcular y devolver los números relacionados a bits en el 
     * texto original, el comprimido y el porcentaje de compresión.
     * 
     * @param contenidoArchivo el contenido del archivo original
     * @return los resultados finales
     */
    private String resultadosCompresion(String contenidoArchivo) {
        int sumatoriaBits = 0;
        int bitsOriginal = (contenidoArchivo.length() * 8);

        //Calculando sumatoria de bits
        for (Map.Entry<Character, Integer> entry : mapaCaracteres.entrySet()) {
            sumatoriaBits += mapaCodigos.get(String.valueOf(entry.getKey())).length() * entry.getValue();
        }

        String resultados = "RESULTADOS DE LA COMPRESION\n"
                + "\n\u269D Bits texto original: " + bitsOriginal
                + "\n\u269D Bits texto comprimido: " + sumatoriaBits
                + "\n\u269D Porcentaje de compresi\u00F3n: "
                + (double) (100 - (sumatoriaBits * 100 / bitsOriginal)) + "%";
        return resultados;
    }
}
