package controllerPagos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ConsultarPagoRealizado {


    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml", event);
    }

    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/ConsultarPaciente.fxml", event);
    }

    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/ActualizarPaciente.fxml", event);
    }

    private void navigateTo(String fxmlPath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
