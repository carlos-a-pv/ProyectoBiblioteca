package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import model.*;

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
    private TableColumn<Libro, EstadoLibro> colEstado;
    @FXML
    private Text txtMensaje;
    @FXML
    private AnchorPane devolucionView;

    ObservableList<Libro> listaLibrosData = FXCollections.observableArrayList();

    Libro libroSeleccionado;
    Estudiante estudiante;


    @FXML
    void initialize() {
        LinearGradient paint = new LinearGradient(
                0.2227, 0.1422, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(0.0195, 0.39, 0.0998, 0.9442)),
                new Stop(1.0, new Color(1.0, 1.0, 1.0, 1.0)));
        devolucionView.setBackground(new Background(new BackgroundFill(paint,null,null)));
        tbLibros.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    libroSeleccionado = newValue;
                });
    }


    public void onBuscarId(ActionEvent actionEvent) {
        listaLibrosData.clear();
        String id = tfId.getText();
        estudiante = INSTANCE.getModel().buscarEstudiante(id);
        if (estudiante!=null){
            listaLibrosData.addAll(INSTANCE.getModel().buscarPrestamoEstudiante(estudiante));
            if (!listaLibrosData.isEmpty()){
                tbLibros.setItems(listaLibrosData);
                this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
                this.colEstado.setCellValueFactory(new  PropertyValueFactory<>("estado"));
                txtMensaje.setText("Estos son los libros prestados a "+estudiante.getNombre()+" "+estudiante.getApellido()+" identificado con C.C "+estudiante.getId());
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
        listaLibrosData.clear();
        String codigo = tfId.getText();
        Prestamo prestamo = INSTANCE.getModel().buscarPrestamo(codigo);
        if (prestamo!=null){
            estudiante = prestamo.getDetallePrestamo().getEstudiante();
            if (prestamo.getDetallePrestamo().getLibroPrestado()!=null){
                listaLibrosData.add(prestamo.getDetallePrestamo().getLibroPrestado());
                tbLibros.setItems(listaLibrosData);
                this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
                this.colEstado.setCellValueFactory(new  PropertyValueFactory<>("estado"));
                txtMensaje.setText("El libro prestado con código "+codigo+",si desea lo demás libros prestados por el estudiante "+estudiante.getNombre()+" "+estudiante.getApellido()+" identificado con C.C "+estudiante.getId()+" Realice la busqueda por cedula.");
                tbLibros.refresh();
            }
        }
        else {
            mostrarMensaje("información libro","ERROR","No existe este codigo de prestamo en el sistema", Alert.AlertType.WARNING);
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void onDevolver(ActionEvent actionEvent) {

        if (libroSeleccionado!=null){

                INSTANCE.getModel().devolverLibro(libroSeleccionado,estudiante);
                tbLibros.getItems().clear();
                tbLibros.refresh();
                listaLibrosData.addAll(INSTANCE.getModel().buscarPrestamoEstudiante(estudiante));
                tbLibros.setItems(listaLibrosData);
                tbLibros.refresh();
        }
        else {
            mostrarMensaje("INFORMACIÓN","ERROR","Seleccione un libro para devolución", Alert.AlertType.ERROR);
        }
    }
}
