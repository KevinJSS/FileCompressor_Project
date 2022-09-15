
package ListaEnlazada;

import excepcionesPersonalizadas.ExceptionLista;
import nodo.Nodo;

/**
 * Esta clase se encarga del manejo de la lista enlazada que se
 * utiliza para ordenar los nodos con caracteres en la compresión.
 * 
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class Lista { 
    
    private Nodo primero;
    private Nodo ultimo;
    
    /**
     * Método para obtener (get) el atributo primero del nodo.
     * 
     * @return el atributo primero.
     */
    public Nodo getPrimero() {
        return primero;
    }
    
    /**
     * Método para insertar un nodo en la lista. Se inserta ordenado por 
     * el compareTo de la clase Nodo. Si la lista está vacía o el nodo a
     * insertar es el menor en repeticiones, se inserta al inicio con el
     * método adecuado. Si es el que tiene más repeticiones, se agrega al
     * final con otro método, y sino, se hace comparaciones hasta encontrar
     * su lugar y se inserta en el correspondiente.
     * 
     * @param nodo es el nuevo nodo a insertar
     * @return un valor booleano dependiendo de si se realizó el proceso
     * correctamente
     */
    public boolean insertar(Nodo nodo) {
        if (primero == null || nodo.compareTo(primero) <= 0) {
            return insertarInicio(nodo);
        }
        if (nodo.compareTo(ultimo) > 0) {
            return insertarFinal(nodo);
        }
        for (Nodo temp = primero; temp.getSiguiente() != null; temp = temp.getSiguiente()) {
            if (nodo.compareTo(temp.getSiguiente()) <= 0) {
                nodo.setSiguiente(temp.getSiguiente());
                temp.setSiguiente(nodo);
                return true;
            }
        }
        return false;
    }
            
    /**
     * Método para insertar un nodo al inicio de la lista. Si la lista está
     * vacía, se agrega como primero y último, sino, el primero se vuelve
     * el siguiente al nodo agregado.
     * 
     * @param nodo es el nuevo nodo a insertar
     * @return un valor booleano dependiendo de si se realizó el proceso
     * correctamente 
     */
    private boolean insertarInicio(Nodo nodo) {
        if (primero == null) {
            primero = ultimo = nodo;
        } else {
            nodo.setSiguiente(primero);
            primero = nodo;
        }
        return true;
    }
    
    /**
     * Método para insertar un nodo al final de la lista. Si la lista está
     * vacía, se agrega como primero y último, sino, el nodo se vuelve el 
     * siguiente al último.
     * 
     * @param nodo es el nuevo nodo a insertar
     * @return un valor booleano dependiendo de si se realizó el proceso
     * correctamente 
     */
    private boolean insertarFinal(Nodo nodo) {
        if (primero == null) {
            primero = ultimo = nodo;
        } else {
            ultimo.setSiguiente(nodo);
            ultimo = nodo;
        }
        return true;
    }
    
    /**
     * Método para remover un nodo al inicio de la lista. Si no está vacía,
     * se elimina el nodo de la lista, y se retorna al terminar.
     * 
     * @return  el nodo removido, o null si la lista estaba vacía
     * @throws ExceptionLista mensaje personalizado si la lista está vacía
     */
    public Nodo removerInicio() throws ExceptionLista {
        if (primero != null) {
            Nodo temp;
            if (primero == ultimo) {
                temp = primero;
                primero = ultimo = null;
            } else {
                temp = primero;
                primero = primero.getSiguiente();
            }
            return temp;
        }
        throw new ExceptionLista("Lista vac\u00EDa");
    }
    
    /**
     * Devuelve la lista de manera ordenada. Utiliza un índice para marcar cada
     * caracter de la lista, y luego de vuelve su información.
     * 
     * @return la lista de manera ordenada.
     */
    public String imprimir() {
        String output = "";
        int indice = 1;
        for (Nodo temp = primero; temp != null; temp = temp.getSiguiente()) {
            output += (indice++) + ") " + temp + "\n";
        }
        return output;
    }
}
