/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controlador.Cuestionario;
import excepciones.ArchivoInvalidoException;
import excepciones.CargarInvalidoException;
import excepciones.GuardarArchivoException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Pregunta;

/**
 *
 * @author DELL
 */
public class MaestroFrame extends JFrame {

    private JPanel pnlBoton;
    private JButton btnAgregar;
    private JButton btnListo;
    private PreguntasDialog dlgPreguntas;
    private EditarQuizDialogo dlgEdit;

    private Cuestionario controladorMaestro;
    private TablaModelCuestionario modelTabla;
    private JTable tablePregunta;
    
    private RegresarListener listener;

    public MaestroFrame() {
        super("Maestro");
        super.setSize(800, 350);
        cerrar();
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);

        try {
            controladorMaestro = new Cuestionario();
        } catch (CargarInvalidoException | ArchivoInvalidoException ex) {
            JOptionPane.showMessageDialog(MaestroFrame.this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        modelTabla = new TablaModelCuestionario(controladorMaestro);
        tablePregunta = new JTable(modelTabla);
        dlgPreguntas = new PreguntasDialog(this);
        dlgPreguntas.setListener(new AgregarPreguntaClick() {
            @Override
            public void agregarClick(Pregunta pregunta) {
                controladorMaestro.addPregunta(pregunta);
                modelTabla.fireTableDataChanged();
                dlgPreguntas.setVisible(false);
                dlgPreguntas.clear();
            }
        });


        pnlBoton = new JPanel();
        pnlBoton.setLayout(new BorderLayout());
        btnAgregar = new JButton("Agregar Pregunta");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgPreguntas.setVisible(true);
            }
        });
        btnListo = new JButton("Guardar Examen");
        btnListo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controladorMaestro.guardarQuiz();
                    JOptionPane.showMessageDialog(MaestroFrame.this,
                            "Guardado con exito",
                            "Guardar",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (GuardarArchivoException ex) {
                    JOptionPane.showMessageDialog(MaestroFrame.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tablePregunta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String[] options = {"Ver", "Editar", "Borrar", "Cancelar"};
                    int seleccion = JOptionPane.showOptionDialog(
                            MaestroFrame.this,
                            "Que desea hacer con esta pregunta",
                            "Pregunta", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    switch (seleccion) {
                        case 0:
                            int filaselccionada = tablePregunta.getSelectedRow();
                            VerPreguntaDialog pr = new VerPreguntaDialog(
                                    MaestroFrame.this,
                                    controladorMaestro.getPregunta(filaselccionada),
                                    (Integer) tablePregunta.getValueAt(filaselccionada, 0),
                                    tablePregunta.getValueAt(filaselccionada, 2).toString());
                            pr.setVisible(true);
                            break;
                        case 1:
                            int filas = tablePregunta.getSelectedRow();
                            dlgEdit = new EditarQuizDialogo(
                                    MaestroFrame.this,
                                    controladorMaestro.getPregunta(filas),
                                    (Integer) tablePregunta.getValueAt(filas, 0));
                            dlgEdit.setListener(new ModificarListener() {
                                @Override
                                public void modificarClick(Pregunta pregunta, Integer noPregunta) {                                    
                                    controladorMaestro.eliminarPregunta(noPregunta-1);
                                    controladorMaestro.addPregunta(pregunta);
                                    modelTabla.fireTableDataChanged();
                                    dlgEdit.setVisible(false);
                                }
                            });
                            dlgEdit.setVisible(true);

                            break;
                        case 2:
                            int resp = JOptionPane.showConfirmDialog(
                                    MaestroFrame.this,
                                    "¿Seguro que quiere borrar esta pregunta?",
                                    "Eliminar Pregunta",
                                    JOptionPane.YES_NO_OPTION);
                            if (JOptionPane.OK_OPTION == resp) {
                                int fila = tablePregunta.getSelectedRow();
                                controladorMaestro.eliminarPregunta(fila);
                                modelTabla.fireTableDataChanged();
                                JOptionPane.showMessageDialog(MaestroFrame.this,
                                        "Se ha borrado exitosamente",
                                        "Borrar",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                            break;
                        default:
                            break;
                    }
                }
            }
        });

        pnlBoton.add(btnAgregar, BorderLayout.EAST);
        pnlBoton.add(btnListo, BorderLayout.WEST);

        super.setJMenuBar(makeMenu());
        super.add(new JScrollPane(tablePregunta), BorderLayout.CENTER);
        super.add(pnlBoton, BorderLayout.SOUTH);
    }

    private JMenuBar makeMenu() {
        JMenuBar menu = new JMenuBar();

        JMenu mExamen = new JMenu("Ayuda");
        //JMenuItem miItem = new JMenuItem("Opciones de Examen");

        JMenuItem miAplicar = new JMenuItem("Como Editar y Borrar...");
        miAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        MaestroFrame.this,
                        "Para editar y borrar alguna pregunta\nColocate en la fila de la pregunta y da doble click",
                        "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //mExamen.add(miItem);
        mExamen.addSeparator();
        mExamen.add(miAplicar);

        menu.add(mExamen);
        return menu;
    }
    
    public Cuestionario getCuestionario(){
        return controladorMaestro;
    }
    
    private void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           addWindowListener(new WindowAdapter() {            
                @Override
                public void windowClosing(WindowEvent e){
                    listener.regresarAlMenu();
                }
            });
        } catch (Exception e) {
        }
    }
    public void setListener(RegresarListener listener){
        this.listener= listener;
    }
}
