package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import app.Validaciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obj.Paciente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsultarPaciente implements Initializable {

    @FXML
    private TextField txtMesNacimiento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumCedula;

    @FXML
    private ListView<Paciente> listViewPacientes;

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

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) txtMesNacimiento.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                MensajeAlerta.mensaje("No existe un Paciente registrado con dicho número de cédula de identidad - vuelva a ingresar");
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
                MensajeAlerta.mensaje("No existen Pacientes registrados con dicho nombre - vuelva a ingresar");
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
                    MensajeAlerta.mensaje("No existen Pacientes registrados con ese mes - vuelva a ingresar");
                }
            } else{
                MensajeAlerta.mensaje("No existen Pacientes registrados con ese mes - vuelva a ingresar");
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String nombreStr = txtNombre.getText();
                if (!nuevo) {
                    if(!Validaciones.validarNombre(nombreStr)){MensajeAlerta.mensaje("Nombre no válido - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtMesNacimiento.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String mesStr = txtMesNacimiento.getText();
                if (!nuevo) {
                    if(!Validaciones.validarMesNac(mesStr)){MensajeAlerta.mensaje("Mes no válido - vuelva a ingresar");
                        return;}
                }
            }
        });
        txtNumCedula.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String numCedulaText = txtNumCedula.getText();
                if (!nuevo) {
                    if(!Validaciones.validarCedula(numCedulaText)){MensajeAlerta.mensaje("Número de cédula de identidad no válido - vuelva a ingresar");
                        return;}
                }
            }
        });
    }
}
