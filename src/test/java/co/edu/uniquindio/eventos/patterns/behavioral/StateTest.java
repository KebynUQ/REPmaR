package co.edu.uniquindio.eventos.patterns.behavioral;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Compra;
import co.edu.uniquindio.eventos.model.Pago;
import co.edu.uniquindio.eventos.model.enums.EstadoCompra;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateTest {

    @Test
    void debeCambiarDeCreadaAPagadaYConfirmada() {
        Compra compra = new Compra("C1", TestDataUtil.crearUsuario(), TestDataUtil.crearEvento());
        compra.agregarEntrada(TestDataUtil.crearEntrada());

        boolean pagoExitoso = compra.pagar(new Pago("P1", compra.calcularTotal(), new PagoTarjeta()));
        compra.confirmar();

        assertTrue(pagoExitoso);
        assertEquals(EstadoCompra.CONFIRMADA, compra.getEstado());
    }

    @Test
    void debeCancelarYReembolsarSegunLaLogicaActual() {
        Compra compra = new Compra("C1", TestDataUtil.crearUsuario(), TestDataUtil.crearEvento());
        compra.agregarEntrada(TestDataUtil.crearEntrada());
        compra.pagar(new Pago("P1", compra.calcularTotal(), new PagoTarjeta()));
        compra.confirmar();
        compra.cancelar();
        assertEquals(EstadoCompra.CANCELADA, compra.getEstado());

        compra.reembolsar();
        assertEquals(EstadoCompra.REEMBOLSADA, compra.getEstado());
    }
}
