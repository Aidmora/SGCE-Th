package controllerCitas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ConcluirCita {
    @FXML
    private Button btnAceptar;
    @FXML
    void actionBuscar(ActionEvent event) {

    }
    @FXML
    private MenuItem btnActualizacionCita;

    @FXML
    private MenuItem btnActualizacionMedicos;

    @FXML
    private MenuItem btnActualizacionTratamiento;

    @FXML
    private MenuItem btnActualizarPaciente;

    @FXML
    private MenuItem btnConsultaCita;

    @FXML
    private MenuItem btnConsultaCliente;

    @FXML
    private MenuItem btnConsultaMedicos;

    @FXML
    private MenuItem btnConsultaTratamiento;

    @FXML
    private MenuItem btnRegistroCita;

    @FXML
    private MenuItem btnRegistroMedicos;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private MenuItem btnRegistroTratamiento;

    @FXML
    private TextField txtNumCedula;

    @FXML
    private TextField txtNumCedula1;

    @FXML
    private TextField txtNumCedula2;

    @FXML
    private TextField txtNumCedula21;

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}