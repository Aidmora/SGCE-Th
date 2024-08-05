package controllerCitas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ActualizarCitas {
    @FXML
    private Button btnConcluirCita;
    @FXML
    private Button btnReagendarCita;

    @FXML
    void actionReagendarCitas(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ReagendarCita.fxml");
    }
    @FXML
    void actionConcluirCitas(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ConcluirCita.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnConcluirCita.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
