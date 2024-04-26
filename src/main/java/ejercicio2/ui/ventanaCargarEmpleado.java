package ejercicio2.ui;

import ejercicio2.model.SistemaEmpleados;

import javax.swing.*;

public class ventanaCargarEmpleado extends JFrame {
    private final SistemaEmpleados sistema;
    private JPanel mainPanel;
    private JLabel LblNombre;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JLabel lblApellido;
    private JLabel lblFecNac;
    private JTextField fecNacTextField;
    private JLabel lblCorreoElectronico;
    private JTextField correoElectronicoTextField;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JFrame origen;

    public ventanaCargarEmpleado(SistemaEmpleados sistemaEmpleados) {
        sistema = sistemaEmpleados;
        cargarComponentes();

    }

    private void cargarComponentes() {
        this.setContentPane(this.mainPanel);
        this.pack();
        this.setVisible(true);
        btnConfirmar.addActionListener(e -> {
            actionPerformedBtnConfirmar();
        });
        btnCancelar.addActionListener(e -> {
            actionPerformedBtnCancelar();
        });

    }

    private void actionPerformedBtnCancelar() {
        origen.setVisible(true);
        dispose();
    }

    private void actionPerformedBtnConfirmar() {
        var nombre = nombreTextField.getText();
        var apellido = apellidoTextField.getText();
        var fechaNac = fecNacTextField.getText();
        var correo = correoElectronicoTextField.getText();
        try {
            sistema.cargarEmpleado(nombre, apellido, fechaNac, correo);
            origen.setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            this.setVisible(true);
        }
    }

    void modificarOrigen(JFrame ventanaAnterior) {
        this.origen = ventanaAnterior;
    }


}
