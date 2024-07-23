package controllerCitas;

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

public class ConcluirCita {
    @FXML
    private Button btnAceptar;
    @FXML
    void actionBuscar(ActionEvent event) {

    }
    @FXML
    private MenuItem btnActualizacionCita;

    @FXML
    private MenuItem btnActualizacionMedicos;

    @FXML
    private MenuItem btnActualizacionTratamiento;

    @FXML
    private MenuItem btnActualizarPaciente;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private MenuItem btnConsultaCita;

    @FXML
    private MenuItem btnConsultaCliente;

    @FXML
    private MenuItem btnConsultaMedicos;

    @FXML
    private MenuItem btnConsultaTratamiento;

    @FXML
    private MenuItem btnRegistroCita;

    @FXML
    private MenuItem btnRegistroMedicos;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private MenuItem btnRegistroTratamiento;

    @FXML
    private TextField txtNumCedula;

    @FXML
    private TextField txtNumCedula1;

    @FXML
    private TextField txtNumCedula2;

    @FXML
    private TextField txtNumCedula21;

    @FXML
    void actionRegistroTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/RegTratamiento.fxml");
    }
    @FXML
    void actionConsultarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/ConsultarTratamientos.fxml");
    }
    @FXML
    void actionActualizarTratamiento(ActionEvent event) throws IOException {
        navigateTo("/fxml/Tratamientos/ActualizarTratamientos.fxml");
    }
    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");
    }

    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml");
    }

    @FXML
    void actionRegistroPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }

    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
    }
    @FXML
    void actionRegistroCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/RegistrarCita.fxml");
    }
    @FXML
    void actionRegistroMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/RegistrarMedico.fxml");
    }
    @FXML
    void actionConsultarMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ConsultarMedico.fxml");
    }
    @FXML
    void actionActualizarMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ActualizarMedico.fxml");
    }
    @FXML
    void actionConsultarHorarioMedico(ActionEvent event) throws IOException {
        navigateTo("/fxml/Medicos/ConsHorarioLaboralMedico.fxml");
    }
    @FXML
    void actionConsultarPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ConsultarPagoRealizado.fxml");
    }
    @FXML
    void actionConsultarPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ConsultarPagoPendiente.fxml");
    }
    @FXML
    void actionRegistrarPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/RegistrarPagoRealizado.fxml");
    }
    @FXML
    void actionRegistrarPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/RegistrarPagoPendiente.fxml");
    }
    @FXML
    void actionActualizarPagoPendiente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ActualizarPagoPendiente.fxml");
    }
    @FXML
    void actionActualizarPagoRealizado(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pagos/ActualizarPagoRealizado.fxml");
    }

    @FXML
    void actionConsultarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ConsultarCita.fxml");
    }
    @FXML
    void actionActualizarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ActualizarCita.fxml");
    }
    private void navigateTo(String fxmlPath, Stage currentStage) throws IOException {
        currentStage.hide();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        newStage.setScene(new Scene(root));
        newStage.show();
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