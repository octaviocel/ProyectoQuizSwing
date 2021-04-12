/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author DELL
 */
public class SinSeleccionException extends Exception {

    /**
     * Creates a new instance of <code>SinSeleccionException</code> without
     * detail message.
     */
    public SinSeleccionException() {
    }

    /**
     * Constructs an instance of <code>SinSeleccionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SinSeleccionException(String msg) {
        super(msg);
    }
}
