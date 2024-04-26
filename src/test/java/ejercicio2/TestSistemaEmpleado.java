package ejercicio2;

import ejercicio2.model.DefaultSistemaEmpleados;
import ejercicio2.model.ProveedorFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestSistemaEmpleado {
    FakePersistenciaEmpleados registroFake = new FakePersistenciaEmpleados();
    FakeNotificacion notificacionFake = new FakeNotificacion();
    ProveedorFechas fechaFake = new ProveedorFechas() {
        @Override
        public LocalDate fecha() {
            return LocalDate.of(2002, 12, 18);
        }
    };
    DefaultSistemaEmpleados sistemaEmpleados = new DefaultSistemaEmpleados(registroFake, "RRHH.empresa@gmail.com", notificacionFake);

    @Test
    public void test1() {
        sistemaEmpleados.cargarEmpleado("agus", "curi", "2002-12-18", "agus@gmail");
        sistemaEmpleados.enviarSaludos(fechaFake);
        Assertions.assertTrue(registroFake.StartsWich("agus, curi, 2002-12-18, agus@gmail"));
    }

    @Test
    public void test2() {
        sistemaEmpleados.cargarEmpleado("agus", "curi", "2002-12-18", "agus@gmail");
        Assertions.assertEquals(1, registroFake.recuperarEmpleado(fechaFake.fecha()).size());
    }


    @Test
    public void test3() {
        sistemaEmpleados.cargarEmpleado("Juan", "Perez", "2002-12-19", "Juan@gmail");
        Assertions.assertEquals(0, registroFake.recuperarEmpleado(fechaFake.fecha()).size());
    }


    @Test
    public void test4() {
        sistemaEmpleados.cargarEmpleado("agus", "curi", "2002-12-18", "agus@gmail");
        sistemaEmpleados.cargarEmpleado("Juan", "Perez", "2002-12-19", "Juan@gmail");
        sistemaEmpleados.cargarEmpleado("tomas", "rojo", "1986-12-18", "tomi@gmail");
        sistemaEmpleados.enviarSaludos(fechaFake);
        Assertions.assertTrue(registroFake.StartsWich("agus, curi, 2002-12-18, agus@gmail\n" +
                "tomas, rojo, 1986-12-18, tomi@gmail"));
    }

    @Test
    public void test5() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("agus", "curi", "2002/12/18", "agus@gmail")
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("agus", "curi", "3109/12/18", "agus@gmail")
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("", "curi", "2002/12/18", "agus@gmail")
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("agus", null, "2002/12/18", "agus@gmail")
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("agus", "curi", null, "agus@gmail")
        );
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sistemaEmpleados.cargarEmpleado("agus", "curi", "2002/12/18", null)
        );
    }


}
