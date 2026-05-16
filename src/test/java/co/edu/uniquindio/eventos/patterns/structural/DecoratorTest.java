package co.edu.uniquindio.eventos.patterns.structural;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorTest {

    @Test
    void servicioBaseDebeTenerDescripcionYPrecio() {
        ServicioAdicional servicio = new ServicioBase();

        assertEquals(0, servicio.getCosto());
        assertTrue(servicio.getDescripcion().contains("Compra"));
    }

    @Test
    void debeAumentarPrecioConDecoradores() {
        ServicioAdicional servicio = new Parqueadero(new SeguroCancelacion(new ServicioVIP(new ServicioBase())));

        assertTrue(servicio.getCosto() > 0);
        assertEquals(65000, servicio.getCosto());
        assertTrue(servicio.getDescripcion().contains("ingreso preferencial"));
    }
}
