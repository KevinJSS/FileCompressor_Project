package excepcionesPersonalizadas;

/**
 * En esta clase se manejan las excepciones relacionadas con la lista.
 * Hereda de la clase Exception
 *
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class ExceptionLista extends Exception {
    
    /**
     * Método que envía una excepción con un mensaje dado.
     * 
     * @param mensaje el mensaje a mostrar
     */
    public ExceptionLista(String mensaje) {
        super(mensaje);
    }
}
