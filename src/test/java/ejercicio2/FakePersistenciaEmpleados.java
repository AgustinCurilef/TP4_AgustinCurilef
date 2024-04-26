package ejercicio2;

import ejercicio2.model.Empleado;
import ejercicio2.model.PersistenciaEmpleados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakePersistenciaEmpleados implements PersistenciaEmpleados {
    private final List<Empleado> empleados = new ArrayList<>();
    String content = "";

    public boolean StartsWich(String registro) {
        return content.startsWith(registro);
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public List<Empleado> recuperarEmpleado(LocalDate fecha) {
        List<Empleado> empleadosCumpleaños = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (cumpleAniosEnFecha(empleado.fechaNacieminto(), fecha.toString())) {
                empleadosCumpleaños.add(empleado);
                content += empleado.toString();
            }

        }
        return empleadosCumpleaños;
    }

}
