package co.edu.uniquindio.eventos.patterns.behavioral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrategyTest {

    @Test
    void pagoTarjetaDebeValidarMontos() {
        MetodoPago metodo = new PagoTarjeta();
        assertTrue(metodo.procesar(1000));
        assertFalse(metodo.procesar(0));
    }

    @Test
    void pagoPseDebeValidarMontos() {
        MetodoPago metodo = new PagoPSE();
        assertTrue(metodo.procesar(1000));
        assertFalse(metodo.procesar(-1));
    }

    @Test
    void pagoNequiDebeValidarMontos() {
        MetodoPago metodo = new PagoNequi();
        assertTrue(metodo.procesar(1000));
        assertFalse(metodo.procesar(0));
    }
}
