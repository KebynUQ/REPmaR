package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioServiceTest {

    @Test
    void debeRegistrarBuscarYListarUsuarios() {
        UsuarioService service = new UsuarioService();
        int cantidadInicial = service.listarUsuarios().size();
        Usuario usuario = new Usuario("UT-" + System.nanoTime(), "Nuevo Usuario", "nuevo" + System.nanoTime() + "@mail.com", "300");

        service.registrarUsuario(usuario);

        assertNotNull(service.buscarPorCorreo(usuario.getCorreo()));
        assertTrue(service.listarUsuarios().size() >= cantidadInicial + 1);
    }
}
