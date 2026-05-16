package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.model.enums.EstadoCompra;
import co.edu.uniquindio.eventos.patterns.structural.ServicioVIP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompraTest {

    @Test
    void debeCrearCompraAgregarEntradaYServicio() {
        Usuario usuario = co.edu.uniquindio.eventos.TestDataUtil.crearUsuario();
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        Compra compra = new Compra("C1", usuario, evento);
        Entrada entrada = co.edu.uniquindio.eventos.TestDataUtil.crearEntrada();

        compra.agregarEntrada(entrada);
        compra.agregarServicio(new ServicioVIP(new co.edu.uniquindio.eventos.patterns.structural.ServicioBase()));

        assertEquals(1, compra.getEntradas().size());
        assertEquals(1, compra.getServicios().size());
        assertEquals(85000, compra.calcularTotal());
    }

    @Test
    void debeCancelarCompra() {
        Compra compra = new Compra("C1", co.edu.uniquindio.eventos.TestDataUtil.crearUsuario(),
                co.edu.uniquindio.eventos.TestDataUtil.crearEvento());

        compra.cancelar();

        assertEquals(EstadoCompra.CANCELADA, compra.getEstado());
    }

    @Test
    void debeGenerarComprobante() {
        Compra compra = new Compra("C1", co.edu.uniquindio.eventos.TestDataUtil.crearUsuario(),
                co.edu.uniquindio.eventos.TestDataUtil.crearEvento());

        String comprobante = compra.generarComprobante();

        assertNotNull(comprobante);
        assertFalse(comprobante.isBlank());
    }
}
