package ejercicio2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultSistemaEmpleados implements SistemaEmpleados {
    private final String remitente;
    PersistenciaEmpleados registrosEmpleados;
    Notificacion cuentaRRHH;
    private String asunto;
    private String mensaje;
    private ProveedorFechas fecha;

    public DefaultSistemaEmpleados(PersistenciaEmpleados registrarEmpleado, String remitente, Notificacion notificacion) {
        this.registrosEmpleados = registrarEmpleado;
        this.remitente = remitente;
        cuentaRRHH = notificacion;

    }

    @Override
    public void cargarEmpleado(String nombre, String apellido, String fechaNacimiento, String correoElectronico) {
        if (validarInformacion(nombre, apellido, fechaNacimiento, correoElectronico)) {
            var empleado = new Empleado(nombre, apellido, fechaNacimiento, correoElectronico);
            registrosEmpleados.guardarEmpleado(empleado);
        }
    }

    private boolean validarInformacion(String nombre, String apellido, String fechaNacimiento, String correoElectronico) {
        if (nombre.isEmpty()) {
            throw new RuntimeException("Debe cargar un nombre");
        }
        if (apellido.isEmpty()) {
            throw new RuntimeException("Debe cargar un apellido");
        }
        if (fechaNacimiento.isEmpty()) {
            throw new RuntimeException("Debe cargar un fecha de nacimiento");
        }
        if (!validarFechaNacimiento(fechaNacimiento)) {
            throw new RuntimeException("El fecha de nacimiento debe ingresarse de la siguiente forma: yyyy-mm-dd");
        }
        if (correoElectronico.isEmpty()) {
            throw new RuntimeException("Debe cargar un correo Electrónico");
        }
        return true;
    }

    private boolean validarFechaNacimiento(String fechaNacimiento) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        // Verificar si la fecha cumple con el formato esperado
        if (!fechaNacimiento.matches(regex)) return false;
        // Separar la fecha en año, mes y día
        String[] partesFecha = fechaNacimiento.split("-");
        int año = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]);
        int dia = Integer.parseInt(partesFecha[2]);

        // Verificar si el año es válido (hasta el año actual)
        var añoActual = LocalDate.now().getYear();
        if (año > añoActual) {
            throw new RuntimeException("cargue una fecha valida");
        }// Verificar si el mes es válido (entre 1 y 12)
        if (mes < 1 || mes > 12) throw new RuntimeException("cargue una fecha valida");

        // Verificar si el día es válido (dependiendo del mes)
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // Febrero en año bisiesto
        if (!(mes == 2 && esAñoBisiesto(año) ? dia >= 1 && dia <= 29 : dia >= 1 && dia <= diasPorMes[mes - 1])) {
            throw new RuntimeException("cargue una fecha valida");
        }

        return true;

        // Si pasa todas las validaciones, la fecha es válida

    }

    // Método auxiliar para verificar si un año es bisiesto
    private boolean esAñoBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    public void enviarSaludos(ProveedorFechas proveedorFechas) {
        asunto = "Feliz Cumpleaños";
        mensaje = "Feliz cumpleaños le desea la empresa muchas gracias ";
        List<Empleado> empleados = new ArrayList<>();
        empleados = registrosEmpleados.recuperarEmpleado(proveedorFechas.fecha());
        // separa los empleados por "-"
        for (Empleado empleado : empleados) {
            cuentaRRHH.enviarNotificacion(empleado.correoElectronico(), this.remitente, asunto, mensaje + empleado.nombre() + " " + empleado.apellido());

        }
    }
}

