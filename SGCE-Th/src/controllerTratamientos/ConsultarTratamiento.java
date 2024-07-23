package controllerTratamientos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultarTratamiento {
    @FXML
    private Button btnCerrarSesion;
    @FXML
    void actionRegistroPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }
    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");
    }
    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
    }
    @FXML
    void actionRegistroTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/RegTratamiento.fxml");
    }
    @FXML
    void actionActualizarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/ActualizarTratamientos.fxml");
    }
    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml", (Stage) btnCerrarSesion.getScene().getWindow());
    }
    private void navigateTo(String fxmlPath) throws IOException {
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.hide();
        Stage main = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        main.setScene(new Scene(root));
        main.show();
    }
    private void navigateTo(String fxmlPath, Stage currentStage) throws IOException {
        currentStage.hide();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        newStage.setScene(new Scene(root));
        newStage.show();
    }

}
