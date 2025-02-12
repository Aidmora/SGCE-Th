package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obj.Paciente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ConsultarPaciente {

    @FXML
    private Button btnCerrarSesion;
    @FXML
    private MenuItem btnRegistroPaciente;
    @FXML
    private MenuItem btnActualizarPaciente;
    @FXML
    private TextField txtMesNacimiento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumCedula;

    @FXML
    private ListView<Paciente> listViewPacientes;

    @FXML
    private MenuItem btnRegistroTratamiento;
    @FXML
    private MenuItem btnConsultaTratamiento;

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
    void actionRegistroCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/RegistrarCita.fxml");
    }
    @FXML
    void actionConsultarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ConsultarCita.fxml");
    }
    @FXML
    void actionActualizarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ActualizarCita.fxml");
    }
    @FXML
    void initialize() {
        try {
            ConexionBD cbd = new ConexionBD();
            cbd.conectar();
            List<Paciente> pacientes = cbd.getPacientes();
            ObservableList<Paciente> observablePacientes = FXCollections.observableArrayList(pacientes);
            listViewPacientes.setItems(observablePacientes);
            listViewPacientes.setCellFactory(param -> new PacienteListCell());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml");
    }

    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");
    }

    private void navigateTo(String fxmlPath) throws IOException {
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.hide();
        Stage main = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        main.setScene(new Scene(root));
        main.show();
    }
    @FXML
    void actionRegistroPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }

    @FXML
    void actionBuscar(ActionEvent event) throws SQLException {
        if (txtNumCedula.getText().isEmpty() && txtNombre.getText().isEmpty() && txtMesNacimiento.getText().isEmpty()) {
            MensajeAlerta.mensaje("Ingrese un parámetro para buscar");
        } else if (!txtNumCedula.getText().isEmpty() && txtNombre.getText().isEmpty() && txtMesNacimiento.getText().isEmpty()) {
            ConexionBD cbd = new ConexionBD();
            cbd.conectar();
            Paciente pacienteBuscado = cbd.getPacientePorCedula(txtNumCedula.getText());
            cbd.cerrar();
            ObservableList<Paciente> observablePacientes;
            if (pacienteBuscado != null) {
                observablePacientes = FXCollections.observableArrayList(pacienteBuscado);
            } else {
                observablePacientes = FXCollections.observableArrayList();
                MensajeAlerta.mensaje("No se encuentran registros con la cédula ingresada");
            }
            listViewPacientes.setItems(observablePacientes);
            listViewPacientes.setCellFactory(param -> new PacienteListCell());
        } else if (txtNumCedula.getText().isEmpty() && !txtNombre.getText().isEmpty() && txtMesNacimiento.getText().isEmpty()) {
            ConexionBD cbd = new ConexionBD();
            cbd.conectar();
            List<Paciente> pacientes = cbd.getPacientesPorNombre(txtNombre.getText());

            ObservableList<Paciente> observablePacientes = FXCollections.observableArrayList(pacientes);
            listViewPacientes.setItems(observablePacientes);
            listViewPacientes.setCellFactory(param -> new PacienteListCell());

            if (pacientes.isEmpty()) {
                MensajeAlerta.mensaje("No se encuentran pacientes con ese nombre");
            }
        } else if (txtNumCedula.getText().isEmpty() && txtNombre.getText().isEmpty() && !txtMesNacimiento.getText().isEmpty()) {
            int mes = convertirMes(txtMesNacimiento.getText());
            if(mes != 13){
                ConexionBD cbd = new ConexionBD();
                cbd.conectar();
                List<Paciente> pacientes = cbd.getPacientesPorMes(mes);

                ObservableList<Paciente> observablePacientes = FXCollections.observableArrayList(pacientes);
                listViewPacientes.setItems(observablePacientes);
                listViewPacientes.setCellFactory(param -> new PacienteListCell());

                if (pacientes.isEmpty()) {
                    MensajeAlerta.mensaje("No se encuentran pacientes en ese mes de cumpleaños");
                }
            } else{
                MensajeAlerta.mensaje("No se encuentran pacientes en ese mes de cumpleaños");
            }
        } else{
            //MensajeAlerta.mensaje("Decida un paramatro por el que buscar");
        }
    }

    public int convertirMes(String mesTxt){
        return switch (mesTxt.toLowerCase()) {
            case "enero" -> 1;
            case "febrero" -> 2;
            case "marzo" -> 3;
            case "abril" -> 4;
            case "mayo" -> 5;
            case "junio" -> 6;
            case "julio" -> 7;
            case "agosto" -> 8;
            case "septiembre" -> 9;
            case "octubre" -> 10;
            case "noviembre" -> 11;
            case "diciembre" -> 12;
            default -> 13;
        };
    }
}
