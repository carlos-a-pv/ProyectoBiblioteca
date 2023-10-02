package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Autor;
import model.Libro;
import model.Tema;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static controller.AppController.INSTANCE;

public class PrincipalViewController {

    @FXML
    private Button btnPrestamo;
    @FXML
    private Button btnDevolucion;
    @FXML
    private AnchorPane principalView;
    @FXML
    private TableView<Libro> tablaLibros;
    @FXML
    private TextField campoNombreAutor;
    @FXML
    private ComboBox<Tema> comboBoxGenero;
    @FXML
    private DatePicker datosFechaPublicacion;

    @FXML
    private TextField campoNombreLibro;
    @FXML
    private TableColumn<Libro, String> columnaNombreLibros;
    @FXML
    private TableColumn<Libro, Autor> columnaAutor;

    @FXML
    private TableColumn<Libro, LocalDate> columnaFechaPublicacion;

    @FXML
    private TableColumn<Libro, String> columnaGenero;

    @FXML
    private TableColumn<Libro, String> columnaEstado;
    ObservableList<Libro> listaLibrosData = FXCollections.observableArrayList();

    Libro libroSeleccionado;

    @FXML
    void initialize(){
        tablaLibros.setItems(getListaLibrosData());
        columnaNombreLibros.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("temario"));
        columnaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columnaFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        comboBoxGenero.getItems().addAll(
                Tema.ACCION,
                Tema.TERROR,
                Tema.ROMANCE,
                Tema.POESIA,
                Tema.COMEDIA,
                Tema.NOVELA
        );
        tablaLibros.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));


    }

    public void onClickPrestamo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("../view/prestamo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        Stage stage = new Stage();
        stage.setTitle("PRESTAMO");
        stage.setScene(scene);
        stage.show();

    }

    public void onClickDevolucion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("../view/devolucion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        Stage stage = new Stage();
        stage.setTitle("Devolución");
        stage.setScene(scene);

        stage.show();
    }
    private void llenarCampos(Libro libro) {
        libroSeleccionado = libro;
        if (libro != null){
            campoNombreLibro.setText(libro.getNombre());
            campoNombreAutor.setText(libro.getAutor().getNombre());
            comboBoxGenero.setValue(libro.getTemario());
            datosFechaPublicacion.setValue(libro.getFechaPublicacion());
        }
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean datosValidos(String nombreLibro, Tema generoLibro, String autorLibro, LocalDate fechaPublicacion) {
        String mensaje = "";

        if (nombreLibro == null || nombreLibro.equals(""))
            mensaje += "El nombre del libro es inválido.\n";

        if (generoLibro == null)
            mensaje += "El género del libro es inválido.\n";

        if (autorLibro == null || autorLibro.equals(""))
            mensaje += "El autor del libro es inválido.\n";

        if (fechaPublicacion == null)
            mensaje += "La fecha de publicación es inválida.\n";

        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación", "Datos inválidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void limpiarCamposLibro() {
        campoNombreLibro.setText("");
        comboBoxGenero.setValue(null); //
        campoNombreAutor.setText("");
        datosFechaPublicacion.setValue(null);
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    @FXML
    void cargarLibroOnAction (ActionEvent event){
        String nombreLibro = campoNombreLibro.getText();
        Tema generoLibro = comboBoxGenero.getValue();
        String autorLibro = campoNombreAutor.getText();
        LocalDate fechaPublicacion = datosFechaPublicacion.getValue();

        if (datosValidos(nombreLibro, generoLibro, autorLibro, fechaPublicacion) == true) {

            Libro libro = INSTANCE.getModel().crearLibro(nombreLibro, generoLibro, autorLibro, fechaPublicacion);;

            if (libro != null) {
                listaLibrosData.addAll(libro);
                limpiarCamposLibro();
                tablaLibros.setItems(listaLibrosData);
                mostrarMensaje("Notificación", "Libro creado", "El libro se ha creado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Notificación", "Libro no creado", "El libro no se ha creado correctamente.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación libro", "Libro no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void editarLibroOnAction (ActionEvent event) {
        if (libroSeleccionado != null) {

            String nuevoAutor = campoNombreAutor.getText();
            String nuevoNombreLibro = campoNombreLibro.getText();
            Tema nuevoGeneroLibro = comboBoxGenero.getValue();
            LocalDate nuevaFechaPublicacion = datosFechaPublicacion.getValue();
            Libro newLibro = new Libro(nuevoNombreLibro,new Autor(nuevoAutor,"",""),nuevoGeneroLibro);
            if (datosValidos(nuevoNombreLibro, nuevoGeneroLibro, nuevoAutor, nuevaFechaPublicacion)) {

                if (INSTANCE.getModel().actualizarLibro(libroSeleccionado,newLibro)) {
                    // Refrescar la tabla
                    tablaLibros.refresh();
                    mostrarMensaje("Notificación libro", "Libro actualizado", "El libro se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    //  limpiarCamposLibro();
                } else {
                    mostrarMensaje("Notificación libro", "Libro no actualizado", "El libro no se ha actualizado", Alert.AlertType.INFORMATION);
                }
            } else {
                mostrarMensaje("Notificación libro", "Libro no actualizado", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación libro", "Libro no seleccionado", "Selecciona un libro de la lista", Alert.AlertType.WARNING);
        }
    }
    @FXML
    void eliminarLibroOnAction (ActionEvent event){
        boolean libroEliminado = false;

        if (libroSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Estás seguro de eliminar el libro?")) {
                libroEliminado = INSTANCE.getModel().eliminarLibro(libroSeleccionado.getNombre());

                if (libroEliminado) {
                    listaLibrosData.remove(libroSeleccionado);
                    libroSeleccionado = null;

                    // Limpia cualquier otro componente relacionado que se necesite
                    // (por ejemplo, gráficos, selecciones, etc.)

                    tablaLibros.getSelectionModel().clearSelection();
                    limpiarCamposLibro();

                    mostrarMensaje("Notificación libro", "Libro eliminado", "El libro se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Notificación libro", "Libro no eliminado", "El libro no se pudo eliminar", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Notificación libro", "Libro no seleccionado", "Selecciona un libro de la lista", Alert.AlertType.WARNING);
        }
    }

    public ObservableList<Libro> getListaLibrosData() {
        listaLibrosData.addAll(INSTANCE.getModel().getListaLibros());
        return listaLibrosData;
    }
}
