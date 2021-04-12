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
public class IncisoVacioException extends Exception {

    /**
     * Creates a new instance of <code>IncisoVacioException</code> without
     * detail message.
     */
    public IncisoVacioException() {
    }

    /**
     * Constructs an instance of <code>IncisoVacioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IncisoVacioException(String msg) {
        super(msg);
    }
}
