package ejercicio3.model;

import javax.swing.*;
import java.time.LocalDate;

public interface SistemaRadioCompetition {
    void inscribirParticipante(String nombre, String apellido, String dni, String mail, String telefono, int idconcurso);

    void listarConcurso(JComboBox<String> comboBox);//obtiene los concursos disponibles

    void generarConcurso(int idConcurso, String nombre, LocalDate fechaInicio, LocalDate fechaFin);// crea concursos

    int obtenerIDPorNombre(Object nombre);
}
