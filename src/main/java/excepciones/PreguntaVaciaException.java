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
public class PreguntaVaciaException extends Exception {

    /**
     * Creates a new instance of <code>PreguntaVaciaException</code> without
     * detail message.
     */
    public PreguntaVaciaException() {
    }

    /**
     * Constructs an instance of <code>PreguntaVaciaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PreguntaVaciaException(String msg) {
        super(msg);
    }
}
