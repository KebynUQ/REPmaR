package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Compra;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Usuario;
import co.edu.uniquindio.eventos.model.Zona;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompraServiceTest {

    @Test
    void debeCrearAgregarListarYCalcularCompras() {
        CompraService service = new CompraService();
        Usuario usuario = TestDataUtil.crearUsuario();
        Evento evento = TestDataUtil.crearEvento();
        Zona zona = evento.getRecinto().getZonas().get(0);
        Asiento asiento = zona.getAsientos().get(0);
        int cantidadInicial = service.listarCompras().size();

        Compra compra = service.crearCompra(usuario, evento, zona, asiento);

        assertTrue(service.listarCompras().size() >= cantidadInicial + 1);
        assertTrue(service.listarComprasPorUsuario(usuario).contains(compra));
        assertEquals(compra.getTotal(), service.calcularTotalCompra(compra));
    }
}
