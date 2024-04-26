package ejercicio2.model;

public interface SistemaEmpleados {
    void cargarEmpleado(String nombre, String apellido, String fechaNacimiento, String correoElectronico);

    void enviarSaludos(ProveedorFechas proveedorFechas);
}