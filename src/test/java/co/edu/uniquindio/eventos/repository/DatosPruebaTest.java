package co.edu.uniquindio.eventos.repository;

import co.edu.uniquindio.eventos.model.Recinto;
import co.edu.uniquindio.eventos.model.Zona;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatosPruebaTest {

    @Test
    void debeCargarDatosBasicos() {
        DatosPrueba datos = DatosPrueba.getInstancia();

        assertTrue(datos.getUsuarios().size() >= 3);
        assertNotNull(datos.getAdministrador());
        assertTrue(datos.getEventos().size() >= 5);
        assertFalse(datos.getRecintos().isEmpty());
        assertNotNull(datos.getCompras());
        assertNotNull(datos.getIncidencias());
    }

    @Test
    void debeTenerZonasYAsientos() {
        DatosPrueba datos = DatosPrueba.getInstancia();
        boolean existeVip = false;
        boolean existePreferencial = false;
        boolean existeGeneral = false;
        boolean hayAsientos = false;

        for (Recinto recinto : datos.getRecintos()) {
            for (Zona zona : recinto.getZonas()) {
                existeVip |= zona.getNombre().equals("VIP");
                existePreferencial |= zona.getNombre().equals("Preferencial");
                existeGeneral |= zona.getNombre().equals("General");
                hayAsientos |= !zona.getAsientos().isEmpty();
            }
        }

        assertTrue(existeVip);
        assertTrue(existePreferencial);
        assertTrue(existeGeneral);
        assertTrue(hayAsientos);
    }
}
