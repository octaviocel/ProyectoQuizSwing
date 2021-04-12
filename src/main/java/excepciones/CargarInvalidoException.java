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
public class CargarInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>CargarInvalidoException</code> without
     * detail message.
     */
    public CargarInvalidoException() {
    }

    /**
     * Constructs an instance of <code>CargarInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CargarInvalidoException(String msg) {
        super(msg);
    }
}
