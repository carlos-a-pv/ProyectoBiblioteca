package controller;

import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Autor;
import model.EstadoLibro;
import model.Estudiante;
import model.Libro;
import model.Prestamo;

public class PrestamoViewController {
    ObservableList<Libro> listaLibrosData = FXCollections.observableArrayList();
    @FXML
    private AnchorPane prestamoView;
    @FXML
    private TableView<Libro> tbLibros;
    @FXML
    private TableColumn<Libro, String> colNombre;
    @FXML
    private TableColumn<Libro, Autor> colAutor;
    @FXML
    private TableColumn<Libro, LocalDate> colFechaPublicacion;
    @FXML
    private TableColumn<Libro, EstadoLibro> colEstado;
    Libro libroSeleccionado;

    public PrestamoViewController() {
    }

    @FXML
    void initialize() {
        LinearGradient paint = new LinearGradient(
                0.2227, 0.1422, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(0.0195, 0.39, 0.0998, 0.9442)),
                new Stop(1.0, new Color(1.0, 1.0, 1.0, 1.0)));
        prestamoView.setBackground(new Background(new BackgroundFill(paint,null,null)));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        this.colFechaPublicacion.setCellValueFactory(new PropertyValueFactory("fechaPublicacion"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        this.tbLibros.setItems(this.getListaLibros());
        this.tbLibros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.libroSeleccionado = newValue;
        });
    }

    private ObservableList<Libro> getListaLibros() {
        this.listaLibrosData.addAll(AppController.INSTANCE.getModel().getListaLibros());
        return this.listaLibrosData;
    }

    public void onClickPrestar() {
        if (this.libroSeleccionado != null) {
            Stage dialog = new Stage();
            dialog.setTitle("PRESTAMO");
            dialog.setMaxWidth(500.0);
            dialog.setMaxHeight(400.0);
            Label label = new Label("Ingrese la cedula del estudiante");
            TextField textField = new TextField("Ingrese su texto aquí");
            Button btnGuardar = new Button("Buscar");
            Button btnCancelar = new Button("Cancelar");
            HBox botones = new HBox(new Node[]{btnGuardar, btnCancelar});
            botones.setSpacing(25.0);
            VBox vbox = new VBox(new Node[]{label, textField, botones});
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10.0);
            vbox.setPadding(new Insets(10.0));
            dialog.setScene(new Scene(vbox));
            dialog.show();
            btnCancelar.setOnMouseClicked((event1) -> {
                dialog.close();
            });
            btnGuardar.setOnMouseClicked((event) -> {
                String id = textField.getText();
                if (!id.equals("")) {
                    Estudiante estudiante = AppController.INSTANCE.getModel().buscarEstudiante(id);
                    if (estudiante != null) {
                        Prestamo prestamo = AppController.INSTANCE.getModel().prestarLibro(estudiante, this.libroSeleccionado);
                        if (prestamo != null) {
                            this.mostrarMensaje("INFO", "INFO", "Libro prestado correctamente, recuerda el numero: " + prestamo.getCodigo() + " para la devolucion del libro", AlertType.INFORMATION);
                            this.tbLibros.refresh();
                            this.tbLibros.setItems(this.getListaLibros());
                            dialog.close();
                        } else {
                            this.mostrarMensaje("ADVERTENCIA", "ADVERTENCIA", "El libro ya esta prestado", AlertType.WARNING);
                            dialog.close();
                        }
                    } else {
                        this.mostrarMensaje("ERROR", "No se ha encontrado un estudiante, registrese primero", "", AlertType.ERROR);
                        dialog.close();
                    }
                }

            });
        } else {
            this.mostrarMensaje("", "ADVERTENCIA", "Seleccione un libro para prestar", AlertType.WARNING);
        }

    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText((String)null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
}

