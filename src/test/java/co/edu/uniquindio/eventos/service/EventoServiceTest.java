package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.enums.EstadoEvento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventoServiceTest {

    @Test
    void debeAgregarListarFiltrarYCambiarEstadoDeEventos() {
        EventoService service = new EventoService();
        int cantidadInicial = service.listarEventos().size();
        Evento evento = new Evento("ET-" + System.nanoTime(), "Evento Test", "Conferencia", "Desc",
                "Cali", LocalDateTime.now().plusDays(3), TestDataUtil.crearRecinto());

        service.agregarEvento(evento);
        service.publicarEvento(evento);
        service.pausarEvento(evento);
        service.cancelarEvento(evento);

        assertTrue(service.listarEventos().size() >= cantidadInicial + 1);
        assertNotNull(service.filtrarPorCiudad("Cali"));
        assertNotNull(service.filtrarPorCategoria("Conferencia"));
        assertEquals(EstadoEvento.CANCELADO, evento.getEstado());
    }
}
