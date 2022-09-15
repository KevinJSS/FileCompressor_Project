package excepcionesPersonalizadas;

/**
 * En esta clase se manejan las excepciones relacionadas con los archivos.
 * Hereda de la clase Exception
 *
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class ExcepcionArchivos extends Exception {
    
    /**
     * Método que envía una excepción con un mensaje dado.
     * 
     * @param mensaje el mensaje a mostrar
     */
    public ExcepcionArchivos(String mensaje) {
        super(mensaje);
    }
}
