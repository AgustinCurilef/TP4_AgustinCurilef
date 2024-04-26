package ejercicio1.database;

import ejercicio1.model.RegistrarParticipante;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcRegistrarParticipante implements RegistrarParticipante {
    private String url;
    private String user;
    private String password;


    public JdbcRegistrarParticipante() {
        String url = "jdbc:mysql://localhost:3306/tp4-ejercicio1-objetos2";
        String user = "root";
        String password = "";

    }

    public void registrarParticipante(String nombre, String telefono, String region) {
        java.sql.Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, user, password);
            PreparedStatement st = conexion.prepareStatement("insert into participantes(nombre, telefono, region) values(?,?,?)");
            try {
                st.setString(1, nombre);
                st.setString(2, telefono);
                st.setString(3, region);
                st.executeUpdate();
            } finally {
                st.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexion", e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

