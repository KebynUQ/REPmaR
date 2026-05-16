package co.edu.uniquindio.eventos.controller;

import co.edu.uniquindio.eventos.Navigator;
import co.edu.uniquindio.eventos.app.MainApp;
import co.edu.uniquindio.eventos.model.Compra;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.repository.DatosPrueba;
import co.edu.uniquindio.eventos.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MainController {
    @FXML
    private StackPane contenidoPane;
    @FXML
    private Label usuarioLabel;
    @FXML
    private Label notificacionLabel;

    private MainApp mainApp;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        usuarioLabel.setText("Usuario: " + mainApp.getUsuarioActual().getNombreCompleto());
        cargarCartelera();
    }

    @FXML
    public void mostrarCartelera() {
        Navigator.irCartelera();
    }

    public void cargarCartelera() {
        VBox root = new VBox(18);
        root.getStyleClass().add("section-wrapper");
        Label titulo = new Label("Estrenos / Cartelera");
        titulo.getStyleClass().add("section-title");
        Label subtitulo = new Label("Eventos destacados con una vista inspirada en cartelera.");
        subtitulo.getStyleClass().add("section-subtitle");

        FlowPane cards = new FlowPane();
        cards.setHgap(18);
        cards.setVgap(18);
        cards.setPadding(new Insets(10, 0, 10, 0));

        for (Evento evento : mainApp.getEventoService().listarEventosPublicados()) {
            cards.getChildren().add(crearTarjetaEvento(evento));
        }

        root.getChildren().addAll(titulo, subtitulo, cards);
        contenidoPane.getChildren().setAll(root);
        actualizarNotificacion();
    }

    private VBox crearTarjetaEvento(Evento evento) {
        VBox card = new VBox(10);
        card.getStyleClass().add("evento-card");
        card.setPrefWidth(250);

        VBox poster = new VBox();
        poster.getStyleClass().add("poster-box");
        poster.setPrefHeight(140);
        Label posterText = new Label(evento.getCategoria().toUpperCase());
        posterText.getStyleClass().add("poster-text");
        poster.getChildren().add(posterText);

        Label nombre = new Label(evento.getNombre());
        nombre.getStyleClass().add("card-title");
        Label ciudad = new Label(evento.getCiudad() + " | " + evento.getCategoria());
        ciudad.getStyleClass().add("card-text");
        Label fecha = new Label(evento.getFechaHora().format(formatter));
        fecha.getStyleClass().add("card-text");
        Label precio = new Label("Desde $" + String.format("%.0f", evento.getPrecioDesde()));
        precio.getStyleClass().add("card-price");
        Button comprar = new Button("Comprar");
        comprar.getStyleClass().add("primary-button");
        comprar.setOnAction(event -> mainApp.mostrarDetalleEvento(evento));

        card.getChildren().addAll(poster, nombre, ciudad, fecha, precio, comprar);
        return card;
    }

    public void cargarDetalleEvento(Evento evento) {
        try {
            FXMLLoader loader = Navigator.loadLoader("detalle-evento-view.fxml");
            Parent vista = loader.load();
            EventoController controller = loader.getController();
            controller.cargarEvento(evento);
            contenidoPane.getChildren().setAll(vista);
            actualizarNotificacion();
        } catch (IOException e) {
            AlertUtil.mostrarInfo("Error", "No fue posible abrir el detalle del evento.");
        }
    }

    public void cargarDetalleEventoActual() {
        if (mainApp.getEventoSeleccionado() != null) {
            cargarDetalleEvento(mainApp.getEventoSeleccionado());
        } else {
            cargarCartelera();
        }
    }

    public void cargarCompra(Compra compra) {
        try {
            FXMLLoader loader = Navigator.loadLoader("compra-view.fxml");
            Parent vista = loader.load();
            CompraController controller = loader.getController();
            controller.cargarCompra(compra);
            contenidoPane.getChildren().setAll(vista);
            actualizarNotificacion();
        } catch (IOException e) {
            AlertUtil.mostrarInfo("Error", "No fue posible abrir la vista de compra.");
        }
    }

    public void cargarAdmin() {
        try {
            FXMLLoader loader = Navigator.loadLoader("admin-view.fxml");
            Parent vista = loader.load();
            AdminController controller = loader.getController();
            controller.cargarDatos();
            contenidoPane.getChildren().setAll(vista);
            actualizarNotificacion();
        } catch (IOException e) {
            AlertUtil.mostrarInfo("Error", "No fue posible abrir el panel de administrador.");
        }
    }

    @FXML
    public void mostrarServicios() {
        VBox box = new VBox(12);
        box.getStyleClass().add("section-wrapper");
        Label titulo = new Label("Servicios adicionales");
        titulo.getStyleClass().add("section-title");
        Label texto = new Label("VIP, seguro de cancelacion y parqueadero se agregan en la pantalla de compra.");
        texto.getStyleClass().add("section-subtitle");
        box.getChildren().addAll(titulo, texto);
        contenidoPane.getChildren().setAll(box);
        actualizarNotificacion();
    }

    @FXML
    public void mostrarPromociones() {
        VBox box = new VBox(12);
        box.getStyleClass().add("section-wrapper");
        Label titulo = new Label("Promociones");
        titulo.getStyleClass().add("section-title");
        Label texto = new Label("Martes de cultura: 10% de descuento informativo para compras grupales.");
        texto.getStyleClass().add("section-subtitle");
        box.getChildren().addAll(titulo, texto);
        contenidoPane.getChildren().setAll(box);
        actualizarNotificacion();
    }

    @FXML
    public void mostrarAdmin() {
        Navigator.irAdmin();
    }

    private void actualizarNotificacion() {
        String mensaje = DatosPrueba.getInstancia().getNotificador().getUltimoMensaje();
        notificacionLabel.setText(mensaje == null ? "Sin notificaciones nuevas." : mensaje);
    }
}
