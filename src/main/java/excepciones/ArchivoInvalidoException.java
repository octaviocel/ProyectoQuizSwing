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
public class ArchivoInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>ArchivoInvalidoException</code> without
     * detail message.
     */
    public ArchivoInvalidoException() {
    }

    /**
     * Constructs an instance of <code>ArchivoInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ArchivoInvalidoException(String msg) {
        super(msg);
    }
}
