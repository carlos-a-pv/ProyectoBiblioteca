package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalViewController {

    @FXML
    private Button btnPrestamo;
    @FXML
    private Button btnDevolucion;

    @FXML
    void initialize(){

    }


    public void onClickPrestamo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("../view/prestamo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Stage stage = new Stage();
        stage.setTitle("PRESTAMO");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        //stage.initOwner(btnPrestamo.getScene().getWindow());
        //btnPrestamo.getScene().getWindow().hide();
        stage.show();

    }

    public void onClickDevolucion() {

    }
}
