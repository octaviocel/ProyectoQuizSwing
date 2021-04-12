/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excepciones.SinIncisoException;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class IncisosList extends JPanel {

    private ArrayList<TEincisos> inciso;
    //private ButtonGroup bgGrupo;

    public IncisosList() {
        inciso = new ArrayList<>();
        super.setLayout(new GridLayout(siz(), 1));
    }

    public void limpiar() {
        for (TEincisos tEincisos : inciso) {
            tEincisos.clear();
        }
    }

    public void agregar(Integer a) throws SinIncisoException {
        inciso.clear();
        super.removeAll();
        if (a > 1) {
            for (int i = 0; i < a; i++) {
                TEincisos n = new TEincisos("Inciso " + (i + 1));
                inciso.add(n);
                super.add(n);
                //bgGrupo.add(n.getRadi());
            }
        }else{
            throw new SinIncisoException("Coloque al menos dos incisos");
        }
        
    }

    private Integer siz() {
        return inciso.size();
    }
    
    public ArrayList<TEincisos> getInciso(){
        return inciso;
    }
}
