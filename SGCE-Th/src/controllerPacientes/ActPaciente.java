package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import app.Validaciones;
import controller.LoginC;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obj.AntecedenteFamiliar;
import obj.AntecedentePersonal;
import obj.Medicamentos;
import obj.Paciente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class ActPaciente implements Initializable {


    @FXML
    private Button btnActualizarPaciente;

    @FXML
    private TextField txtBuscarCedula;

    @FXML
    private Button btnBuscarCedula;

    @FXML
    private MenuItem btnRegistroTratamiento;
    @FXML
    private MenuItem btnConsultaTratamiento;
    @FXML
    private MFXDatePicker dateFechaNac;
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

    private Paciente pacienteBuscado;
    private AntecedenteFamiliar afBuscado;
    private AntecedentePersonal apBuscado;
    private Medicamentos medBuscado;
    public static Paciente pacActualizado;

   @FXML
    void actionBuscarCedula(ActionEvent event) throws SQLException {
        ConexionBD cbd = new ConexionBD();
        cbd.conectar();
        String cedula = txtBuscarCedula.getText();
         pacienteBuscado = cbd.getPacientePorCedula(cedula);
         afBuscado = cbd.getAfConCedula(cedula);
         apBuscado = cbd.getAPConCedula(cedula);
         medBuscado = cbd.getMedicamentoCedula(cedula);
        cbd.cerrar();

        if (pacienteBuscado != null || afBuscado != null || apBuscado != null || medBuscado != null) {
            setPaciente(pacienteBuscado);
            setAF(afBuscado);
            setAP(apBuscado);
            setMedicamentos(medBuscado);
        } else {
            limpiarCampos();
            MensajeAlerta.mensaje("No existe un Paciente registrado con dicho número de cédula - vuelva a ingresar”");
        }
    }

    private void setPaciente(Paciente paciente) {
        txtNombre.setText(paciente.getNombresCompletos());
        txtCedula.setText(paciente.getNumeroCedulaIdentidad());
        txtCorreo.setText(paciente.getCorreoElectronico());
        txtDireccion.setText(paciente.getDireccionDomiciliaria());
        txtTelefono.setText(paciente.getNumeroDeTelefono());
        dateFechaNac.setValue(paciente.getFechaDeNacimiento());
    }

    private void setAF(AntecedenteFamiliar af) {
        txtAF1.setText(af.getNombre1());
        txtAF2.setText(af.getNombre2());
        txtAF3.setText(af.getNombre3());
        txtAF4.setText(af.getNombre4());
        txtAF5.setText(af.getNombre5());
        txtAFG1.setText(af.getGrado1() == 0 ? "" : af.getGrado1().toString());
        txtAFG2.setText(af.getGrado2() == 0 ? "" : String.valueOf(af.getGrado2()));
        txtAFG3.setText(af.getGrado3() == 0 ? "" : af.getGrado3().toString());
        txtAFG4.setText(af.getGrado4() == 0 ? "" : af.getGrado4().toString());
        txtAFG5.setText(af.getGrado5() == 0 ? "" : af.getGrado5().toString());
    }

    private void setAP(AntecedentePersonal ap) {
        txtAP1.setText(ap.getNombre1());
        txtAP2.setText(ap.getNombre2());
        txtAP3.setText(ap.getNombre3());
        txtAP4.setText(ap.getNombre4());
        txtAP5.setText(ap.getNombre5());
        txtAPT1.setText(ap.getTiempo1() == 0 ? "" : ap.getTiempo1().toString());
        txtAPT2.setText(ap.getTiempo2() == 0 ? "" : ap.getTiempo2().toString());
        txtAPT3.setText(ap.getTiempo3() == 0 ? "" : ap.getTiempo3().toString());
        txtAPT4.setText(ap.getTiempo4() == 0 ? "" : ap.getTiempo4().toString());
        txtAPT5.setText(ap.getTiempo5() == 0 ? "" : ap.getTiempo5().toString());
    }

    private void setMedicamentos(Medicamentos med) {
        txtMed1.setText(med.getNombre1());
        txtMed2.setText(med.getNombre2());
        txtMed3.setText(med.getNombre3());
        txtMedDosis1.setText(med.getDosis1());
        txtMedDosis2.setText(med.getDosis2());
        txtMedDosis3.setText(med.getDosis3());
        txtMedTiempo1.setText(med.getTiempo1() == 0 ? "" : med.getTiempo1().toString());
        txtMedTiempo2.setText(med.getTiempo2() == 0 ? "" : med.getTiempo2().toString());
        txtMedTiempo3.setText(med.getTiempo3() == 0 ? "" : med.getTiempo3().toString());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        dateFechaNac.setValue(null);

        txtAF1.setText("");
        txtAF2.setText("");
        txtAF3.setText("");
        txtAF4.setText("");
        txtAF5.setText("");
        txtAFG1.setText("");
        txtAFG2.setText("");
        txtAFG3.setText("");
        txtAFG4.setText("");
        txtAFG5.setText("");

        txtAP1.setText("");
        txtAP2.setText("");
        txtAP3.setText("");
        txtAP4.setText("");
        txtAP5.setText("");
        txtAPT1.setText("");
        txtAPT2.setText("");
        txtAPT3.setText("");
        txtAPT4.setText("");
        txtAPT5.setText("");

        txtMed1.setText("");
        txtMed2.setText("");
        txtMed3.setText("");
        txtMedDosis1.setText("");
        txtMedDosis2.setText("");
        txtMedDosis3.setText("");
        txtMedTiempo1.setText("");
        txtMedTiempo2.setText("");
        txtMedTiempo3.setText("");
    }


    @FXML
    void actionActualizarPaciente(ActionEvent event) throws SQLException { //ACT AP AF Y M!!!
        try{
            String direccion = txtDireccion.getText();
            String correo = txtCorreo.getText();
            String numTelefono = txtTelefono.getText();
            LocalDate fechaCreacion = pacienteBuscado.getFechaCreacion();
            int ap = pacienteBuscado.getAntPersonal();
            int af = pacienteBuscado.getAntFamiliar();
            int m = pacienteBuscado.getMedicamento();
            String nombre;
            String cedula;
            LocalDate fechaNac;
            if (LoginC.rol.equals("Administración")) {
                nombre = txtNombre.getText();
                cedula = txtCedula.getText();
                fechaNac = dateFechaNac.getValue();
            } else {
                nombre = pacienteBuscado.getNombresCompletos();
                cedula = pacienteBuscado.getNumeroCedulaIdentidad();
                fechaNac = pacienteBuscado.getFechaDeNacimiento();
            }
            pacActualizado = new Paciente(nombre, cedula, direccion, numTelefono, correo, fechaNac, fechaCreacion, ap, af, m);

            //Actualizar AF
            String af1 = txtAF1.getText();
            int afG1 = Objects.equals(txtAFG1.getText(), "") ? 0 : Integer.parseInt(txtAFG1.getText());
            String af2 = txtAF2.getText();
            int afG2 = Objects.equals(txtAFG2.getText(), "") ? 0 : Integer.parseInt(txtAFG2.getText());
            String af3 = txtAF3.getText();
            int afG3 = Objects.equals(txtAFG3.getText(), "") ? 0 : Integer.parseInt(txtAFG3.getText());
            String af4 = txtAF4.getText();
            int afG4 = Objects.equals(txtAFG4.getText(), "") ? 0 : Integer.parseInt(txtAFG4.getText());
            String af5 = txtAF5.getText();
            int afG5 = Objects.equals(txtAFG5.getText(), "") ? 0 : Integer.parseInt(txtAFG5.getText());
            AntecedenteFamiliar afActualizado = new AntecedenteFamiliar(af1, afG1, af2, afG2, af3, afG3, af4, afG4, af5, afG5);

            //Actualizar AP
            String ap1 = txtAP1.getText();
            float apt1 = Objects.equals(txtAPT1.getText(), "") ? 0 : Float.parseFloat(txtAPT1.getText());
            String ap2 = txtAP2.getText();
            float apt2 = Objects.equals(txtAPT2.getText(), "") ? 0 : Float.parseFloat(txtAPT2.getText());
            String ap3 = txtAP3.getText();
            float apt3 = Objects.equals(txtAPT3.getText(), "") ? 0 : Float.parseFloat(txtAPT3.getText());
            String ap4 = txtAP4.getText();
            float apt4 = Objects.equals(txtAPT4.getText(), "") ? 0 : Float.parseFloat(txtAPT4.getText());
            String ap5 = txtAP5.getText();
            float apt5 = Objects.equals(txtAPT5.getText(), "") ? 0 : Float.parseFloat(txtAPT5.getText());
            AntecedentePersonal apActualizado = new AntecedentePersonal(ap1, apt1, ap2, apt2, ap3, apt3, ap4, apt4, ap5, apt5);

            //Actualizar medicinas
            String m1 = txtMed1.getText();
            String md1 = txtMedDosis1.getText();
            float mt1 = Objects.equals(txtMedTiempo1.getText(), "") ? 0 : Float.parseFloat(txtMedTiempo1.getText());
            String m2 = txtMed2.getText();
            String md2 = txtMedDosis2.getText();
            float mt2 = Objects.equals(txtMedTiempo2.getText(), "") ? 0 : Float.parseFloat(txtMedTiempo2.getText());
            String m3 = txtMed3.getText();
            String md3 = txtMedDosis3.getText();
            float mt3 = Objects.equals(txtMedTiempo3.getText(), "") ? 0 : Float.parseFloat(txtMedTiempo3.getText());
            Medicamentos medActualizado = new Medicamentos(m1, md1, mt1, m2, md2, mt2, m3, md3, mt3);


            ConexionBD cbd = new ConexionBD();
            cbd.conectar();
            boolean actualizadoPaciente = cbd.actualizarPaciente(pacActualizado, pacienteBuscado.getNumeroCedulaIdentidad());
            boolean actualizadoAF = cbd.actualizarAF(afActualizado, pacienteBuscado.getNumeroCedulaIdentidad());
            boolean actualizadoAP = cbd.actualizarAP(apActualizado, pacienteBuscado.getNumeroCedulaIdentidad());
            boolean actualizadoMedicamento = cbd.actualizarMedicamento(medActualizado,pacienteBuscado.getNumeroCedulaIdentidad());
            cbd.cerrar();

            if(actualizadoPaciente || actualizadoAF || actualizadoAP || actualizadoMedicamento){
                MensajeAlerta.exitoso("Paciente actualizado con éxito");
                limpiarCampos();
            }
        }catch (NullPointerException e){
            MensajeAlerta.mensaje("Número de cédula de identidad no válida - vuelva a ingresar");
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnBuscarCedula.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String rol = LoginC.rol;
        if(!rol.equals("Administración")){
            txtNombre.setDisable(true);
            txtCedula.setDisable(true);
            dateFechaNac.setDisable(true);
        }
        txtCedula.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String cedula = txtCedula.getText();
                if (!nuevo) {
                    if(txtCedula.getText().isEmpty())
                        return;
                    if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Número de cédula no válido - vuelva a ingresar");
                        return;}
                }
            }
        });
        txtBuscarCedula.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String cedula = txtBuscarCedula.getText();
                if (!nuevo) {
                    if(txtBuscarCedula.getText().isEmpty())
                        return;
                    if(!Validaciones.validarCedula(cedula)){MensajeAlerta.mensaje("Número de cédula no válido - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtNombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String nombre = txtNombre.getText();
                if (!nuevo) {
                    if(txtNombre.getText().isEmpty())
                        return;
                    if(!Validaciones.validarNombre(nombre)){MensajeAlerta.mensaje("Nombre no válido - vuelva a ingresar") ;
                        return;}
                }
            }
        });

        txtTelefono.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String telefono = txtTelefono.getText();
                if (!nuevo) {
                    if(txtTelefono.getText().isEmpty())
                        return;
                    if(!Validaciones.validarTelefono(telefono)){MensajeAlerta.mensaje("Número de teléfono móvil no válido - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtDireccion.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String direccion = txtDireccion.getText();
                if (!nuevo) {
                    if(txtDireccion.getText().isEmpty())
                        return;
                    if(!Validaciones.validarDireccion(direccion)){MensajeAlerta.mensaje("Dirección domiciliaria no válida - vuelva a ingresar");
                        return;}
                }
            }
        });

        txtCorreo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String correo = txtCorreo.getText();
                if (!nuevo) {
                    if(txtCorreo.getText().isEmpty())
                        return;
                    if(!Validaciones.validarCorreo(correo)){MensajeAlerta.mensaje("Correo electrónico no válido - vuelva a ingresar");
                        return;}
                }
            }
        });
        //Validaciones.cambiarForamtoFecha(dateFechaNac);
        dateFechaNac.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!Validaciones.validarFechaNac(dateFechaNac)){MensajeAlerta.mensaje("Seleccione la fecha de nacimiento");
                    return;}
            }
        });
        validarAntecedentesP(txtAP1, txtAPT1);
        validarAntecedentesP(txtAP2, txtAPT2);
        validarAntecedentesP(txtAP3, txtAPT3);
        validarAntecedentesP(txtAP4, txtAPT4);
        validarAntecedentesP(txtAP5, txtAPT5);
        validarAntecedentesF(txtAF1, txtAFG1);
        validarAntecedentesF(txtAF2, txtAFG2);
        validarAntecedentesF(txtAF3, txtAFG3);
        validarAntecedentesF(txtAF4, txtAFG4);
        validarAntecedentesF(txtAF5, txtAFG5);
        validarMedicamentos(txtMed1, txtMedDosis1, txtMedTiempo1);
        validarMedicamentos(txtMed2, txtMedDosis2, txtMedTiempo2);
        validarMedicamentos(txtMed3, txtMedDosis3, txtMedTiempo3);

    }


    private void validarAntecedentesP(TextField txtAntPersonal, TextField txtTiempoAP) {

        txtAntPersonal.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String antPersonal = txtAntPersonal.getText();
                if (!nuevo) {
                    if(txtAntPersonal.getText().isEmpty())
                        return;
                    if (!Validaciones.validarNombreA(antPersonal)) {
                        MensajeAlerta.mensaje("Nombre del antecedente personal no válido- vuelva a ingresar");
                    }
                }
            }
        });
        txtTiempoAP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String tiempoPersonal = txtTiempoAP.getText();
                if (!nuevo) {
                    if(txtTiempoAP.getText().isEmpty())
                        return;
                    if (!Validaciones.validarTiempoAP(tiempoPersonal)) {
                        MensajeAlerta.mensaje("Tiempo del antecedente personal no válido- vuelva a ingresar");
                    }
                }
            }
        });
    }
    private void validarAntecedentesF(TextField txtAntFamiliar, TextField txtGradoConsanginidad) {
        txtAntFamiliar.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String antFamiliar = txtAntFamiliar.getText();
                if (!nuevo) {
                    if(txtAntFamiliar.getText().isEmpty())
                        return;
                    if (!Validaciones.validarNombreA(antFamiliar)) {
                        MensajeAlerta.mensaje("Nombre del antecedente familiar no válido- vuelva a ingresar");
                    }
                }
            }
        });
        txtGradoConsanginidad.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String grado = txtGradoConsanginidad.getText();
                if (!nuevo) {
                    if(txtGradoConsanginidad.getText().isEmpty())
                        return;
                    if (!Validaciones.validarGradoCon(grado)) {
                        MensajeAlerta.mensaje("Grado de consanguinidad no válido- vuelva a ingresar");
                    }
                }
            }
        });
    }
    private void validarMedicamentos(TextField txtNombre, TextField txtDosis, TextField txtTiempo) {
        txtNombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String nombre = txtNombre.getText();
                if (!nuevo) {
                    if(txtNombre.getText().isEmpty())
                        return;
                    if (!Validaciones.validarNombreM(nombre)) {
                        MensajeAlerta.mensaje("Nombre del medicamento no válido- vuelva a ingresar");
                    }
                }
            }
        });

        txtDosis.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String dosis = txtDosis.getText();
                if (!nuevo) {
                    if(txtDosis.getText().isEmpty())
                        return;
                    if (!Validaciones.validarDosis(dosis)) {
                        MensajeAlerta.mensaje("Dosis del medicamento no válida- vuelva a ingresar");
                    }
                }
            }
        });

        txtTiempo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean antiguo, Boolean nuevo) {
                String tiempo = txtTiempo.getText();
                if (!nuevo) {
                    if(txtTiempo.getText().isEmpty())
                        return;
                    if (!Validaciones.validarTiempoAP(tiempo)) {
                        MensajeAlerta.mensaje("Tiempo del medicamento no válido- vuelva a ingresar");
                    }
                }
            }
        });
    }
}
