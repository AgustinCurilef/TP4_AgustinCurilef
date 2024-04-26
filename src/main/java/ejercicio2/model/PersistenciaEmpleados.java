package ejercicio2.model;

import java.time.LocalDate;
import java.util.List;

public interface PersistenciaEmpleados {
    void guardarEmpleado(Empleado empleado);

    List<Empleado> recuperarEmpleado(LocalDate fecha);

    default boolean cumpleAniosEnFecha(String fechaNacimiento, String fechaEspecifica) {
        String[] partes = fechaNacimiento.split("-");
        String[] partes2 = fechaEspecifica.split("-");

        return partes[1].equals(partes2[1]) && partes[2].equals(partes2[2]);
    }
}
