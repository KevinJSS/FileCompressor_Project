package excepcionesPersonalizadas;

/**
 * En esta clase se manejan las excepciones relacionadas con el árbol.
 * Hereda de la clase Exception
 *
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class ExcepcionArbol extends Exception {
    
    /**
     * Método que envía una excepción con un mensaje dado.
     * 
     * @param mensaje el mensaje a mostrar
     */
    public ExcepcionArbol(String mensaje) {
        super(mensaje);
    }
}
