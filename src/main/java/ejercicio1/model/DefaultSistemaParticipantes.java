package ejercicio1.model;

public class DefaultSistemaParticipantes implements SistemaParticipantes {
    RegistrarParticipante registrarParticipante;


    public DefaultSistemaParticipantes(RegistrarParticipante registrarParticipante) {
        this.registrarParticipante = registrarParticipante;

    }

    public void altaParticipante(String nombre, String telefono, String region) {
        if (validarInformacion(nombre, telefono, region))
            registrarParticipante.registrarParticipante(nombre, telefono, region);
    }


    private boolean validarInformacion(String nombre, String telefono, String region) {
        if (nombre.isEmpty()) {
            throw new RuntimeException("Debe cargar un nombre");
        }
        if (telefono.isEmpty()) {
            throw new RuntimeException("Debe cargar un telefono");
        }
        if (!validarTelefono(telefono)) {
            throw new RuntimeException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
        if (!region.equals("China") && !region.equals("US") && !
                region.equals("Europa")) {
            throw new RuntimeException("Region desconocida. Las conocidas son: China, US, Europa");
        }
        return true;
    }

    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

}
