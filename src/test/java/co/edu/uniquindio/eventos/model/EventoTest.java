package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.model.enums.EstadoEvento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventoTest {

    @Test
    void debeCrearEventoConDatosBasicos() {
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();

        assertEquals("Evento Prueba", evento.getNombre());
        assertEquals("Concierto", evento.getCategoria());
        assertEquals("Armenia", evento.getCiudad());
    }

    @Test
    void debeCambiarEstadoAPublicado() {
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        evento.publicar();
        assertEquals(EstadoEvento.PUBLICADO, evento.getEstado());
    }

    @Test
    void debeCambiarEstadoAPausado() {
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        evento.pausar();
        assertEquals(EstadoEvento.PAUSADO, evento.getEstado());
    }

    @Test
    void debeCambiarEstadoACancelado() {
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        evento.cancelar();
        assertEquals(EstadoEvento.CANCELADO, evento.getEstado());
    }

    @Test
    void debeCambiarEstadoAFinalizado() {
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        evento.finalizar();
        assertEquals(EstadoEvento.FINALIZADO, evento.getEstado());
    }
}
