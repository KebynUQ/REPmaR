package co.edu.uniquindio.eventos.patterns.creational;

import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Recinto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventoFactoryTest {

    @Test
    void debeCrearEventoConCategoriaConcierto() {
        validarCategoria("Concierto");
    }

    @Test
    void debeCrearEventoConCategoriaTeatro() {
        validarCategoria("Teatro");
    }

    @Test
    void debeCrearEventoConCategoriaConferencia() {
        validarCategoria("Conferencia");
    }

    @Test
    void debeCrearEventoConCategoriaGeneral() {
        validarCategoria("General");
    }

    private void validarCategoria(String categoria) {
        Recinto recinto = new Recinto("R1", "Recinto", "Dir", "Armenia");
        Evento evento = EventoFactory.crearEvento("E1", "Evento", categoria, "Desc",
                "Armenia", LocalDateTime.now().plusDays(1), recinto);

        assertNotNull(evento);
        assertEquals(categoria, evento.getCategoria());
    }
}
