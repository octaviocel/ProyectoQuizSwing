/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import excepciones.IncisoVacioException;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Incisos implements Serializable{
    private String respuesta;
    private Boolean correcto;

    public Incisos(String respuesta, Boolean correcto) throws IncisoVacioException {
        this.setRespuesta(respuesta);
        this.correcto = correcto;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) throws IncisoVacioException {
        if(!respuesta.isEmpty()){
            this.respuesta = respuesta;
        }else{
            throw new IncisoVacioException("Campo Inciso Vacio");
        }
        
    }

    public Boolean getCorrecto() {
        return correcto;
    } 
}
