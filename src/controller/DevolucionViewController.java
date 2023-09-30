package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autor;
import model.EstadoLibro;
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
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.colFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

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

}
