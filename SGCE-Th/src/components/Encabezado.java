package components;

import controller.LoginC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Encabezado extends AnchorPane {
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Menu menuPagos;

    @FXML
    private MenuItem
            itemRegistrarPaciente,
            itemConsultaPaciente,
            itemActualizarPaciente;

    @FXML
    private MenuItem
            itemRegistroCita,
            itemConsultarCita,
            itemActualizarCita;

    @FXML
    private MenuItem
            itemRegistroMedico,
            itemConsultaMedico,
            itemActualizarMedico;

    @FXML
    private MenuItem
            itemRegistroTratamiento,
            itemConsultaTratamiento,
            itemActualizarTratamiento;

    @FXML
    private MenuItem
            itemRegPagoPendiente,
            itemRegPagoRealizado,
            itemConsPagoPendiente,
            itemConsPagoRealizado,
            itemActPagoPendiente,
            itemActPagoRealizado;

    @FXML
    private MenuItem
            itemRegUsuario,
            itemActUsuario;

    public Encabezado() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/components/Encabezado.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        controlRoles();

        btnCerrarSesion.setOnAction(this::controlarAction);

        // PACIENTES
        itemRegistrarPaciente.setOnAction(this::controlarAction);
        itemConsultaPaciente.setOnAction(this::controlarAction);
        itemActualizarPaciente.setOnAction(this::controlarAction);

        // MÉDICOS
        itemRegistroMedico.setOnAction(this::controlarAction);
        itemConsultaMedico.setOnAction(this::controlarAction);
        itemActualizarMedico.setOnAction(this::controlarAction);

        // TRATAMIENTOS
        itemRegistroTratamiento.setOnAction(this::controlarAction);
        itemConsultaTratamiento.setOnAction(this::controlarAction);
        itemActualizarTratamiento.setOnAction(this::controlarAction);

        // CITAS
        itemRegistroCita.setOnAction(this::controlarAction);
        itemConsultarCita.setOnAction(this::controlarAction);
        itemActualizarCita.setOnAction(this::controlarAction);

        // PAGOS
        itemRegPagoPendiente.setOnAction(this::controlarAction);
        itemRegPagoRealizado.setOnAction(this::controlarAction);
        itemConsPagoPendiente.setOnAction(this::controlarAction);
        itemConsPagoRealizado.setOnAction(this::controlarAction);
        itemActPagoPendiente.setOnAction(this::controlarAction);
        itemActPagoRealizado.setOnAction(this::controlarAction);

        // ADMINISTRACIÓN
        itemRegUsuario.setOnAction(this::controlarAction);
        itemActUsuario.setOnAction(this::controlarAction);
    }

    @FXML
    private void controlarAction(ActionEvent event) {
        if (event.getSource() == btnCerrarSesion)
            navigateTo("/fxml/Login.fxml");

        // PACIENTES
        else if (event.getSource() == itemRegistrarPaciente)
            navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
        else if (event.getSource() == itemConsultaPaciente)
            navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
        else if (event.getSource() == itemActualizarPaciente)
            navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");

        // MÉDICOS
        else if (event.getSource() == itemRegistroMedico)
            navigateTo("/fxml/Medicos/RegistrarMedico.fxml");
        else if (event.getSource() == itemConsultaMedico)
            navigateTo("/fxml/Medicos/ConsultarMedico.fxml");
        else if (event.getSource() == itemActualizarMedico)
            navigateTo("/fxml/Medicos/ActualizarMedico.fxml");

        // TRATAMIENTOS
        else if (event.getSource() == itemRegistroTratamiento)
            navigateTo("/fxml/Tratamientos/RegTratamiento.fxml");
        else if (event.getSource() == itemConsultaTratamiento)
            navigateTo("/fxml/Tratamientos/ConsultarTratamientos.fxml");
        else if (event.getSource() == itemActualizarTratamiento)
            navigateTo("/fxml/Tratamientos/ActualizarTratamientos.fxml");

        // CITAS
        else if (event.getSource() == itemRegistroCita)
            navigateTo("/fxml/Citas/RegistrarCita.fxml");
        else if (event.getSource() == itemConsultarCita)
            navigateTo("/fxml/Citas/ConsultarCita.fxml");
        else if (event.getSource() == itemActualizarCita)
            navigateTo("/fxml/Citas/ActualizarCita.fxml");

        // PAGOS
        else if (event.getSource() == itemRegPagoPendiente)
            navigateTo("/fxml/Pagos/RegistrarPagoPendiente.fxml");
        else if (event.getSource() == itemRegPagoRealizado)
            navigateTo("/fxml/Pagos/RegistrarPagoRealizado.fxml");
        else if (event.getSource() == itemConsPagoPendiente)
            navigateTo("/fxml/Pagos/ConsultarPagoPendiente.fxml");
        else if (event.getSource() == itemConsPagoRealizado)
            navigateTo("/fxml/Pagos/ConsultarPagoRealizado.fxml");
        else if (event.getSource() == itemActPagoPendiente)
            navigateTo("/fxml/Pagos/ActualizarPagoPendiente.fxml");
        else if (event.getSource() == itemActPagoRealizado)
            navigateTo("/fxml/Pagos/ActualizarPagoRealizado.fxml");

        // ADMINISTRACIÓN
        else if (event.getSource() == itemRegUsuario)
            navigateTo("/fxml/Administracion/RegistrarUsuario.fxml");
        else if (event.getSource() == itemActUsuario)
            navigateTo("/fxml/Administracion/ActualizarUsuario.fxml");
    }

    private void controlRoles() {
        String rol = LoginC.rol;
        if(!rol.equals("Administración")){
            menuPagos.setDisable(true);
            itemActualizarMedico.setDisable(true);
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
