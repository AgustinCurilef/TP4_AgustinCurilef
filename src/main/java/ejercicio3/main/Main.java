package ejercicio3.main;

import ejercicio3.database.PersistenciaSistemaRadioJDBC;
import ejercicio3.model.DefaulSistemaRadioCompetition;
import ejercicio3.model.SistemaRadioCompetition;
import ejercicio3.ui.VentanaInscripcionRadioCompetition;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SistemaRadioCompetition sistema = new DefaulSistemaRadioCompetition(new PersistenciaSistemaRadioJDBC(), new PersistenciaSistemaRadioJDBC());
                    new VentanaInscripcionRadioCompetition(sistema);
                    //Main.cargarConcursos(sistema); para cargar los concursos una unica vez

                } catch (Exception e) {
// log exception...
                    System.out.println(e);
                }
            }
        });
    }

    private static void cargarConcursos(SistemaRadioCompetition sistema) {
        sistema.generarConcurso(1, "concurso1", LocalDate.of(2024, 1, 1), LocalDate.now());
        sistema.generarConcurso(2, "concurso2", LocalDate.of(2022, 12, 3), LocalDate.of(2023, 1, 3));
        sistema.generarConcurso(3, "concurso3", LocalDate.now(), LocalDate.of(2032, 12, 12));
    }
}