/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excepciones.DemasiadosIncisosException;
import excepciones.IncisoVacioException;
import excepciones.PreguntaVaciaException;
import excepciones.RespuestaFalsasException;
import excepciones.SinIncisoException;
import excepciones.SinIncisosException;
import excepciones.SinSeleccionException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class PreguntasDialog extends JDialog {

    private final String[] COMBO = {" ", "2", "3", "4", "5","6","7","8","9"};

    private TEdit pregunta;
    private IncisosList incisos;

    private JComboBox combo;
    private JPanel pnlBoton;
    private JButton btnAgregar;
    private JButton btnCancelar;
    private JPanel pnlPregunta;
    private JLabel lblIncio;

    private JPanel pnlTipo;
    private JRadioButton rbMulti;
    private JRadioButton rbSolo;
    private ButtonGroup grpBtn;

    private AgregarPreguntaClick listener;

    public PreguntasDialog(Frame parent) {
        super(parent, true);
        super.setSize(300, 400);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());

        pregunta = new TEdit("Pregunta: ");
        combo = new JComboBox(COMBO);
        incisos = new IncisosList();
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    incisos.agregar(combo.getSelectedIndex() + 1);
                } catch (SinIncisoException ex) {
                    JOptionPane.showMessageDialog(
                            PreguntasDialog.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                PreguntasDialog.this.pack();
            }
        });
        pnlTipo = new JPanel();
        pnlTipo.setLayout(new BorderLayout());
        rbMulti = new JRadioButton("Multi-respuesta");
        rbSolo = new JRadioButton("Solo una correcta");
        grpBtn = new ButtonGroup();
        grpBtn.add(rbSolo);
        grpBtn.add(rbMulti);
        pnlTipo.add(rbMulti, BorderLayout.EAST);
        pnlTipo.add(rbSolo, BorderLayout.WEST);

        pnlPregunta = new JPanel();
        pnlPregunta.setLayout(new BorderLayout());
        pnlPregunta.add(pregunta, BorderLayout.NORTH);
        lblIncio = new JLabel("Cuantos incisos tiene tu pregunta");
        pnlPregunta.add(lblIncio, BorderLayout.WEST);
        pnlPregunta.add(combo, BorderLayout.EAST);
        pnlPregunta.add(pnlTipo, BorderLayout.SOUTH);

        pnlBoton = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Pregunta p = new Pregunta(pregunta.getValor(),seleccion());
                    ArrayList<TEincisos> inci = incisos.getInciso();
                    if (inci.isEmpty()) {
                        throw new SinIncisosException("Debe tener al menos dos incisos");                       
                    } else {
                         if (seleccion() == 1) {
                            int d = 0;
                            for (TEincisos tEincisos : inci) {
                                if (tEincisos.getRespuesta() == false) {
                                    d++;
                                }
                                p.setInciso(tEincisos.getInciso(), tEincisos.getRespuesta());
                            }
                            if (d == inci.size()) {
                                throw new RespuestaFalsasException("Porfavor selecciona al menos una respuesta correcta");
                            }
                        } else if (seleccion() == 2) {
                            int f = 0, d = 0;
                            for (TEincisos tEincisos : inci) {
                                if (tEincisos.getRespuesta() == true) {
                                    f++;
                                }
                                if (tEincisos.getRespuesta() == false) {
                                    d++;
                                }
                                p.setInciso(tEincisos.getInciso(), tEincisos.getRespuesta());
                                if (f > 1) {
                                    throw new DemasiadosIncisosException("Selecciono Respuesta unica\nPor favor Selecciona solo una");
                                }
                                if (d == inci.size()) {
                                    throw new RespuestaFalsasException("Porfavor selecciona al menos una respuesta correcta");
                                }
                            }
                        }
                        listener.agregarClick(p);
                    }
                } catch (PreguntaVaciaException | IncisoVacioException | SinSeleccionException | DemasiadosIncisosException | RespuestaFalsasException | SinIncisosException ex) {
                    JOptionPane.showMessageDialog(
                            PreguntasDialog.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }                
            }
        });
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreguntasDialog.this.setVisible(false);
            }
        });
        pnlBoton.setLayout(new FlowLayout());
        pnlBoton.add(btnAgregar);
        pnlBoton.add(btnCancelar);

        super.add(pnlPregunta, BorderLayout.NORTH);
        super.add(pnlBoton, BorderLayout.SOUTH);
        super.add(incisos);
    }

    public Integer seleccion() throws SinSeleccionException {
        if (rbMulti.isSelected()) {
            return 1;
        } else if (rbSolo.isSelected()) {
            return 2;
        } else {
            throw new SinSeleccionException("Seleccione una opcion de respuestas");
        }
    }

    public void setListener(AgregarPreguntaClick listener) {
        this.listener = listener;
    }
    
    public void clear(){        
        grpBtn.clearSelection();
        pregunta.clear();
        //combo.setSelectedIndex(0);
        incisos.limpiar();
    }
}
