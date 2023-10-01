package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Optional;

import static controller.AppController.INSTANCE;

public class PrestamoViewController {

    ObservableList<Libro> listaLibrosData = FXCollections.observableArrayList();
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
    @FXML
    private ComboBox<String> cbFiltro;

    Libro libroSeleccionado;

    @FXML
    void initialize(){
        cbFiltro.getItems().addAll("Nombre","Autor", "Fecha de Publicacion", "Estado");
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.colFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tbLibros.setItems(getListaLibros());

        tbLibros.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    libroSeleccionado = newValue;
                });
    }

    private ObservableList<Libro> getListaLibros() {
        listaLibrosData.addAll(INSTANCE.getModel().getListaLibros());
        return listaLibrosData;

    }


    public void onClickPrestar() {

        if(libroSeleccionado != null){
            Stage dialog = new Stage();
            dialog.setTitle("PRESTAMO");
            dialog.setMaxWidth(500);
            dialog.setMaxHeight(400);

            Label label = new Label("Ingrese la cedula del estudiante");
            TextField textField = new TextField("Ingrese su texto aquí");
            Button btnGuardar = new Button("Buscar");
            Button btnCancelar = new Button("Cancelar");
            HBox botones = new HBox(btnGuardar, btnCancelar);
            botones.setSpacing(25);

            // Agregar el campo de texto a la ventana modal
            VBox vbox = new VBox(label, textField, botones);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            dialog.setScene(new Scene(vbox));

            // Mostrar la ventana modal
            dialog.show();

            //
            btnCancelar.setOnMouseClicked(event1 -> {
                dialog.close();
            });

            btnGuardar.setOnMouseClicked(event -> {
                String id = textField.getText();
                if(!id.equals("")){
                    Estudiante estudiante = INSTANCE.getModel().buscarEstudiante(id);
                    if(estudiante != null){
                        Prestamo prestamo = INSTANCE.getModel().prestarLibro(estudiante, libroSeleccionado);
                        if(prestamo != null){
                            mostrarMensaje("INFO","INFO","Libro prestado correctamente, recuerda el numero: "+prestamo.getCodigo()+" para la devolucion del libro", Alert.AlertType.INFORMATION);
                            tbLibros.refresh();
                            tbLibros.setItems(getListaLibros());
                            dialog.close();
                        }else{
                            mostrarMensaje("ADVERTENCIA", "ADVERTENCIA", "El libro ya esta prestado", Alert.AlertType.WARNING);
                            dialog.close();
                        }
                    }else{
                        mostrarMensaje("ERROR","No se ha encontrado un estudiante, registrese primero","", Alert.AlertType.ERROR);
                        dialog.close();
                    }
                }
            });
        }else{
            mostrarMensaje("","ADVERTENCIA","Seleccione un libro para prestar", Alert.AlertType.WARNING);
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (((Optional<?>) action).get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
