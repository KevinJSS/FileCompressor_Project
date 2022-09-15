package Menu;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Esta clase se encarga del manejo de mensajes al usuario, con JOptionPane.
 * 
 * @author Kevin Alvarado
 * @author Alina Rodriguez
 */
public class PanelMensajes {

    /**
     * Muestra un mensaje dado usando showMessageDialog de JOptionPane.
     * 
     * @param mensaje el mensaje a mostrar
     */
    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Muestra un mensaje dado solicitando un entero usando showInputDialog
     * de JOptionPane, y convierte la hilera recibida en entero.
     * 
     * @param mensaje el mensaje a mostrar
     * @return el entero recibido
     */
    public static int solicitarEntero(String mensaje) {
        return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
    }

    /**
     * Muestra un mensaje dado solicitando una hilera usando showInputDialog
     * de JOptionPane.
     * 
     * @param mensaje el mensaje a mostrar
     * @return el entero recibido
     */
    public static String solicitarHilera(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    /**
     * Muestra un mensaje dado en un panel definido con scroll. Muy útil para 
     * mensajes grandes. Se muestra usando showMessageDialog de JOptionPane.
     * 
     * @param mensaje el mensaje a mostrar
     */
    public static void mostrarPanel(String mensaje) {
        JTextArea textArea = new JTextArea(mensaje);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        JOptionPane.showMessageDialog(null, scrollPane);
    }
}
