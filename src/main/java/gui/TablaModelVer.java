/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Incisos;

/**
 *
 * @author DELL
 */
public class TablaModelVer extends AbstractTableModel {

    private ArrayList<Incisos> control;

    public TablaModelVer(ArrayList<Incisos> c) {
        this.control = c;
    }

    @Override
    public int getRowCount() {
        return control.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Incisos i= control.get(rowIndex);
        switch(columnIndex){
            case 0:
                return i.getRespuesta();
            case 1:
                return i.getCorrecto();
            default:
                throw new AssertionError(); 
        }
    }
     @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Respuesta";
            case 1:
                return "Correcto/Falso";
            default:
                throw new AssertionError();              
        
        }   
    }

}
