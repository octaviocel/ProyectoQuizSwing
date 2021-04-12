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
public class SinIncisoException extends Exception {

    /**
     * Creates a new instance of <code>SinIncisoException</code> without detail
     * message.
     */
    public SinIncisoException() {
    }

    /**
     * Constructs an instance of <code>SinIncisoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SinIncisoException(String msg) {
        super(msg);
    }
}
