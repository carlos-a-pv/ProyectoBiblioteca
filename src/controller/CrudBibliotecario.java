package controller;

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
import model.Bibliotecario;

import java.util.Optional;

import static controller.AppController.INSTANCE;

public class CrudBibliotecario {

    @FXML
    private AnchorPane bibliotecarioView;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Bibliotecario, String> columnApellido;

    @FXML
    private TableColumn<Bibliotecario,String> columnId;

    @FXML
    private TableColumn<Bibliotecario, String> columnNombre;

    @FXML
    private TableView<Bibliotecario> tbBibliotecarios;
    @FXML
    private ObservableList <Bibliotecario> listBibliotecarioData = FXCollections.observableArrayList();

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    Bibliotecario bibliotecarioSeleccionado;

    @FXML
    void initialize() {
        Color paint = new Color(0.0, 0.0, 0.0, 1.0);
        Color paintBtn = new Color(0.0044, 0.4737, 0.0279, 1.0);
        btnCrear.setBackground(new Background(new BackgroundFill(paintBtn,null,null)));
        btnActualizar.setBackground(new Background(new BackgroundFill(paintBtn,null,null)));
        btnEliminar.setBackground(new Background(new BackgroundFill(paintBtn,null,null)));
        bibliotecarioView.setBackground(new Background(new BackgroundFill(paint,null,null)));
        tbBibliotecarios.setItems(getListaBibliotecariosData());
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));


        tbBibliotecarios.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));

    }

    private void llenarCampos(Bibliotecario bibliotecario) {
        bibliotecarioSeleccionado = bibliotecario;
        if (bibliotecario != null) {

            tfNombre.setText(bibliotecario.getNombre());
            tfApellido.setText(bibliotecario.getApellido());
            tfId.setText(bibliotecario.getId());

        }
    }

    @FXML
    void OnActualizarBibliotecarioClick(ActionEvent event) {
        //1. Capturar los datos
        String nombre = tfNombre.getText();
        String apellido = tfApellido.getText();
        String id = tfId.getText();
        if (datosValidos(nombre,apellido,id)){
            boolean bibliotecarioActualizado = false;

            //2. verificar el empleado seleccionado
            if(bibliotecarioSeleccionado != null){
                //3. Validar la información
                if(datosValidos(nombre, apellido, id)== true){

                    bibliotecarioActualizado = INSTANCE.getModel().actualizarBibliotecario(bibliotecarioSeleccionado.getId(), nombre, apellido, id);

                    if(bibliotecarioActualizado == true){
                        tbBibliotecarios.refresh();
                        limpiarCampos();
                        mostrarMensaje("Notificación Bibliotecario", "Bibliotecario actualizado", "El bibliotecario se ha actualizado con éxito", Alert.AlertType.INFORMATION);

                    }else{
                        mostrarMensaje("Notificación Bibliotecario", "Bibliotecario no actualizado", "El bibliotecario no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                        limpiarCampos();
                    }
                }else{
                    mostrarMensaje("Notificación Bibliotecario", "Bibliotecario no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                }

            }
        }
        else {
            mostrarMensaje("Notificación Bibliotecario", "Bibliotecario no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }


    @FXML
    void OnCrearBibliotecarioClick(ActionEvent event) {
        if (datosValidos(tfNombre.getText(),tfApellido.getText(),tfId.getText())){
            tbBibliotecarios.getItems().clear();
            Bibliotecario bibliotecario = new Bibliotecario(tfNombre.getText(),tfApellido.getText(),tfId.getText());
            INSTANCE.getModel().añadirBibliotecario(bibliotecario);
            limpiarCampos();
            mostrarMensaje("Información","El bibliotecario ha sido creado","", Alert.AlertType.INFORMATION);
            listBibliotecarioData.addAll(INSTANCE.getModel().getBibliotecarios());
            tbBibliotecarios.setItems(listBibliotecarioData);
            tbBibliotecarios.refresh();
        }else {
            mostrarMensaje("Notificación bibliotecario", "bibliotecario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void OnEliminarBibliotecarioClick(ActionEvent event) {
        if(bibliotecarioSeleccionado != null && mostrarMensajeConfirmacion(" Esta seguro que lo desea eliminar") == true){

            boolean estudianteEliminado = INSTANCE.getModel().eliminarBibliotecario(bibliotecarioSeleccionado);
            if(estudianteEliminado){
                limpiarCampos();
                tbBibliotecarios.setItems(FXCollections.observableArrayList(INSTANCE.getModel().getBibliotecarios()));
                tbBibliotecarios.refresh();

                mostrarMensaje("Notificación bibliotecario", "bibliotecario eliminado", "El bibliotecario se ha eliminado con éxito", Alert.AlertType.INFORMATION);

            }else{
                mostrarMensaje("Notificación bibliotecario", "bibliotecario no eliminado", "El bibliotecario no se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }
        }else{
            mostrarMensaje("Notificación bibliotecario", "bibliotecario no eliminado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        tfNombre.clear();
        tfApellido.clear();
        tfId.clear();
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
    private boolean datosValidos(String nombre, String apellido,String id) {
        String mensaje = "";

        if(nombre == null || nombre.equals(""))
            mensaje += "El nombre es invalido \n" ;

        if(apellido == null || apellido.equals(""))
            mensaje += "El apellido es invalido \n" ;
        if (id == null || id.equals(""))
            mensaje+= "La identificación es incorrecta \n ";
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación Bibliotecario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    private ObservableList<Bibliotecario> getListaBibliotecariosData() {

        listBibliotecarioData.addAll(INSTANCE.getModel().getBibliotecarios());
        return listBibliotecarioData;

    }

}