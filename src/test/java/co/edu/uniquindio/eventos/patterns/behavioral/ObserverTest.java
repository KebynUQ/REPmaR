package co.edu.uniquindio.eventos.patterns.behavioral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObserverTest {

    @Test
    void debeAgregarEliminarYNotificarObservadores() {
        Notificador notificador = new Notificador();
        NotificacionUsuario usuario = new NotificacionUsuario();
        NotificacionAdministrador administrador = new NotificacionAdministrador();

        notificador.agregarObservador(usuario);
        notificador.agregarObservador(administrador);
        notificador.notificar("Compra creada");

        assertEquals("Usuario: Compra creada", usuario.getUltimoMensaje());
        assertEquals("Admin: Compra creada", administrador.getUltimoMensaje());

        notificador.eliminarObservador(administrador);
        notificador.notificar("Cambio de estado");

        assertEquals(1, notificador.getCantidadObservadores());
        assertEquals("Usuario: Cambio de estado", usuario.getUltimoMensaje());
    }
}
