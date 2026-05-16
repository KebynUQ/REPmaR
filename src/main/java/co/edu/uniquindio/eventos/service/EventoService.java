package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.enums.EstadoEvento;
import co.edu.uniquindio.eventos.repository.DatosPrueba;

import java.util.ArrayList;
import java.util.List;

public class EventoService {
    public List<Evento> listarEventos() {
        return DatosPrueba.getInstancia().getEventos();
    }

    public List<Evento> listarEventosPublicados() {
        return listarEventos().stream()
                .filter(evento -> evento.getEstado() == EstadoEvento.PUBLICADO)
                .toList();
    }

    public List<Evento> filtrarPorCiudad(String ciudad) {
        return listarEventos().stream()
                .filter(evento -> evento.getCiudad().equalsIgnoreCase(ciudad))
                .toList();
    }

    public List<Evento> filtrarPorCategoria(String categoria) {
        return listarEventos().stream()
                .filter(evento -> evento.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public void agregarEvento(Evento evento) {
        DatosPrueba.getInstancia().getEventos().add(evento);
    }

    public void publicarEvento(Evento evento) {
        evento.publicar();
    }

    public void pausarEvento(Evento evento) {
        evento.pausar();
    }

    public void cancelarEvento(Evento evento) {
        evento.cancelar();
    }

    public List<Evento> obtenerEventos() {
        return new ArrayList<>(listarEventos());
    }

    public List<Evento> obtenerEventosPublicados() {
        return new ArrayList<>(listarEventosPublicados());
    }
}
