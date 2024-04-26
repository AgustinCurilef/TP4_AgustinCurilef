package ejercicio2.ui;

import ejercicio2.model.ProveedorFechas;
import ejercicio2.model.SistemaEmpleados;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final SistemaEmpleados sistemaEmpleados;
    private final ProveedorFechas proveedorFechas;
    private JPanel principalPanel;
    private JLabel lblBienvenida;
    private JButton cargarEmpleadoButton;
    private JButton enviarSaludosButton;
    private JButton salirButton;

    public VentanaPrincipal(SistemaEmpleados sistemaEmpleados, ProveedorFechas proveedor) {
        this.sistemaEmpleados = sistemaEmpleados;
        this.proveedorFechas = proveedor;
        cargarCompontentes();
    }

    private void cargarCompontentes() {
        this.setContentPane(this.principalPanel);
        this.pack();
        this.setVisible(true);

        cargarEmpleadoButton.addActionListener(e -> {
            actionPerformedCargarEmpleado();
        });
        enviarSaludosButton.addActionListener(e -> {
            actionPerformedEnviarSaludos();
        });
        salirButton.addActionListener(e -> {
            actionPerformedSalir();
        });
    }

    private void actionPerformedSalir() {
        this.dispose();
    }

    private void actionPerformedEnviarSaludos() {
        try {
            sistemaEmpleados.enviarSaludos(proveedorFechas);
            JOptionPane.showMessageDialog(null, "Se ha enviado la notifiacion con exito");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "No se pudo enviar la notificacion");
        }
    }

    private void actionPerformedCargarEmpleado() {
        var ventana = new ventanaCargarEmpleado(sistemaEmpleados);
        ventana.modificarOrigen(this);
        this.setVisible(false);
    }
}
