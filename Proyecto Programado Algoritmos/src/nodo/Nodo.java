package nodo;

/**
 * En esta clase se maneja el objeto nodo, el cual formará la lista
 * de caracteres y el árbol de Huffman para comprimir. Almacena un
 * caracter, que se obtiene del texto a comprimir, el número de 
 * repeticiones de ese caracter, y su nodo siguiente en la lista, 
 * e izquierdo y derecho para el árbol.
 * 
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class Nodo implements Comparable<Nodo> {
    
    private Character caracter;
    private Integer repeticiones;
    private Nodo siguiente, izquierdo, derecho;
    
    /**
     * Constructor vacío para inicializar un nodo.
     */
    public Nodo() {}
    
    /**
     * Constructor para inicializar un nodo.
     * 
     * @param caracter es el caracter a almacenar
     * @param repeticiones el número de repeticiones de ese caracter
     */
    public Nodo(Character caracter, Integer repeticiones) {
        this.caracter = caracter;
        this.repeticiones = repeticiones;
    }
    
    /**
     * Método para establecer (set) el atributo caracter del nodo.
     * 
     * @param caracter el valor dado a establecer
     */
    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }
    
    /**
     * Método para establecer (set) el atributo repeticiones del nodo.
     * 
     * @param repeticiones el valor dado a establecer
     */
    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    /**
     * Método para establecer (set) el nodo siguiente del nodo actual.
     * 
     * @param siguiente el nodo a establecer como siguiente
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    /**
     * Método para establecer (set) el nodo hijo izquierdo del nodo actual.
     * 
     * @param izquierdo el nodo a establecer como hijo izquierdo
     */
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    /**
     * Método para establecer (set) el nodo hijo derecho del nodo actual.
     * 
     * @param derecho el nodo a establecer como hijo derecho
     */
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
    
    /**
     * Método para obtener (get) el atributo caracter del nodo.
     * 
     * @return el atributo caracter.
     */
    public Character getCaracter() {
        return caracter;
    }
    
    /**
     * Método para obtener (get) el atributo repeticiones del nodo.
     * 
     * @return el atributo repeticiones.
     */
    public Integer getRepeticiones() {
        return repeticiones;
    }

    /**
     * Método para obtener (get) el atributo siguiente del nodo.
     * 
     * @return el atributo siguiente.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    /**
     * Método para obtener (get) el atributo izquierdo del nodo.
     * 
     * @return el atributo izquierdo.
     */
    public Nodo getIzquierdo() {
        return izquierdo;
    }
    
    /**
     * Método para obtener (get) el atributo derecho del nodo.
     * 
     * @return el atributo derecho.
     */
    public Nodo getDerecho() {
        return derecho;
    }

    /**
     * Retorna la información pertinete del nodo, en este caso, el
     * caracter y sus repeticiones.
     * 
     * @return la información pertinete del nodo.
     */
    @Override
    public String toString() {
        return "\"" + caracter + "\"" + "(" + repeticiones + ")";
    }
    
    /**
     * Método para comparar la cantidad de repeticiones entre varios nodos, 
     * para así ordenarlos correctamente.
     * 
     * @param o otro nodo a comparar con el actual
     * @return un entero, dependiendo del resultado de la comparación. 0 si
     * es igual, 1 si las del nodo actual son mayores al las del recibido por
     * parámetro, y -1 en caso contrario.
     */
    @Override
    public int compareTo(Nodo o) {
        return repeticiones.compareTo(o.repeticiones);
    }
}
