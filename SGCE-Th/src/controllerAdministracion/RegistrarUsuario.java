package controllerAdministracion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrarUsuario implements Initializable {

    @FXML
    private MenuItem btnActualizarPaciente;

    @FXML
    private Button btnAddPaciente;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private MenuItem btnConsultaTratamiento;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private MenuItem btnRegistroTratamiento;

    @FXML
    private ChoiceBox<String> cboxRol;

    @FXML
    private PasswordField pswConfirmarC;

    @FXML
    private PasswordField pswNuevaC;


    @FXML
    private TextField txtFieldCorreoUsuario;

    @FXML
    private TextField txtFieldNombreUsuario;

    @FXML
    void actionAddPaciente(ActionEvent event) {

    }

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void getRol(MouseEvent event) {
        String Rol = cboxRol.getSelectionModel().getSelectedItem();
    }

    @FXML
    void actionConfirmarC(ActionEvent event) {

    }
    @FXML
    void actionNuevaC(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboxRol.getItems().addAll("Administrador","Usuario");
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnAddPaciente.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
