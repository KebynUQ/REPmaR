package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.model.Usuario;
import co.edu.uniquindio.eventos.repository.DatosPrueba;

import java.util.List;

public class UsuarioService {
    public Usuario registrarUsuario(Usuario usuario) {
        DatosPrueba.getInstancia().getUsuarios().add(usuario);
        return usuario;
    }

    public Usuario buscarPorCorreo(String correo) {
        return DatosPrueba.getInstancia().getUsuarios().stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return DatosPrueba.getInstancia().getUsuarios();
    }

    public Usuario obtenerUsuarioSimulado() {
        return listarUsuarios().get(0);
    }
}
