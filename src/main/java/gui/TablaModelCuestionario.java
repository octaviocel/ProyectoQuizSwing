/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controlador.Cuestionario;
import javax.swing.table.AbstractTableModel;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class TablaModelCuestionario extends AbstractTableModel{

    private Cuestionario oControl;
    
    public TablaModelCuestionario(Cuestionario oC){
        this.oControl=oC;
    }
    
    @Override
    public int getRowCount() {
        return oControl.getLargo();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Pregunta p = oControl.getPregunta(rowIndex);
        switch(columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return p.getPregunta();  
            case 2:
                return p.getTipo();
            default:
                throw new AssertionError();          
        
        }  
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No. Pregunta";
            case 1:
                return "Pregunta";
            case 2:
                return "Tipo de pregunta";
            default:
                throw new AssertionError();              
        
        }   
    }
}
