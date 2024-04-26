package ejercicio2.database;


import ejercicio2.model.Empleado;
import ejercicio2.model.PersistenciaEmpleados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersistenciaEmpleadosEnDisco implements PersistenciaEmpleados {
    private final String url;

    public PersistenciaEmpleadosEnDisco(String URL) {
        this.url = Objects.requireNonNull(URL);
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        try {
            Files.write(Paths.get(this.url), empleado.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("No se puedo Inscribir en disco", e);
        }
    }

    @Override
    public List<Empleado> recuperarEmpleado(LocalDate fecha) {
        List<Empleado> empleados = new ArrayList<>();

        try {
            // Leer todas las líneas del archivo
            List<String> lineas = Files.readAllLines(Paths.get(this.url));

            // Iterar sobre cada línea para buscar empleados que cumplan años en la fecha especificada
            for (String linea : lineas) {
                String[] partes = linea.split(", ");
                String nombre = partes[0];
                String apellido = partes[1];
                String fechaNacimiento = partes[2];
                // Si la fecha de nacimiento coincide con la fecha especificada, agregar el empleado a la lista
                if (cumpleAniosEnFecha(fechaNacimiento, fecha.toString())) {
                    String correoElectronico = partes[3];
                    var empleado = new Empleado(nombre, apellido, fechaNacimiento, correoElectronico);
                    empleados.add(empleado);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de empleados", e);
        }

        // Devolver la lista de empleados que cumplen años en la fecha especificada
        return empleados;
    }

}
