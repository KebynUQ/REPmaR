package co.edu.uniquindio.eventos.patterns.creational;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Compra;
import co.edu.uniquindio.eventos.model.Entrada;
import co.edu.uniquindio.eventos.patterns.structural.ServicioBase;
import co.edu.uniquindio.eventos.patterns.structural.ServicioVIP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservaBuilderTest {

    @Test
    void debeConstruirCompraValida() {
        Entrada entrada = TestDataUtil.crearEntrada();

        Compra compra = new ReservaBuilder()
                .conDatosBasicos("C1", TestDataUtil.crearUsuario(), TestDataUtil.crearEvento())
                .agregarEntrada(entrada)
                .agregarServicio(new ServicioVIP(new ServicioBase()))
                .construir();

        assertNotNull(compra);
        assertEquals(1, compra.getEntradas().size());
        assertEquals(1, compra.getServicios().size());
    }
}
