package ejercicio1.ui;

import ejercicio1.model.SistemaParticipantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaAgregarParticipante extends JFrame {
    private final SistemaParticipantes unSistema;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;

    public VentanaAgregarParticipante(SistemaParticipantes sistemaParticipantes) {
        unSistema = sistemaParticipantes;
        cargarComponentesUI();
    }

    private void cargarComponentesUI() {
        setTitle("Add Participant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(e -> {
            actionPerformed();
        });
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void actionPerformed() {
        try {
            unSistema.altaParticipante(nombre.getText(), telefono.getText(), region.getText());
            dispose();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e);
            this.setVisible(true);
        }


    }


}

