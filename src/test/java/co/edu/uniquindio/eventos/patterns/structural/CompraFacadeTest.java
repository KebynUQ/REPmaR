package co.edu.uniquindio.eventos.patterns.structural;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Compra;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Usuario;
import co.edu.uniquindio.eventos.model.Zona;
import co.edu.uniquindio.eventos.model.enums.EstadoCompra;
import co.edu.uniquindio.eventos.patterns.behavioral.PagoTarjeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompraFacadeTest {

    @Test
    void debeCrearPagarConfirmarYCancelarCompra() {
        CompraFacade facade = new CompraFacade();
        Usuario usuario = TestDataUtil.crearUsuario();
        Evento evento = TestDataUtil.crearEvento();
        Zona zona = evento.getRecinto().getZonas().get(0);
        Asiento asiento = zona.getAsientos().get(0);

        Compra compra = facade.crearCompra(usuario, evento, zona, asiento);
        facade.agregarServicio(compra, new ServicioVIP(new ServicioBase()));

        assertEquals(1, compra.getEntradas().size());
        assertTrue(facade.pagarCompra(compra, new PagoTarjeta()));

        facade.confirmarCompra(compra);
        assertEquals(EstadoCompra.CONFIRMADA, compra.getEstado());

        compra.cancelar();
        assertEquals(EstadoCompra.CANCELADA, compra.getEstado());
    }
}
