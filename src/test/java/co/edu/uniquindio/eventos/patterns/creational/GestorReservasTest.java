package co.edu.uniquindio.eventos.patterns.creational;

import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.enums.EstadoAsiento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorReservasTest {

    @Test
    void debeRetornarLaMismaInstancia() {
        assertSame(GestorReservas.getInstancia(), GestorReservas.getInstancia());
    }

    @Test
    void debeReservarAsientoDisponible() {
        Asiento asiento = new Asiento("A1", "A", 1);

        boolean resultado = GestorReservas.getInstancia().reservarAsiento(asiento);

        assertTrue(resultado);
        assertEquals(EstadoAsiento.RESERVADO, asiento.getEstado());
    }

    @Test
    void noDebeReservarAsientoBloqueadoOVendido() {
        Asiento bloqueado = new Asiento("A1", "A", 1);
        bloqueado.bloquear();
        Asiento vendido = new Asiento("A2", "A", 2);
        vendido.vender();

        assertFalse(GestorReservas.getInstancia().reservarAsiento(bloqueado));
        assertFalse(GestorReservas.getInstancia().reservarAsiento(vendido));
    }

    @Test
    void debeLiberarReserva() {
        Asiento asiento = new Asiento("A1", "A", 1);
        asiento.reservar();

        GestorReservas.getInstancia().liberarAsiento(asiento);

        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
    }
}
