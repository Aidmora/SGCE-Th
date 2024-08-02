package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import app.TempDataStore;
import app.Validaciones;
import controller.LoginC;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import obj.Paciente;


public class RegPaciente implements Initializable {
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnHistorialClinico;

    @FXML
    private TextField txtFieldCedulaPaciente;

    @FXML
    private TextField txtFieldCorreoPaciente;

    @FXML
    private TextField txtFieldDireccionPaciente;



    @FXML
    private TextField txtFieldNombrePaciente;

    @FXML
    private TextField txtFieldTelefonoPaciente;
    @FXML
    private DatePicker dateFechaNac;

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
    private static List<Paciente> listaPacientes = new ArrayList<>();

    @FXML
    public void initialize() {

    }

    @FXML
    void actionCerrarSesion(ActionEvent event) throws IOException {
        navigateTo("/fxml/Login.fxml", (Stage) btnCerrarSesion.getScene().getWindow());
    }

    @FXML
    void actionHistorialClinico(ActionEvent event) throws IOException {
        saveData();
        navigateTo("/fxml/Pacientes/RegistrarHistorialMedico.fxml", (Stage) btnHistorialClinico.getScene().getWindow());
    }


    private void saveData() {
        TempDataStore.nombrePaciente = txtFieldNombrePaciente.getText();
        TempDataStore.cedulaPaciente = txtFieldCedulaPaciente.getText();
        TempDataStore.correoPaciente = txtFieldCorreoPaciente.getText();
        TempDataStore.direccionPaciente = txtFieldDireccionPaciente.getText();
        TempDataStore.fechaNacimientoPaciente = dateFechaNac.getValue().toString();
        TempDataStore.telefonoPaciente = txtFieldTelefonoPaciente.getText();
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
    @FXML
    void actionConsultaPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ConsultarPaciente.fxml");
    }

    @FXML
    void actionActualizarPaciente(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/ActualizarPaciente.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (TempDataStore.nombrePaciente != null) txtFieldNombrePaciente.setText(TempDataStore.nombrePaciente);
        if (TempDataStore.cedulaPaciente != null) txtFieldCedulaPaciente.setText(TempDataStore.cedulaPaciente);
        if (TempDataStore.correoPaciente != null) txtFieldCorreoPaciente.setText(TempDataStore.correoPaciente);
        if (TempDataStore.direccionPaciente != null) txtFieldDireccionPaciente.setText(TempDataStore.direccionPaciente);
        if (TempDataStore.fechaNacimientoPaciente != null) dateFechaNac.setValue(LocalDate.parse(TempDataStore.fechaNacimientoPaciente));
        if (TempDataStore.telefonoPaciente != null) txtFieldTelefonoPaciente.setText(TempDataStore.telefonoPaciente);

        String rol = LoginC.rol;
        System.out.println(LoginC.rol);

        txtFieldCedulaPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String cedula = txtFieldCedulaPaciente.getText();
                if (!nuevo) {
                    if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Cédula no válida");
                        return;}
                }
            }
        });
        txtFieldNombrePaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String nombre = txtFieldNombrePaciente.getText();
                if (!nuevo) {
                    if(!Validaciones.validarNombre(nombre)){MensajeAlerta.mensaje("Nombre no válido") ;
                        return;}
                }
            }
        });

        txtFieldTelefonoPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String telefono = txtFieldTelefonoPaciente.getText();
                if (!nuevo) {
                    if(!Validaciones.validarTelefono(telefono)){MensajeAlerta.mensaje("Teléfono no válido");
                        return;}
                }
            }
        });

        txtFieldDireccionPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String direccion = txtFieldDireccionPaciente.getText();
                if (!nuevo) {
                    if(!Validaciones.validarDireccion(direccion)){MensajeAlerta.mensaje("Dirección no válida");
                        return;}
                }
            }
        });

        txtFieldCorreoPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String correo = txtFieldCorreoPaciente.getText();
                if (!nuevo) {
                    if(!Validaciones.validarCorreo(correo)){MensajeAlerta.mensaje("Correo no válido");
                        return;}
                }
            }
        });
    }


    @FXML
    void actionAddPaciente(ActionEvent event) {
        try {
            String nombre = txtFieldNombrePaciente.getText();
            String cedula = txtFieldCedulaPaciente.getText();
            String direccion = txtFieldDireccionPaciente.getText();
            String telefono = txtFieldTelefonoPaciente.getText();
            String correo = txtFieldCorreoPaciente.getText();
            LocalDate fechaNacimiento = dateFechaNac.getValue();
            LocalDate fechaCreacion = LocalDate.now();
            ConexionBD cbd = new ConexionBD();
            cbd.conectar();
            int antPersonalId = cbd.getIDAntecedentes();
            int antFamiliarId = cbd.getIDAF();
            int medicamentoId = cbd.getIDMedicamento();
            List<String> cedulas = cbd.getPacienteCedulas();
            Paciente nuevoPaciente = new Paciente(
                    nombre,
                    cedula,
                    direccion,
                    telefono,
                    correo,
                    fechaNacimiento,
                    fechaCreacion,
                    antPersonalId,
                    antFamiliarId,
                    medicamentoId
            );
            cbd.cerrar();

            if(!Validaciones.validarNombre(nombre)){MensajeAlerta.mensaje("Nombre no válido") ;
                return;}
            if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Cédula no válida");
                return;}
            if(!Validaciones.validarDireccion(direccion)){MensajeAlerta.mensaje("Dirección no válida");
                return;}
            if(!validarCedulaDuplicada(cedulas,cedula)){MensajeAlerta.mensaje("Número de cédula ya registrado");
                return;
            }
            if(!Validaciones.validarTelefono(telefono)){MensajeAlerta.mensaje("Teléfono no válido");
                return;}
            if(!Validaciones.validarCorreo(correo)){MensajeAlerta.mensaje("Correo no válido");
                return;}
            if(!RegHistorialMedico.correcto){
                MensajeAlerta.mensaje("Añadir historial clínico");
                return;
            }

            listaPacientes.add(nuevoPaciente);

            ConexionBD conexionBD = new ConexionBD();
            conexionBD.conectar();
            boolean guardado = conexionBD.guardarPaciente(nuevoPaciente);
            conexionBD.cerrar();

            if (guardado) {
                System.out.println("Paciente guardado exitosamente.");
                MensajeAlerta.exitoso("Paciente registrado exitosamente");
                txtFieldNombrePaciente.setText(" ");
                dateFechaNac.setValue(null);
                txtFieldCorreoPaciente.setText(" ");
                txtFieldDireccionPaciente.setText(" ");
                txtFieldTelefonoPaciente.setText(" ");
                txtFieldCedulaPaciente.setText(" ");
                TempDataStore.nombrePaciente = null;
                TempDataStore.cedulaPaciente = null;
                TempDataStore.correoPaciente = null;
                TempDataStore.direccionPaciente = null;
                TempDataStore.fechaNacimientoPaciente = null;
                TempDataStore.telefonoPaciente = null;
            } else {
                System.out.println("Error al guardar el paciente en la base de datos.");
            }

        }
        catch (Exception e) {
            System.out.println("Error al guardar el paciente.");
            e.printStackTrace();
        }
    }

    public boolean validarCedulaDuplicada(List<String> cedulasBD, String cedula){
        boolean duplicado = true;
        for(String cedulaBD : cedulasBD){
            if (cedulaBD.equals(cedula)) {
                duplicado = false;
                break;
            }
        }
        return duplicado;
    }
}


