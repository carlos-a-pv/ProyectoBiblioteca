package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bibliotecario;
import model.Estudiante;

import static controller.AppController.INSTANCE;

public class CrudEstudianteViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private ObservableList<Estudiante> listEstudianteData = FXCollections.observableArrayList();

    @FXML
    private TableView <Estudiante> tbEstudiantes;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Estudiante,String> columnApellido;

    @FXML
    private TableColumn<Estudiante, String> columnId;

    @FXML
    private TableColumn<Estudiante, String> columnNombre;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    Estudiante estudianteSeleccionado;

    @FXML
    void OnActualizarEstudianteClick(ActionEvent event) {

            //1. Capturar los datos
            String nombre = tfNombre.getText();
            String apellido = tfApellido.getText();
            String id = tfId.getText();
            boolean vendedorActualizado = false;

            //2. verificar el empleado seleccionado
            if(estudianteSeleccionado != null){
                //3. Validar la información
                if(datosValidos(nombre, apellido, id)== true){

                    vendedorActualizado = INSTANCE.getModel().actualizarEstudiante(estudianteSeleccionado.getId(), nombre, apellido, id);

                    if(vendedorActualizado == true){
                        tbEstudiantes.refresh();
                        limpiarCampos();
                        mostrarMensaje("Notificación estudiante", "Estudiante actualizado", "El estudiante se ha actualizado con éxito", Alert.AlertType.INFORMATION);

                    }else{
                        mostrarMensaje("Notificación estudiante", "Estudiante no actualizado", "El empleado no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                        limpiarCampos();
                    }
                }else{
                    mostrarMensaje("Notificación estudiante", "Estudiante no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                }

            }

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

    @FXML
    void OnCrearEstudianteClick(ActionEvent event) {
        if (datosValidos(tfNombre.getText(),tfApellido.getText(),tfId.getText()));{
        Estudiante estudiante = new Estudiante(tfNombre.getText(),tfApellido.getText(),tfId.getText());
        INSTANCE.getModel().añadirEstudiante(estudiante);
        limpiarCampos();
        mostrarMensaje("Información","El estudiante ha sido creado","", Alert.AlertType.INFORMATION);
        tbEstudiantes.setItems(FXCollections.observableArrayList(INSTANCE.getModel().getListaEstudiantes()));
        tbEstudiantes.refresh();
        }
    }

    private void limpiarCampos() {
        tfNombre.clear();
        tfApellido.clear();
        tfId.clear();
    }

    @FXML
    void OnEliminarEstudianteClick(ActionEvent event) {
        if(estudianteSeleccionado != null && mostrarMensajeConfirmacion(" Esta seguro que lo desea eliminar")){

           boolean estudianteEliminado = INSTANCE.getModel().eliminarEstudiante(estudianteSeleccionado);
            if(estudianteEliminado){
                limpiarCampos();
                tbEstudiantes.setItems(FXCollections.observableArrayList(INSTANCE.getModel().getListaEstudiantes()));
                tbEstudiantes.refresh();

                mostrarMensaje("Notificación estudiante", "Estudiante eliminado", "El estudiante se ha eliminado con éxito", Alert.AlertType.INFORMATION);

            }else{
                mostrarMensaje("Notificación estudiante", "Estudiante no eliminado", "El empleado no se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }
        }else{
            mostrarMensaje("Notificación estudiante", "Estudiante no eliminado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void initialize() {

        tbEstudiantes.setItems(getListaEstudiantesData());
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));


        tbEstudiantes.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));

    }

    private void llenarCampos(Estudiante estudiante) {
        estudianteSeleccionado = estudiante;
        if (estudiante != null) {

            tfNombre.setText(estudiante.getNombre());
            tfApellido.setText(estudiante.getApellido());
            tfId.setText(estudiante.getId());

        }
    }
    private ObservableList<Estudiante> getListaEstudiantesData() {
        listEstudianteData.addAll(INSTANCE.getModel().getListaEstudiantes());
        return listEstudianteData;

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
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
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

}
