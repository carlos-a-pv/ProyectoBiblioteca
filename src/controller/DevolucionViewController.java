package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autor;
import model.EstadoLibro;
import model.Estudiante;
import model.Libro;

import static controller.AppController.INSTANCE;

public class DevolucionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDevolver;
    @FXML
    private MenuButton menuBtn;
    @FXML
    private MenuItem btnBuscarId;
    @FXML
    private MenuItem btnBuscarCodigo;
    @FXML
    private TextField tfId;
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

    ObservableList<Libro> listaLibrosData = FXCollections.observableArrayList();
    Libro libroSeleccionado;

    @FXML
    void onClickDevolver(ActionEvent event) {

//    INSTANCE.getModel().devolverPrestamo(libroSeleccionado);

    }



    @FXML
    void initialize() {

        tbLibros.setItems(getListaLibrosPrestados());

        tbLibros.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    libroSeleccionado = newValue;
                });
    }

    private ObservableList<Libro> getListaLibrosPrestados() {
        listaLibrosData.addAll(INSTANCE.getModel().getListaLibrosPrestados());
        return listaLibrosData;
    }

    public void onBuscarId(ActionEvent actionEvent) {

        String id = tfId.getText();
        Estudiante estudiante = INSTANCE.getModel().buscarEstudiante(id,libroSeleccionado);
        if (estudiante!=null){
            if (estudiante.getLibrosPrestados()!=null){
                tbLibros.setItems(listaLibrosData);
                this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
                this.colEstado.setCellValueFactory(new  PropertyValueFactory<>("estado"));
                tbLibros.refresh();
            }
            else {
                mostrarMensaje("Información Libro","","El estudiante no tiene libros prestados", Alert.AlertType.INFORMATION);
            }

        }
        else {
            mostrarMensaje("información libro","","no existe este estudiante en el sistema", Alert.AlertType.WARNING);
        }
    }

    public void onBuscarCodigo(ActionEvent actionEvent) {
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
