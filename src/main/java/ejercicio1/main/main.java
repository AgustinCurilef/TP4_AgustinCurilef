package ejercicio1.main;

import ejercicio1.database.JdbcRegistrarParticipante;
import ejercicio1.model.DefaultSistemaParticipantes;
import ejercicio1.ui.VentanaAgregarParticipante;

import java.awt.*;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    var sistemaParticipante = new DefaultSistemaParticipantes(new JdbcRegistrarParticipante());
                    new VentanaAgregarParticipante(sistemaParticipante);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}