/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controlador.Cuestionario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author DELL
 */
public class PrincipalFrame extends JFrame {

    private AlumnoFrameDialog frameAlum;
    private MaestroFrame frameMaes;
    private Cuestionario controlador;
    private ExamenAplicar exam;

    private JButton btnMaestro;
    private JButton btnAlumno;
    private JButton btnSalir;

    private JPanel pnlBotones;
    private JButton btnTeacher;
    private JButton btnStudent;
    private JButton btnExit;

    public PrincipalFrame() {
        super("Examen");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(230, 450);
        super.setLocationRelativeTo(null);

        frameMaes = new MaestroFrame();
        frameAlum = new AlumnoFrameDialog(this);
        controlador = frameMaes.getCuestionario();

        pnlBotones = new JPanel(new BorderLayout());
        JPanel pnl = new JPanel(new FlowLayout());
        JLabel lbl = new JLabel("Maestro");
        pnl.add(lbl);
        btnTeacher = new JButton();
        btnTeacher.setBounds(100, 100, 100, 100);
        ImageIcon profe = new ImageIcon("profesor.png");
        Icon i = new ImageIcon(profe.getImage().getScaledInstance(btnTeacher.getWidth(), btnTeacher.getHeight(), Image.SCALE_DEFAULT));
        btnTeacher.setIcon(i);
        pnl.add(btnTeacher);
        pnlBotones.add(pnl, BorderLayout.NORTH);

        JPanel pnl1 = new JPanel(new FlowLayout());
        JLabel lbl1 = new JLabel("Alumno");
        pnl1.add(lbl1);
        btnStudent = new JButton();
        btnStudent.setBounds(100, 100, 100, 100);
        ImageIcon stu = new ImageIcon("alumni.png");
        Icon i1 = new ImageIcon(stu.getImage().getScaledInstance(btnStudent.getWidth(), btnStudent.getHeight(), Image.SCALE_DEFAULT));
        btnStudent.setIcon(i1);
        pnl1.add(btnStudent);
        pnlBotones.add(pnl1, BorderLayout.CENTER);

        JPanel pnl2 = new JPanel(new FlowLayout());
        pnl2.setSize(50, 50);
        //JLabel lbl2 = new JLabel("Exit");
        //pnl2.add(lbl2);
        btnExit = new JButton();
        btnExit.setBounds(50, 50, 50, 50);
        ImageIcon exit = new ImageIcon("salida.png");
        Icon i2 = new ImageIcon(exit.getImage().getScaledInstance(btnStudent.getWidth(), btnStudent.getHeight(), Image.SCALE_DEFAULT));
        btnExit.setIcon(i2);
        pnl2.add(btnExit);
        pnlBotones.add(pnl2, BorderLayout.SOUTH);

        frameMaes.setListener(new RegresarListener() {
            @Override
            public void regresarAlMenu() {
                PrincipalFrame.this.setVisible(true);
            }
        });

        frameAlum.setListener(new IniciarExamenClick() {
            @Override
            public void iniciarExamen(String nombre, String matricula) {

                frameAlum.setVisible(false);
                int pregunta = 0;
                Boolean ultimaPregunta = false;
                while (pregunta != controlador.getLargo()) {
                    if (pregunta == (controlador.getLargo() - 1)) {
                        ultimaPregunta = true;
                    }
                    exam = new ExamenAplicar(PrincipalFrame.this, controlador.getPregunta(pregunta), pregunta, ultimaPregunta);
                    exam.setListener(new CalificarListener() {
                        @Override
                        public void calificarPreguntaSoloClick(Integer noPregunta, Integer respuesta) {
                            controlador.verificaPreguntaSolo(noPregunta, respuesta);
                            exam.setVisible(false);
                        }

                        @Override
                        public void calificarPreguntaMultiClick(Integer noPregunta, ArrayList<Integer> seleccion) {
                            controlador.verificaPreguntaMulti(noPregunta, seleccion);
                            exam.setVisible(false);
                        }

                        @Override
                        public void calificarExamenClick() {
                            exam.setVisible(false);
                            
                            JOptionPane.showMessageDialog(
                                    PrincipalFrame.this,
                                    String.format("Nombre %s\nMatricula %s\nTu Calificacion es %.2f", 
                                            nombre, 
                                            matricula,controlador.calificarExamen(controlador.getLargo())) );
                            frameAlum.limpiar();
                            PrincipalFrame.super.setVisible(true);
                        }
                    });
                    exam.setVisible(true);
                    pregunta++;
                }

            }

            @Override
            public void regresar() {
                PrincipalFrame.this.setVisible(true);
            }
        });

        //super.setJMenuBar(makeMenu());
        btnTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalFrame.super.setVisible(false);
                frameMaes.setVisible(true);
            }
        });
        btnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador.getLargo() != 0) {
                    PrincipalFrame.super.setVisible(false);
                    frameAlum.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,
                            "No se te ha asignado un examen",
                            "Examen sin asignar",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        super.add(pnlBotones);

        super.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalFrame();
            }
        });
    }
}
