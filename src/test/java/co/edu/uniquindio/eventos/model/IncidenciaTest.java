package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.model.enums.EstadoIncidencia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidenciaTest {

    @Test
    void debeCrearYRegistrarIncidencia() {
        Incidencia incidencia = new Incidencia("I1", "Silla", "Dano en silla", "Evento");
        incidencia.registrar();

        assertEquals(EstadoIncidencia.ABIERTA, incidencia.getEstado());
    }

    @Test
    void debeConsultarDetalle() {
        Incidencia incidencia = new Incidencia("I1", "Silla", "Dano en silla", "Evento");

        assertTrue(incidencia.consultarDetalle().contains("Silla"));
    }
}
