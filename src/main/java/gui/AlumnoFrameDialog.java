/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class AlumnoFrameDialog extends JDialog {

    private JPanel pnlTitulo;
    private JLabel lblTitulo;

    private JPanel pnlDatos;
    private TEdit edtNombre;
    private TEdit edtMatricula;

    private JButton btnIniciar;

    private IniciarExamenClick listener;

    public AlumnoFrameDialog(Frame parent) {
        super(parent, true);
        super.setSize(320, 180);
        super.setTitle("Examen");
        cerrar();
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());

        pnlTitulo = new JPanel(new FlowLayout());
        lblTitulo = new JLabel("Bienvenido a tu examen");
        lblTitulo.setFont(new Font("Serif", Font.ITALIC, 18));
        pnlTitulo.add(lblTitulo);

        pnlDatos = new JPanel(new GridLayout(2, 1));

        edtNombre = new TEdit("Nombre completo: ");
        edtMatricula = new TEdit("Matricula: ");

        pnlDatos.add(edtNombre);
        pnlDatos.add(edtMatricula);

        btnIniciar = new JButton("Iniciar Examen");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listener.iniciarExamen(getNombre(), getMatricula());
                } catch (CampoNombreException ex) {
                    JOptionPane.showMessageDialog(AlumnoFrameDialog.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        super.add(pnlTitulo, BorderLayout.NORTH);
        super.add(pnlDatos, BorderLayout.CENTER);
        super.add(btnIniciar, BorderLayout.SOUTH);
    }
    
    public void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {            
                @Override
                public void windowClosing(WindowEvent e){
                    listener.regresar();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListener(IniciarExamenClick listener) {
        this.listener = listener;
    }

    private String getNombre() throws CampoNombreException {
        if (edtNombre.getValor().isEmpty()) {
            throw new CampoNombreException("Por favor introduce tu nombre para comenzar");
        } else {
            return edtNombre.getValor();
        }
    }

    private String getMatricula() throws CampoNombreException {
        if (edtMatricula.getValor().isEmpty()) {
            throw new CampoNombreException("Introduce tu matricula");
        } else {
            return edtMatricula.getValor();
        }
    }
    
    public void limpiar(){
        edtNombre.clear();
        edtMatricula.clear();
    }
}
