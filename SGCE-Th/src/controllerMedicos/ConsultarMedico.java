package controllerMedicos;

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

public class ConsultarMedico {

    @FXML
    private MenuItem btnActPagoPendiente;

    @FXML
    private MenuItem btnActPagoRealizado;

    @FXML
    private MenuItem btnActualizarCita;

    @FXML
    private MenuItem btnActualizarMedico;

    @FXML
    private MenuItem btnActualizarPaciente;

    @FXML
    private MenuItem btnActualizarTratamiento;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private MenuItem btnConsPagoPendiente;

    @FXML
    private MenuItem btnConsPagoRealizado;

    @FXML
    private MenuItem btnConsultaCita;

    @FXML
    private MenuItem btnConsultaPaciente;

    @FXML
    private MenuItem btnConsultarMedico;

    @FXML
    private MenuItem btnConsultarTratamiento;

    @FXML
    private Button btnHorario;

    @FXML
    private MenuItem btnRegPagoPendiente;

    @FXML
    private MenuItem btnRegPagoRealizado;

    @FXML
    private MenuItem btnRegistrarCita;

    @FXML
    private MenuItem btnRegistrarMedico;

    @FXML
    private MenuItem btnRegistrarTratamiento;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumCedula;

    @FXML
    void actionActPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ActualizarPagoPendiente.fxml");
    }

    @FXML
    void actionActPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ActualizarPagoRealizado.fxml");
    }

    @FXML
    void actionActualizarCita(ActionEvent event) {

    }

    @FXML
    void actionActualizarMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ActualizarMedico.fxml");
    }

    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");
    }

    @FXML
    void actionActualizarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/ActualizarTratamientos.fxml");
    }

    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml");
    }

    @FXML
    void actionConsPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ConsultarPagoPendiente.fxml");
    }

    @FXML
    void actionConsPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ConsultarPagoRealizado.fxml");
    }

    @FXML
    void actionConsultaCita(ActionEvent event) {

    }

    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
    }

    @FXML
    void actionConsultarMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ConsultarMedico.fxml");
    }

    @FXML
    void actionConsultarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/ConsultarTratamientos.fxml");
    }

    @FXML
    void actionHorario(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ConsHorarioLaboralMedico.fxml");
    }

    @FXML
    void actionRegPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/RegistrarPagoPendiente.fxml");
    }

    @FXML
    void actionRegPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/RegistrarPagoRealizado.fxml");
    }

    @FXML
    void actionRegistrarCita(ActionEvent event) throws IOException {
    }

    @FXML
    void actionRegistrarMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/RegistrarMedico.fxml");
    }

    @FXML
    void actionRegistrarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/RegTratamiento.fxml");
    }

    @FXML
    void actionRegistroPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }

    private void navigateTo(String fxmlPath) throws IOException {
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.hide();
        Stage main = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        main.setScene(new Scene(root));
        main.show();
    }

}
