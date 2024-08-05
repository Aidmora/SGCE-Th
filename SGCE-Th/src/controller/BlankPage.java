package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import usuario.Usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlankPage implements Initializable {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Menu btnPaciente;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private MenuItem btnConsultaPaciente;

    @FXML
    private MenuItem btnActualizarPaciente;
    @FXML
    private MenuItem btnRegistroTratamiento;
    @FXML
    private MenuItem btnConsultaTratamiento;
    @FXML
    private MenuItem btnActualizarTratamiento;
    @FXML
    private Menu btnPagos;
    @FXML
    private MenuItem btnActualizarMedico;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
