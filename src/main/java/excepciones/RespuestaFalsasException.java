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
public class RespuestaFalsasException extends Exception {

    /**
     * Creates a new instance of <code>RespuestaFalsasException</code> without
     * detail message.
     */
    public RespuestaFalsasException() {
    }

    /**
     * Constructs an instance of <code>RespuestaFalsasException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RespuestaFalsasException(String msg) {
        super(msg);
    }
}
