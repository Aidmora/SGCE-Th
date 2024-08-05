package controllerAdministracion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ActualizarUsuario {

    @FXML
    private MenuItem btnActualizarPaciente;

    @FXML
    private MenuItem btnActualizarPaciente1;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private MenuItem btnConsultaPaciente;

    @FXML
    private MenuItem btnConsultaPaciente1;

    @FXML
    private MenuItem btnConsultaTratamiento;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private MenuItem btnRegistroTratamiento;

    @FXML
    private PasswordField pswAntiguaC;

    @FXML
    private PasswordField pswConfirmarC;

    @FXML
    private PasswordField pswNuevaC;

    @FXML
    private TextField txtIDCorreoE;

    @FXML
    private TextField txtNombreActualizarU;

    @FXML
    private TextField txtNuevoCorreoE;


    @FXML
    void actionIDCorreoE(ActionEvent event) {

    }

    @FXML
    void actionNombreActualizarU(ActionEvent event) {

    }

    @FXML
    void actionNuevaC(ActionEvent event) {

    }

    @FXML
    void actionNuevoCorreoE(ActionEvent event) {

    }

    @FXML
    void pswConfirmarC(ActionEvent event) {

    }

    @FXML
    public void actionAntiguaC(ActionEvent actionEvent) {
    }

    @FXML
    public void actionActualizarUsuario(ActionEvent actionEvent) {
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnActualizarUsuario.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
