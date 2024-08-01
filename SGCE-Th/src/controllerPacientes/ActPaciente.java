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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obj.AntecedenteFamiliar;
import obj.AntecedentePersonal;
import obj.Medicamentos;
import obj.Paciente;

import java.io.IOException;
import java.sql.SQLException;

public class ActPaciente {

    @FXML
    private MenuItem btnConsultaPaciente;

    @FXML
    private TextField txtBuscarCedula;

    @FXML
    private Button btnBuscarCedula;

    @FXML
    private MenuItem btnRegistroPaciente;

    @FXML
    private Button btnCerrarSesion;
    @FXML
    private MenuItem btnRegistroTratamiento;
    @FXML
    private MenuItem btnConsultaTratamiento;
    @FXML
    private DatePicker dateFechaNac;
    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtAF1;

    @FXML
    private TextField txtAF2;

    @FXML
    private TextField txtAF3;

    @FXML
    private TextField txtAF4;

    @FXML
    private TextField txtAF5;

    @FXML
    private TextField txtAFG1;

    @FXML
    private TextField txtAFG2;

    @FXML
    private TextField txtAFG3;

    @FXML
    private TextField txtAFG4;

    @FXML
    private TextField txtAFG5;

    @FXML
    private TextField txtAP1;

    @FXML
    private TextField txtAP2;

    @FXML
    private TextField txtAP3;

    @FXML
    private TextField txtAP4;

    @FXML
    private TextField txtAP5;

    @FXML
    private TextField txtAPT1;

    @FXML
    private TextField txtAPT2;

    @FXML
    private TextField txtAPT3;

    @FXML
    private TextField txtAPT4;

    @FXML
    private TextField txtAPT5;

    @FXML
    private TextField txtMed1;

    @FXML
    private TextField txtMed2;

    @FXML
    private TextField txtMed3;

    @FXML
    private TextField txtMedDosis1;

    @FXML
    private TextField txtMedDosis2;

    @FXML
    private TextField txtMedDosis3;

    @FXML
    private TextField txtMedTiempo1;

    @FXML
    private TextField txtMedTiempo2;

    @FXML
    private TextField txtMedTiempo3;

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
    void actionRegistroPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }

    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
    }

    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml");
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
    void actionActualizarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ActualizarCita.fxml");
    }
    @FXML
    void actionConsultarCita(ActionEvent event) throws IOException {
        navigateTo("/fxml/Citas/ConsultarCita.fxml");
    }
    @FXML
    void actionBuscarCedula(ActionEvent event) throws SQLException {
        ConexionBD cbd = new ConexionBD();
        cbd.conectar();
        Paciente pacienteBuscado = cbd.getPacientePorCedula(txtBuscarCedula.getText());
        AntecedenteFamiliar afBuscado = cbd.getAfConCedula(txtBuscarCedula.getText());
        AntecedentePersonal apBuscado = cbd.getAPConCedula(txtBuscarCedula.getText());
        Medicamentos medBuscado = cbd.getMedicamentoCedula(txtBuscarCedula.getText());
        cbd.cerrar();

        if (pacienteBuscado != null) {
            txtNombre.setText(pacienteBuscado.getNombresCompletos());
            txtCedula.setText(pacienteBuscado.getNumeroCedulaIdentidad());
            txtCorreo.setText(pacienteBuscado.getCorreoElectronico());
            txtDireccion.setText(pacienteBuscado.getDireccionDomiciliaria());
            txtTelefono.setText(pacienteBuscado.getNumeroDeTelefono());
            dateFechaNac.setValue(pacienteBuscado.getFechaDeNacimiento());

            txtAF1.setText(afBuscado.getNombre1());
            txtAF2.setText(afBuscado.getNombre2());
            txtAF3.setText(afBuscado.getNombre3());
            txtAF4.setText(afBuscado.getNombre4());
            txtAF5.setText(afBuscado.getNombre5());
            txtAFG1.setText(afBuscado.getGrado1() == 0 ? "" : afBuscado.getGrado1().toString());
            txtAFG2.setText(afBuscado.getGrado2() == 0 ? "" : String.valueOf(afBuscado.getGrado2()));
            txtAFG3.setText(afBuscado.getGrado3() == 0 ? "" : afBuscado.getGrado3().toString());
            txtAFG4.setText(afBuscado.getGrado4() == 0 ? "" : afBuscado.getGrado4().toString());
            txtAFG5.setText(afBuscado.getGrado5() == 0 ? "" : afBuscado.getGrado5().toString());

            txtAP1.setText(apBuscado.getNombre1());
            txtAP2.setText(apBuscado.getNombre2());
            txtAP3.setText(apBuscado.getNombre3());
            txtAP4.setText(apBuscado.getNombre4());
            txtAP5.setText(apBuscado.getNombre5());
            txtAPT1.setText(apBuscado.getTiempo1() == 0 ? "" : apBuscado.getTiempo1().toString());
            txtAPT2.setText(apBuscado.getTiempo2() == 0 ? "" : apBuscado.getTiempo2().toString());
            txtAPT3.setText(apBuscado.getTiempo3() == 0 ? "" : apBuscado.getTiempo3().toString());
            txtAPT4.setText(apBuscado.getTiempo4() == 0 ? "" : apBuscado.getTiempo4().toString());
            txtAPT5.setText(apBuscado.getTiempo5() == 0 ? "" : apBuscado.getTiempo5().toString());

            txtMed1.setText(medBuscado.getNombre1());
            txtMed2.setText(medBuscado.getNombre2());
            txtMed3.setText(medBuscado.getNombre3());
            txtMedDosis1.setText(medBuscado.getDosis1());
            txtMedDosis2.setText(medBuscado.getDosis2());
            txtMedDosis3.setText(medBuscado.getDosis3());
            txtMedTiempo1.setText(medBuscado.getTiempo1() == 0 ? "" : medBuscado.getTiempo1().toString());
            txtMedTiempo2.setText(medBuscado.getTiempo2() == 0 ? "" : medBuscado.getTiempo2().toString());
            txtMedTiempo3.setText(medBuscado.getTiempo3() == 0 ? "" : medBuscado.getTiempo3().toString());

        } else {
            txtNombre.setText("");
            txtCedula.setText("");
            txtCorreo.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            dateFechaNac.setValue(null);
            MensajeAlerta.mensaje("No se encuentran registros con la cédula ingresada");
        }
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
