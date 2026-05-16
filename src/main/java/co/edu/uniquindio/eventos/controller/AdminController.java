package co.edu.uniquindio.eventos.controller;

import co.edu.uniquindio.eventos.Navigator;
import co.edu.uniquindio.eventos.app.MainApp;
import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Incidencia;
import co.edu.uniquindio.eventos.model.PanelMetricas;
import co.edu.uniquindio.eventos.model.Zona;
import co.edu.uniquindio.eventos.repository.DatosPrueba;
import co.edu.uniquindio.eventos.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @FXML
    private ListView<Evento> eventosListView;
    @FXML
    private ListView<Asiento> asientosListView;
    @FXML
    private Label totalVentasLabel;
    @FXML
    private Label comprasLabel;
    @FXML
    private Label publicadosLabel;
    @FXML
    private Label ocupacionLabel;
    @FXML
    private TextField tipoIncidenciaField;
    @FXML
    private TextArea descripcionIncidenciaArea;

    public void cargarDatos() {
        eventosListView.setItems(FXCollections.observableArrayList(MainApp.getInstancia().getAdminService().obtenerEventos()));
        actualizarMetricas();
        eventosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> cargarAsientos(newValue));
    }

    private void cargarAsientos(Evento evento) {
        if (evento == null) {
            return;
        }
        List<Asiento> asientos = new ArrayList<>();
        for (Zona zona : evento.getRecinto().getZonas()) {
            asientos.addAll(zona.getAsientos());
        }
        asientosListView.setItems(FXCollections.observableArrayList(asientos));
    }

    @FXML
    public void publicarEvento() {
        Evento evento = eventosListView.getSelectionModel().getSelectedItem();
        if (evento != null) {
            MainApp.getInstancia().getEventoService().publicarEvento(evento);
            DatosPrueba.getInstancia().getNotificador().notificar("Evento publicado: " + evento.getNombre());
            eventosListView.refresh();
            actualizarMetricas();
        }
    }

    @FXML
    public void pausarEvento() {
        Evento evento = eventosListView.getSelectionModel().getSelectedItem();
        if (evento != null) {
            MainApp.getInstancia().getEventoService().pausarEvento(evento);
            DatosPrueba.getInstancia().getNotificador().notificar("Evento pausado: " + evento.getNombre());
            eventosListView.refresh();
            actualizarMetricas();
        }
    }

    @FXML
    public void cancelarEvento() {
        Evento evento = eventosListView.getSelectionModel().getSelectedItem();
        if (evento != null) {
            MainApp.getInstancia().getEventoService().cancelarEvento(evento);
            DatosPrueba.getInstancia().getNotificador().notificar("Evento cancelado: " + evento.getNombre());
            eventosListView.refresh();
            actualizarMetricas();
        }
    }

    @FXML
    public void bloquearAsiento() {
        Asiento asiento = asientosListView.getSelectionModel().getSelectedItem();
        if (asiento != null) {
            MainApp.getInstancia().getAdminService().bloquearAsiento(asiento);
            asientosListView.refresh();
            actualizarMetricas();
        }
    }

    @FXML
    public void liberarAsiento() {
        Asiento asiento = asientosListView.getSelectionModel().getSelectedItem();
        if (asiento != null) {
            MainApp.getInstancia().getAdminService().liberarAsiento(asiento);
            asientosListView.refresh();
            actualizarMetricas();
        }
    }

    @FXML
    public void registrarIncidencia() {
        Evento evento = eventosListView.getSelectionModel().getSelectedItem();
        String tipo = tipoIncidenciaField.getText();
        String descripcion = descripcionIncidenciaArea.getText();
        if (tipo.isBlank() || descripcion.isBlank() || evento == null) {
            AlertUtil.mostrarInfo("Datos incompletos", "Selecciona un evento y completa la incidencia.");
            return;
        }
        Incidencia incidencia = MainApp.getInstancia().getAdminService()
                .registrarIncidencia(tipo, descripcion, evento.getNombre());
        DatosPrueba.getInstancia().getNotificador().notificar("Nueva incidencia: " + incidencia.consultarDetalle());
        tipoIncidenciaField.clear();
        descripcionIncidenciaArea.clear();
        AlertUtil.mostrarInfo("Incidencia", "Incidencia registrada correctamente.");
    }

    @FXML
    public void volverCartelera() {
        Navigator.volver();
    }

    private void actualizarMetricas() {
        PanelMetricas metricas = MainApp.getInstancia().getAdminService().obtenerMetricasBasicas();
        totalVentasLabel.setText("$" + String.format("%.0f", metricas.getTotalVentas()));
        comprasLabel.setText(String.valueOf(metricas.getCantidadCompras()));
        publicadosLabel.setText(String.valueOf(metricas.getCantidadEventosPublicados()));
        StringBuilder ocupacion = new StringBuilder();
        metricas.getOcupacionPorZona().forEach((nombre, valor) ->
                ocupacion.append(nombre).append(": ").append(String.format("%.0f%%", valor)).append("\n"));
        ocupacionLabel.setText(ocupacion.isEmpty() ? "Sin datos" : ocupacion.toString().trim());
    }
}
