package co.edu.uniquindio.eventos.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdministradorTest {

    @Test
    void debeHeredaDeUsuario() {
        Administrador administrador = new Administrador("A1", "Admin", "admin@uq.edu.co", "300", "EMP-1");

        assertInstanceOf(Usuario.class, administrador);
    }

    @Test
    void debeTenerCodigoEmpleado() {
        Administrador administrador = new Administrador("A1", "Admin", "admin@uq.edu.co", "300", "EMP-1");

        assertEquals("EMP-1", administrador.getCodigoEmpleado());
        assertNotNull(administrador);
    }
}
