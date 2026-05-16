package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.model.enums.EstadoPago;
import co.edu.uniquindio.eventos.patterns.behavioral.PagoTarjeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagoTest {

    @Test
    void debeProcesarPagoValido() {
        Pago pago = new Pago("P1", 50000, new PagoTarjeta());

        boolean resultado = pago.procesarPago();

        assertTrue(resultado);
        assertEquals(EstadoPago.APROBADO, pago.getEstado());
        assertNotNull(pago.getFechaPago());
    }

    @Test
    void debeGenerarComprobante() {
        Pago pago = new Pago("P1", 50000, new PagoTarjeta());
        pago.procesarPago();

        assertFalse(pago.generarComprobante().isBlank());
    }
}
