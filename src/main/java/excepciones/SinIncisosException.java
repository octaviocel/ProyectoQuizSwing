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
public class SinIncisosException extends Exception {

    /**
     * Creates a new instance of <code>SinIncisosException</code> without detail
     * message.
     */
    public SinIncisosException() {
    }

    /**
     * Constructs an instance of <code>SinIncisosException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SinIncisosException(String msg) {
        super(msg);
    }
}
