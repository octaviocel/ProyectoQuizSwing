/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excepciones.SeleccionaAlgoException;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class ExamenAplicar extends Dialog {

    private JLabel lblPregunta;
    private JPanel pnlRespuestas;

    private JPanel pnlBoton;
    private JButton btnNext;
    private JButton btnSubmit;

    private JRadioButton r1;
    private ButtonGroup group;

    private ArrayList<JCheckBox> box;
    private ArrayList<JRadioButton> radio;

    private CalificarListener listener;

    public ExamenAplicar(Frame parent, Pregunta pregunta, Integer noPregunta, Boolean ultimaPregunta) {
        super(parent, true);
        super.setSize(400, 300);
        super.setTitle("Aplicando Examen");
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());

        lblPregunta = new JLabel(pregunta.getPregunta());
        pnlRespuestas = new JPanel(new GridLayout(pregunta.getIncisos().size(), 1));
        group = new ButtonGroup();
        if (pregunta.getTipoInteger() == 2) {
            radio = new ArrayList<>();
            ini(pregunta);
        } else if (pregunta.getTipoInteger() == 1) {
            box = new ArrayList<>();
            for (int j = 0; j < pregunta.getIncisos().size(); j++) {
                JCheckBox r = new JCheckBox(pregunta.getIncisos().get(j).getRespuesta());
                pnlRespuestas.add(r);
                box.add(r);
            }
        }

        pnlBoton = new JPanel(new BorderLayout());
        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pregunta.getTipoInteger() == 2) {
                    try {
                        //System.out.println(getSeleccion());
                        listener.calificarPreguntaSoloClick(noPregunta, getSeleccion());
                    } catch (SeleccionaAlgoException ex) {
                        JOptionPane.showMessageDialog(
                                ExamenAplicar.this,
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (pregunta.getTipoInteger() == 1) {
                    try {
                        listener.calificarPreguntaMultiClick(noPregunta, selecciionMulti());
                    } catch (SeleccionaAlgoException ex) {
                        JOptionPane.showMessageDialog(
                                ExamenAplicar.this,
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea entregar su examen?",
                        "Examen finalizado",
                        JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == resp) {
                    if (pregunta.getTipoInteger() == 2) {
                        try {
                            //System.out.println(getSeleccion());
                            listener.calificarPreguntaSoloClick(noPregunta, getSeleccion());
                        } catch (SeleccionaAlgoException ex) {
                            JOptionPane.showMessageDialog(
                                    ExamenAplicar.this,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (pregunta.getTipoInteger() == 1) {
                        try {
                            listener.calificarPreguntaMultiClick(noPregunta, selecciionMulti());
                        } catch (SeleccionaAlgoException ex) {
                            JOptionPane.showMessageDialog(
                                    ExamenAplicar.this,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    listener.calificarExamenClick();
                }

            }
        });
        if (ultimaPregunta == false) {
            pnlBoton.add(btnNext, BorderLayout.EAST);
        } else {
            pnlBoton.add(btnSubmit, BorderLayout.EAST);
        }
        super.add(lblPregunta, BorderLayout.NORTH);
        super.add(pnlRespuestas);
        super.add(pnlBoton, BorderLayout.SOUTH);

    }

    public void setListener(CalificarListener lis) {
        this.listener = lis;
    }

    public void ini(Pregunta pregunta) {
        for (int i = 0; i < pregunta.getIncisos().size(); i++) {
            r1 = new JRadioButton(pregunta.getIncisos().get(i).getRespuesta());
            pnlRespuestas.add(r1);
            group.add(r1);
            radio.add(r1);
        }
    }

    public Integer getSeleccion() throws SeleccionaAlgoException {
        int regreso = -1;
        for (int i = 0; i < radio.size(); i++) {
            if (radio.get(i).isSelected()) {
                regreso = i;
            }
        }
        if (regreso != -1) {
            return regreso;
        } else {
            throw new SeleccionaAlgoException("Selecciona una respuesta para pasar a la siguiente pregunta");
        }
    }

    public ArrayList<Integer> selecciionMulti() throws SeleccionaAlgoException {
        ArrayList<Integer> regreso = new ArrayList<>();
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).isSelected()) {
                regreso.add(i);
            }
        }
        if (regreso.isEmpty()) {
            throw new SeleccionaAlgoException("Selecciona una respuesta para pasar a la siguiente pregunta");
        } else {
            return regreso;
        }
    }
}
