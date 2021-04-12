/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import excepciones.ArchivoInvalidoException;
import excepciones.CargarInvalidoException;
import excepciones.GuardarArchivoException;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class Cuestionario implements Serializable {

    private ArrayList<Pregunta> quiz;
    private Integer aciertos;

    public Cuestionario() throws CargarInvalidoException, ArchivoInvalidoException {
        cargar();
        this.aciertos = 0;
    }

    public Boolean addPregunta(Pregunta pregunta) {
        return quiz.add(pregunta);
    }

    public Double calificarExamen(Integer preguntas){
        Double calificacion = (double) (aciertos*100)/(preguntas);        
        //JOptionPane.showMessageDialog(null,String.format("Obtuviste %d de %d preguntas\nTu Calificacion es %.2f", aciertos,preguntas,calificacion) );
        this.aciertos=0;
          
        return calificacion;
    }
    public void verificaPreguntaSolo(Integer pregunta, Integer seleccion) {
        if (quiz.get(pregunta).getCorrecto(seleccion)) {
            //System.out.println("Respuesta correcta");
            this.aciertos++;
        } 
    }

    public void verificaPreguntaMulti(Integer pregunta, ArrayList<Integer> selecciones) {
        if(quiz.get(pregunta).getCorrecto(selecciones)){
            //System.out.println("Respuesta correcta");
            this.aciertos++;
        }
    }

    public Integer getLargo() {
        return quiz.size();
    }

    public Pregunta getPregunta(int index) {
        return quiz.get(index);
    }

    public void eliminarPregunta(int index) {
        quiz.remove(index);
    }

    public void guardarQuiz() throws GuardarArchivoException {
        try {
            File file = new File("quiz.dat");
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(output);

            writer.writeObject(quiz);

            writer.close();
            output.close();
        } catch (IOException ex) {
            throw new GuardarArchivoException("Hubo un error al guardar");
        }
    }

    private void cargar() throws CargarInvalidoException, ArchivoInvalidoException {
        File file = new File("quiz.dat");
        if (file.exists()) {
            try {
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream reader = new ObjectInputStream(input);

                quiz = (ArrayList<Pregunta>) reader.readObject();

                reader.close();
                input.close();
            } catch (IOException ex) {
                throw new CargarInvalidoException("Error al cargar archivo");
            } catch (ClassNotFoundException ex) {
                throw new ArchivoInvalidoException("Intento de abrir Archivo corrupto");
            }

        } else {
            quiz = new ArrayList<>();
        }
    }
}
