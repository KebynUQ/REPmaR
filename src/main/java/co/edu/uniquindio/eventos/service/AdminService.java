package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Incidencia;
import co.edu.uniquindio.eventos.model.PanelMetricas;
import co.edu.uniquindio.eventos.model.Zona;
import co.edu.uniquindio.eventos.model.enums.EstadoEvento;
import co.edu.uniquindio.eventos.repository.DatosPrueba;

import java.util.List;
import java.util.UUID;

public class AdminService {
    public List<Evento> obtenerEventos() {
        return DatosPrueba.getInstancia().getEventos();
    }

    public void bloquearAsiento(Asiento asiento) {
        asiento.bloquear();
    }

    public void liberarAsiento(Asiento asiento) {
        asiento.liberar();
    }

    public Incidencia registrarIncidencia(String tipo, String descripcion, String entidadAfectada) {
        Incidencia incidencia = new Incidencia("INC-" + UUID.randomUUID().toString().substring(0, 5), tipo, descripcion, entidadAfectada);
        DatosPrueba.getInstancia().getIncidencias().add(incidencia);
        return incidencia;
    }

    public List<Incidencia> listarIncidencias() {
        return DatosPrueba.getInstancia().getIncidencias();
    }

    public PanelMetricas obtenerMetricasBasicas() {
        PanelMetricas panel = new PanelMetricas();
        panel.setCantidadCompras(DatosPrueba.getInstancia().getCompras().size());
        panel.setTotalVentas(DatosPrueba.getInstancia().getCompras().stream().mapToDouble(compra -> compra.getTotal()).sum());
        panel.setCantidadEventosPublicados((int) obtenerEventos().stream()
                .filter(evento -> evento.getEstado() == EstadoEvento.PUBLICADO)
                .count());

        for (Evento evento : obtenerEventos()) {
            for (Zona zona : evento.getRecinto().getZonas()) {
                panel.getOcupacionPorZona().put(evento.getNombre() + " - " + zona.getNombre(), zona.consultarOcupacion());
            }
        }
        return panel;
    }

    public PanelMetricas consultarMetricas() {
        return obtenerMetricasBasicas();
    }
}
