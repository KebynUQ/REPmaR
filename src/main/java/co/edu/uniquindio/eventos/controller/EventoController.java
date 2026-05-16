package co.edu.uniquindio.eventos.controller;

import co.edu.uniquindio.eventos.Navigator;
import co.edu.uniquindio.eventos.app.MainApp;
import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Zona;
import co.edu.uniquindio.eventos.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class EventoController {
    @FXML
    private Label nombreEventoLabel;
    @FXML
    private Label categoriaLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label descripcionLabel;
    @FXML
    private Label recintoLabel;
    @FXML
    private Label politicasLabel;
    @FXML
    private ComboBox<Zona> zonaComboBox;
    @FXML
    private ComboBox<Asiento> asientoComboBox;

    private Evento evento;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void cargarEvento(Evento evento) {
        this.evento = evento;
        nombreEventoLabel.setText(evento.getNombre());
        categoriaLabel.setText("Categoria: " + evento.getCategoria());
        ciudadLabel.setText("Ciudad: " + evento.getCiudad());
        fechaLabel.setText("Fecha: " + evento.getFechaHora().format(formatter));
        descripcionLabel.setText(evento.getDescripcion());
        recintoLabel.setText("Recinto: " + evento.getRecinto().getNombre() + " - " + evento.getRecinto().getDireccion());
        politicasLabel.setText(evento.getPoliticaCancelacion() + " " + evento.getPoliticaReembolso());
        zonaComboBox.setItems(FXCollections.observableArrayList(evento.getRecinto().getZonas()));
    }

    @FXML
    public void actualizarAsientos() {
        Zona zona = zonaComboBox.getValue();
        if (zona != null) {
            asientoComboBox.setItems(FXCollections.observableArrayList(zona.consultarAsientosDisponibles()));
        }
    }

    @FXML
    public void continuarCompra() {
        Zona zona = zonaComboBox.getValue();
        Asiento asiento = asientoComboBox.getValue();
        if (zona == null || asiento == null) {
            AlertUtil.mostrarInfo("Seleccion incompleta", "Selecciona una zona y un asiento disponibles.");
            return;
        }
        MainApp.getInstancia().mostrarCompra(zona, asiento);
    }

    @FXML
    public void volverCartelera() {
        Navigator.irCartelera();
    }
}
