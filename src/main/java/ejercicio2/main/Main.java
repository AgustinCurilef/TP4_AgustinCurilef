package ejercicio2.main;

import ejercicio2.database.CorreoElectronico;
import ejercicio2.database.PersistenciaEmpleadosEnDisco;
import ejercicio2.model.DefaultSistemaEmpleados;
import ejercicio2.model.ProveedorFechas;
import ejercicio2.model.SistemaEmpleados;
import ejercicio2.ui.VentanaPrincipal;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SistemaEmpleados sistemaEmpleados = new DefaultSistemaEmpleados(new PersistenciaEmpleadosEnDisco("src/main/resources/empleados.txt"), "recursos.humanos@gmail.com", new CorreoElectronico("fd5c2b624ae696", "78b2ddb2667cc9", "sandbox.smtp.mailtrap.io"));
        ProveedorFechas proveedorFechas = new ProveedorFechas() {
            @Override
            public LocalDate fecha() {
                return LocalDate.now();
            }
        };
        new VentanaPrincipal(sistemaEmpleados, proveedorFechas);

    }
}
