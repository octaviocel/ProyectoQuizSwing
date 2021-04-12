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
public class SeleccionaAlgoException extends Exception {

    /**
     * Creates a new instance of <code>SeleccionaAlgoException</code> without
     * detail message.
     */
    public SeleccionaAlgoException() {
    }

    /**
     * Constructs an instance of <code>SeleccionaAlgoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SeleccionaAlgoException(String msg) {
        super(msg);
    }
}
