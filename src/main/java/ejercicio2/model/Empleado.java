package ejercicio2.model;

public record Empleado(String nombre, String apellido, String fechaNacieminto, String correoElectronico) {
    @Override
    public String toString() {
        return nombre + ", " + apellido + ", " + fechaNacieminto + ", " + correoElectronico + "\n";
    }
}

