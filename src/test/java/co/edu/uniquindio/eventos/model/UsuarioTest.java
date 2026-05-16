package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.patterns.behavioral.PagoTarjeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {

    @Test
    void debeCrearUsuarioConDatosBasicos() {
        Usuario usuario = new Usuario("U1", "Mariana", "mariana@uq.edu.co", "3001234567");

        assertEquals("Mariana", usuario.getNombreCompleto());
        assertEquals("mariana@uq.edu.co", usuario.getCorreo());
        assertEquals("3001234567", usuario.getTelefono());
    }

    @Test
    void debeAgregarMetodoDePago() {
        Usuario usuario = new Usuario("U1", "Mariana", "mariana@uq.edu.co", "3001234567");

        usuario.agregarMetodoPago(new PagoTarjeta());

        assertEquals(1, usuario.getMetodosPago().size());
        assertEquals("Tarjeta", usuario.getMetodosPago().get(0).getNombre());
    }

    @Test
    void debeAgregarCompraAlHistorial() {
        Usuario usuario = new Usuario("U1", "Mariana", "mariana@uq.edu.co", "3001234567");
        Evento evento = co.edu.uniquindio.eventos.TestDataUtil.crearEvento();
        Compra compra = new Compra("C1", usuario, evento);

        usuario.agregarCompra(compra);

        assertEquals(1, usuario.getCompras().size());
        assertTrue(usuario.consultarHistorialCompras().contains(compra));
    }
}
