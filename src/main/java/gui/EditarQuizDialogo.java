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
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class EditarQuizDialogo extends JDialog {

    private Pregunta pregunta;

    private TEdit edtPregunta;
    private JPanel pnl;
    
    private JPanel pnlBoton;
    private JButton btnAgregar;
    private JButton btnCancelar;
    
    //private Pregunta repuesto;
    
    private ModificarListener listener;

    public EditarQuizDialogo(Frame parent, Pregunta p, Integer noPregunta) {
        super(parent, true);
        super.setSize(350, 300);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());

        this.pregunta = p;

        edtPregunta = new TEdit("Pregunta: ");
        edtPregunta.setTexto(pregunta.getPregunta());
        
        ArrayList<TEincisos> inc = new ArrayList<>();
        
        pnl = new JPanel();
        pnl.setLayout(new GridLayout(pregunta.getIncisos().size(), 1));
        for (int i = 0; i < pregunta.getIncisos().size(); i++) {
            TEincisos e = new TEincisos("Inciso "+(i+1));
            e.setInciso(pregunta.getIncisos().get(i).getRespuesta());
            e.setRespuesta(pregunta.getIncisos().get(i).getCorrecto());
            pnl.add(e);
            inc.add(e);
        }
        pnlBoton = new JPanel();
        pnlBoton.setLayout(new BorderLayout());
        btnAgregar = new JButton("Modificar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                try {
                    int respuetas=0, falsos=0;
                    Pregunta n = new Pregunta(edtPregunta.getValor(), pregunta.getTipoInteger());
                    for (int i = 0; i < inc.size(); i++) {
                        if(inc.get(i).getRespuesta()==true){
                            respuetas++;
                        }
                        if(inc.get(i).getRespuesta()==false){
                            falsos++;
                        }
                        n.setInciso(inc.get(i).getInciso(), inc.get(i).getRespuesta());
                    }
                    if(pregunta.getTipoInteger()==2){
                        if(respuetas>1){
                            throw new DemasiadosIncisosException("Su pregunta es de una sola respuesta\nNo puede seleccionar multiples");
                        }
                        if(falsos==inc.size()){
                            throw new RespuestaFalsasException("No puedes dejar sin respuesta correcta\n Selecciona una por favor");
                        }
                    }
                    if(pregunta.getTipoInteger()==1){
                        if((falsos==inc.size())||(falsos == (inc.size()-1))){
                            throw new RespuestaFalsasException("Su pregunta es de multiple respuesta\n Selecciona al menos dos por favor");
                        }
                    }
                    listener.modificarClick(n, noPregunta);
                    
                } catch (PreguntaVaciaException | IncisoVacioException | DemasiadosIncisosException | RespuestaFalsasException ex) {
                    JOptionPane.showMessageDialog(
                            EditarQuizDialogo.this,
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
                EditarQuizDialogo.this.setVisible(false);
            }
        });

        pnlBoton.add(btnAgregar, BorderLayout.WEST);
        pnlBoton.add(btnCancelar, BorderLayout.EAST);

        super.add(edtPregunta, BorderLayout.NORTH);
        super.add(pnlBoton, BorderLayout.SOUTH);
        super.add(pnl);
    }

    public void setListener(ModificarListener listener){
        this.listener = listener;
    }
    
    public EditarQuizDialogo(){
        
    }
}
