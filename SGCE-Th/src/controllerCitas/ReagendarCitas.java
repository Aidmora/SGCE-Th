package controllerCitas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class ReagendarCitas {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnGuardar;

    @FXML
    private DatePicker datePicker;

    @FXML
    void actionDatePicker(ActionEvent event) {

    }
    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml", (Stage) btnCerrarSesion.getScene().getWindow());
    }
    @FXML
    void actionRegresarActualizarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ActualizarCita.fxml", (Stage) btnCancelar.getScene().getWindow());
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
