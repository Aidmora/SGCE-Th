package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import app.TempDataStore;
import app.Validaciones;
import controller.LoginC;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

import javafx.util.StringConverter;
import obj.Paciente;


public class RegPaciente implements Initializable {
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
    private MFXDatePicker dateFechaNac;

    private static List<Paciente> listaPacientes = new ArrayList<>();

    @FXML
    void actionHistorialClinico(ActionEvent event) throws IOException {
        try {
            saveData();
            navigateTo("/fxml/Pacientes/RegistrarHistorialMedico.fxml");
        }catch (NullPointerException e){
            MensajeAlerta.mensaje("Ingrese los datos del Paciente");
        }

    }
    private void saveData() {
        TempDataStore.nombrePaciente = txtFieldNombrePaciente.getText();
        TempDataStore.cedulaPaciente = txtFieldCedulaPaciente.getText();
        TempDataStore.correoPaciente = txtFieldCorreoPaciente.getText();
        TempDataStore.direccionPaciente = txtFieldDireccionPaciente.getText();
        TempDataStore.fechaNacimientoPaciente = dateFechaNac.getValue().toString();
        TempDataStore.telefonoPaciente = txtFieldTelefonoPaciente.getText();
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnHistorialClinico.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        //Validaciones.cambiarForamtoFecha(dateFechaNac);
        dateFechaNac.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!Validaciones.validarFechaNac(dateFechaNac)){
                    MensajeAlerta.mensaje("Seleccione la fecha de nacimiento");
                }
            }
        });

        txtFieldCedulaPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String cedula = txtFieldCedulaPaciente.getText();
                if (!nuevo) {
                    if(txtFieldCedulaPaciente.getText().isEmpty())
                        return;
                    if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Número de cédula de identidad no válido - vuelva a ingresar");
                        return;}
                }
            }
        });
        txtFieldNombrePaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String nombre = txtFieldNombrePaciente.getText();
                if (!nuevo) {
                    if(txtFieldNombrePaciente.getText().isEmpty())
                        return;
                    if(!Validaciones.validarNombre(nombre)){MensajeAlerta.mensaje("Nombre no válido - vuelva a ingresar") ;
                        return;}
                }
            }
        });

        txtFieldTelefonoPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String telefono = txtFieldTelefonoPaciente.getText();
                if (!nuevo) {
                    if(txtFieldTelefonoPaciente.getText().isEmpty())
                        return;
                    if(!Validaciones.validarTelefono(telefono)){MensajeAlerta.mensaje("Número de teléfono móvil no válido - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtFieldDireccionPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String direccion = txtFieldDireccionPaciente.getText();
                if (!nuevo) {
                    if(txtFieldDireccionPaciente.getText().isEmpty())
                        return;
                    if(!Validaciones.validarDireccion(direccion)){MensajeAlerta.mensaje("Dirección domiciliaria no válida - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtFieldCorreoPaciente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String correo = txtFieldCorreoPaciente.getText();
                if (!nuevo) {
                    if(txtFieldCorreoPaciente.getText().isEmpty())
                        return;
                    if(!Validaciones.validarCorreo(correo)){MensajeAlerta.mensaje("Correo electrónico no válido - vuelva a ingresar");
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
            List<String> cedulas= new ArrayList<>();
            try {
                cedulas= cbd.getPacienteCedulas();
            }catch (SQLException e){
            }

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

            if(!Validaciones.validarNombre(nombre)){MensajeAlerta.mensaje("Nombre no válido - vuelva a ingresar") ;
                return;}
            if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Número de cédula de identidad no válida - vuelva a ingresar");
                return;}
            if(!Validaciones.validarDireccion(direccion)){MensajeAlerta.mensaje("Dirección no válida");
                return;}
            if(!validarCedulaDuplicada(cedulas,cedula)){MensajeAlerta.mensaje("Número de cédula de identidad ya registrado - vuelva a ingresar");
                return;
            }
            if(!Validaciones.validarTelefono(telefono)){MensajeAlerta.mensaje("Número de teléfono móvil no válido - vuelva a ingresar");
                return;}
            if(!Validaciones.validarCorreo(correo)){MensajeAlerta.mensaje("Correo electrónico no válido - vuelva a ingresar");
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
                alertarMostrarConsulta(event);
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

    private void alertarMostrarConsulta(ActionEvent event) throws IOException {
        MensajeAlerta.mensaje("Paciente registrado exitosamente");
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Pacientes/ConsultarPaciente.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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