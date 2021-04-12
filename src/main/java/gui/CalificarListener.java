/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface CalificarListener {
    
    public void calificarPreguntaSoloClick(Integer noPregunta, Integer respuesta);
    public void calificarPreguntaMultiClick(Integer noPregunta, ArrayList<Integer> seleccion);
    public void calificarExamenClick();
}
