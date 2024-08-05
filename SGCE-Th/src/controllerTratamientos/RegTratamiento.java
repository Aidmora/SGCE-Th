package controllerTratamientos;

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

public class RegTratamiento {

    @FXML
    private TextField txtFieldNombreTerapia1;

    @FXML
    private TextField txtFieldNombreTerapia2;

    @FXML
    private TextField txtFieldNombreTerapia3;

    @FXML
    private TextField txtFieldNombreTerapia4;

    @FXML
    private TextField txtFieldNombreTerapia5;

    @FXML
    private TextField txtFieldNombreTratamiento;

    @FXML
    private TextField txtFieldNumSesiones1;

    @FXML
    private TextField txtFieldNumSesiones2;

    @FXML
    private TextField txtFieldNumSesiones3;

    @FXML
    private TextField txtFieldNumSesiones4;

    @FXML
    private TextField txtFieldNumSesiones5;

    @FXML
    private TextField txtFieldPrecioTratamiento;

    @FXML
    private TextField txtFieldTipoTratamiento;

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) txtFieldTipoTratamiento.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
