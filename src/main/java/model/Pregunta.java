/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import excepciones.IncisoVacioException;
import excepciones.PreguntaVaciaException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Pregunta implements Serializable{
    
    private String pregunta;
    private ArrayList<Incisos> incisos;
    private Integer tipoPregunta; //1 = multirepuesta # 2 = solo una respuesta
    
    public Pregunta(String pregunta, Integer tipoPregunta) throws PreguntaVaciaException{
        this.setPregunta(pregunta);
        this.incisos = new ArrayList();
        this.tipoPregunta= tipoPregunta;
    }
    
    public void setInciso(String inciso, Boolean correcto) throws IncisoVacioException{
        this.incisos.add(new Incisos(inciso,correcto));
    }
    
    public Boolean getCorrecto(Integer inciso){
        return incisos.get(inciso).getCorrecto();
    }
    
    public Boolean getCorrecto(ArrayList<Integer> in){
        int respuestas = 0;
        for (int i = 0; i < incisos.size(); i++) {
            if(incisos.get(i).getCorrecto() == true){
                respuestas++;
            }
        }
        if(respuestas == in.size()){
            for (int i = 0; i < incisos.size(); i++) {
                for (int j = 0; j < in.size(); j++) {
                    if((incisos.get(i).getCorrecto() == true)&&(i==in.get(j))){
                        respuestas--;
                    }
                }
            }
            if(respuestas==0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public void setPregunta(String pregunta) throws PreguntaVaciaException{
        if(!pregunta.isEmpty()){
            this.pregunta= pregunta;
        }else{
            throw new PreguntaVaciaException("Campo pregunta Vacio");
        }
    }
    
    public String getPregunta(){
        return pregunta;
    }
    
    public String getTipo(){
        if(tipoPregunta==1){
            return "Multi-respuesta";
        }else{
            return "Solo una respuesta";
        }
    }
    
    public ArrayList<Incisos> getIncisos(){
        return incisos;
    }
    
    public Integer getTipoInteger(){
        return tipoPregunta;
    }
}
