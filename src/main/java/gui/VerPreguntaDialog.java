/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Incisos;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class VerPreguntaDialog extends JDialog {

    private JPanel pnlGrande;
    private JPanel pnlTitulo;
    private JLabel lblTitulo;
    private JPanel pnlPregunta;
    private JLabel lblPregunta;
    private JPanel pnlTipo;
    private JLabel lblTipo;

    private TablaModelVer modelTabla;
    private JTable tblPregunta;

    public VerPreguntaDialog(Frame parent, Pregunta pregunta, int noPr, String tip) {
        super(parent, true);
        super.setSize(300, 350);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());

        Pregunta t = pregunta;
        pnlGrande = new JPanel();
        pnlGrande.setLayout(new BorderLayout());
        
        pnlTitulo = new JPanel();
        pnlTitulo.setLayout(new FlowLayout());        
        lblTitulo = new JLabel("Pregunta No." + noPr);
        pnlTitulo.add(lblTitulo);

        pnlPregunta = new JPanel();
        pnlPregunta.setLayout(new FlowLayout());
        lblPregunta = new JLabel(t.getPregunta());
        pnlPregunta.add(lblPregunta);
        
        pnlTipo = new JPanel();
        pnlTipo.setLayout(new FlowLayout());
        lblTipo = new JLabel("Tipo Pregunta: " + t.getTipo());
        pnlTipo.add(lblTipo);
        
        pnlGrande.add(pnlTitulo, BorderLayout.NORTH);
        pnlGrande.add(pnlPregunta, BorderLayout.CENTER);
        pnlGrande.add(pnlTipo, BorderLayout.SOUTH);

        ArrayList<Incisos> i = t.getIncisos();

        modelTabla = new TablaModelVer(i);
        tblPregunta = new JTable(modelTabla);

        super.add(pnlGrande, BorderLayout.NORTH);
        super.add(new JScrollPane(tblPregunta), BorderLayout.CENTER);
    }

}
