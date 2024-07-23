package controllerMedicos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsHorarioLaboralMedico {

    @FXML
    private Button btnRegresar;

    @FXML
    void actionRegresar(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ConsultarMedico.fxml");
    }
    private void navigateTo(String fxmlPath) throws IOException {
        Stage currentStage = (Stage) btnRegresar.getScene().getWindow();
        currentStage.hide();
        Stage main = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        main.setScene(new Scene(root));
        main.show();
    }

}
