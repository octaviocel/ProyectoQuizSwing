/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class TEincisos extends JPanel {

    private final String[] CORRECTO = {"FALSE", "TRUE"};
    private JLabel lblcaption;
    private JTextField inciso;
    private JComboBox combo;
    //private JRadioButton rb;

    public TEincisos(String in) {
        super.setLayout(new BorderLayout());
        lblcaption = new JLabel(in);
        inciso = new JTextField(15);
        combo = new JComboBox(CORRECTO);
        //rb= new JRadioButton();
        super.add(lblcaption, BorderLayout.WEST);
        super.add(inciso, BorderLayout.CENTER);
        super.add(combo, BorderLayout.EAST);
        //super.add(rb,BorderLayout.EAST);
    }

    public void clear() {
        inciso.setText("");
    }

    public String getInciso() {
        return inciso.getText();
    }

    public Boolean getRespuesta() {
        if (combo.getSelectedIndex() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setInciso(String inciso) {
        this.inciso.setText(inciso);
    }

    public void setRespuesta(Boolean verdad) {
        if (verdad == true) {
            combo.setSelectedIndex(1);
        }else{
            combo.setSelectedIndex(0);
        }

    }
}
