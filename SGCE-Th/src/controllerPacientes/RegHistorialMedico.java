package controllerPacientes;

import alertas.MensajeAlerta;
import app.ConexionBD;
import app.Validaciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obj.AntecedenteFamiliar;
import obj.AntecedentePersonal;
import obj.Medicamentos;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class RegHistorialMedico implements Initializable {

    @FXML
    private Button btnAddHistorialClinico;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtFieldAntFamiliar1;

    @FXML
    private TextField txtFieldAntFamiliar2;

    @FXML
    private TextField txtFieldAntFamiliar3;

    @FXML
    private TextField txtFieldAntFamiliar4;

    @FXML
    private TextField txtFieldAntFamiliar5;

    @FXML
    private TextField txtFieldAntPersonal1;

    @FXML
    private TextField txtFieldAntPersonal2;

    @FXML
    private TextField txtFieldAntPersonal3;

    @FXML
    private TextField txtFieldAntPersonal4;

    @FXML
    private TextField txtFieldAntPersonal5;

    @FXML
    private TextField txtFieldGradoConsanginidad1;

    @FXML
    private TextField txtFieldGradoConsanginidad2;

    @FXML
    private TextField txtFieldGradoConsanginidad3;

    @FXML
    private TextField txtFieldGradoConsanginidad4;

    @FXML
    private TextField txtFieldGradoConsanginidad5;

    @FXML
    private TextField txtFieldMedicinaDosis1;

    @FXML
    private TextField txtFieldMedicinaDosis2;

    @FXML
    private TextField txtFieldMedicinaDosis3;

    @FXML
    private TextField txtFieldMedicinaNombre1;

    @FXML
    private TextField txtFieldMedicinaNombre2;

    @FXML
    private TextField txtFieldMedicinaNombre3;

    @FXML
    private TextField txtFieldMedicinaTiempo1;

    @FXML
    private TextField txtFieldMedicinaTiempo2;

    @FXML
    private TextField txtFieldMedicinaTiempo3;

    @FXML
    private TextField txtFieldTiempoAP1;

    @FXML
    private TextField txtFieldTiempoAP2;

    @FXML
    private TextField txtFieldTiempoAP3;

    @FXML
    private TextField txtFieldTiempoAP4;

    @FXML
    private TextField txtFieldTiempoAP5;

    public static boolean correcto = false;

    @FXML
    void actionAddHistorialClinico(ActionEvent event) {
        try {
            String antPersonal1 = txtFieldAntPersonal1.getText();
            Float antPersonalTiempo1 = parseFloatOrNull(txtFieldTiempoAP1.getText());
            String antPersonal2 = txtFieldAntPersonal2.getText();
            Float antPersonalTiempo2 = parseFloatOrNull(txtFieldTiempoAP2.getText());
            String antPersonal3 = txtFieldAntPersonal3.getText();
            Float antPersonalTiempo3 = parseFloatOrNull(txtFieldTiempoAP3.getText());
            String antPersonal4 = txtFieldAntPersonal4.getText();
            Float antPersonalTiempo4 = parseFloatOrNull(txtFieldTiempoAP4.getText());
            String antPersonal5 = txtFieldAntPersonal5.getText();
            Float antPersonalTiempo5 = parseFloatOrNull(txtFieldTiempoAP5.getText());

            String antFamiliar1 = txtFieldAntFamiliar1.getText();
            Integer antFamiliarGrado1 = parseIntOrNull(txtFieldGradoConsanginidad1.getText());
            String antFamiliar2 = txtFieldAntFamiliar2.getText();
            Integer antFamiliarGrado2 = parseIntOrNull(txtFieldGradoConsanginidad2.getText());
            String antFamiliar3 = txtFieldAntFamiliar3.getText();
            Integer antFamiliarGrado3 = parseIntOrNull(txtFieldGradoConsanginidad3.getText());
            String antFamiliar4 = txtFieldAntFamiliar4.getText();
            Integer antFamiliarGrado4 = parseIntOrNull(txtFieldGradoConsanginidad4.getText());
            String antFamiliar5 = txtFieldAntFamiliar5.getText();
            Integer antFamiliarGrado5 = parseIntOrNull(txtFieldGradoConsanginidad5.getText());

            String med1 = txtFieldMedicinaNombre1.getText();
            String medDosis1 = txtFieldMedicinaDosis1.getText();
            Float medTiempo1 = parseFloatOrNull(txtFieldMedicinaTiempo1.getText());
            String med2 = txtFieldMedicinaNombre2.getText();
            String medDosis2 = txtFieldMedicinaDosis2.getText();
            Float medTiempo2 = parseFloatOrNull(txtFieldMedicinaTiempo2.getText());
            String med3 = txtFieldMedicinaNombre3.getText();
            String medDosis3 = txtFieldMedicinaDosis3.getText();
            Float medTiempo3 = parseFloatOrNull(txtFieldMedicinaTiempo3.getText());
            if (!validateMedicamento(med1, medDosis1, medTiempo1, med2, medDosis2, medTiempo2, med3, medDosis3, medTiempo3) || !validateAntecedentePersonal(antPersonal1, antPersonalTiempo1, antPersonal2, antPersonalTiempo2, antPersonal3, antPersonalTiempo3, antPersonal4, antPersonalTiempo4, antPersonal5, antPersonalTiempo5) || !validateAntecedenteFamiliar(antFamiliar1, antFamiliarGrado1, antFamiliar2, antFamiliarGrado2, antFamiliar3, antFamiliarGrado3, antFamiliar4, antFamiliarGrado4, antFamiliar5, antFamiliarGrado5)) {

                MensajeAlerta.mensaje("Error en el registro del Historial clínico");
                return;
            }

            AntecedentePersonal nuevoAP = new AntecedentePersonal(
                    antPersonal1, antPersonalTiempo1, antPersonal2, antPersonalTiempo2,
                    antPersonal3, antPersonalTiempo3, antPersonal4, antPersonalTiempo4, antPersonal5,
                    antPersonalTiempo5
            );

            AntecedenteFamiliar nuevoAF = new AntecedenteFamiliar(
                    antFamiliar1, antFamiliarGrado1, antFamiliar2, antFamiliarGrado2, antFamiliar3,antFamiliarGrado3, antFamiliar4, antFamiliarGrado4,antFamiliar5, antFamiliarGrado5
            );

            Medicamentos medicamento = new Medicamentos(
                    med1, medDosis1,medTiempo1,med2,medDosis2,medTiempo2,med3,medDosis3,medTiempo3
            );

            ConexionBD conexionBD = new ConexionBD();
            conexionBD.conectar();
            boolean guardado = conexionBD.guardarAntPersonal(nuevoAP);
            boolean guardadoAF = conexionBD.guardarAntFamiliar(nuevoAF);
            boolean guardadoM = conexionBD.guardarMedicamento(medicamento);
            conexionBD.cerrar();

            if (guardado && guardadoAF && guardadoM) {
                correcto = true;
                System.out.println("Historial clínico guardado exitosamente");
                navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
            } else {
                System.out.println("Error al guardar el historial clínico en la base de datos");
            }
        } catch (NullPointerException e){
            MensajeAlerta.mensaje("Error en el registro del Historial clínico");
        }catch (Exception e) {
            System.out.println("Error al guardar el paciente.");
            e.printStackTrace();
        }
    }
    @FXML
    void actionCancelar(ActionEvent event) throws IOException {
        navigateTo("/fxml/Pacientes/RegistrarPaciente.fxml");
    }


    private void navigateTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Float parseFloatOrNull(String value) {
        try {
            return value.isEmpty() ? 0 : Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseIntOrNull(String value) {
        try {
            return value.isEmpty() ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private boolean validateMedicamento(String med1, String dosis1, Float tiempo1, String med2, String dosis2, Float tiempo2, String med3, String dosis3, Float tiempo3) {
        return validateFields(med1, dosis1, tiempo1) &&
                validateFields(med2, dosis2, tiempo2) &&
                validateFields(med3, dosis3, tiempo3);
    }

    private boolean validateFields(String med, String dosis, Float tiempo) {
        boolean medFilled = !med.isEmpty();
        boolean dosisFilled = !dosis.isEmpty();
        boolean tiempoFilled = tiempo != 0;

        if ((medFilled && dosisFilled && tiempoFilled) || (!medFilled && !dosisFilled && !tiempoFilled)) {
            return true;
        }
        return false;
    }

    private boolean validateAntecedentePersonal(String antPersonal1, Float tiempo1, String antPersonal2, Float tiempo2, String antPersonal3, Float tiempo3, String antPersonal4, Float tiempo4, String antPersonal5, Float tiempo5) {
        return validateFields(antPersonal1, tiempo1) &&
                validateFields(antPersonal2, tiempo2) &&
                validateFields(antPersonal3, tiempo3) &&
                validateFields(antPersonal4, tiempo4) &&
                validateFields(antPersonal5, tiempo5);
    }

    private boolean validateFields(String antPersonal, Float tiempo) {
        boolean antPersonalFilled = !antPersonal.isEmpty();
        boolean tiempoFilled = tiempo != 0;

        if ((antPersonalFilled && tiempoFilled) || (!antPersonalFilled && !tiempoFilled)) {
            return true;
        }
        return false;
    }


    private boolean validateAntecedenteFamiliar(String antFamiliar1, Integer grado1, String antFamiliar2, Integer grado2, String antFamiliar3, Integer grado3, String antFamiliar4, Integer grado4, String antFamiliar5, Integer grado5) {
        return validateFields(antFamiliar1, grado1) &&
                validateFields(antFamiliar2, grado2) &&
                validateFields(antFamiliar3, grado3) &&
                validateFields(antFamiliar4, grado4) &&
                validateFields(antFamiliar5, grado5);
    }

    private boolean validateFields(String antFamiliar, Integer grado) {
        boolean antFamiliarFilled = !antFamiliar.isEmpty();
        boolean gradoFilled = grado != 0;

        if ((antFamiliarFilled && gradoFilled) || (!antFamiliarFilled && !gradoFilled)) {
            return true;
        }
        return false;
    }
    // validaciones para cada campo del historial médico (lostFocus)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validarAntecedentesP(txtFieldAntPersonal1, txtFieldTiempoAP1);
        validarAntecedentesP(txtFieldAntPersonal2, txtFieldTiempoAP2);
        validarAntecedentesP(txtFieldAntPersonal3, txtFieldTiempoAP3);
        validarAntecedentesP(txtFieldAntPersonal4, txtFieldTiempoAP4);
        validarAntecedentesP(txtFieldAntPersonal5, txtFieldTiempoAP5);
        validarAntecedentesF(txtFieldAntFamiliar1, txtFieldGradoConsanginidad1);
        validarAntecedentesF(txtFieldAntFamiliar2, txtFieldGradoConsanginidad2);
        validarAntecedentesF(txtFieldAntFamiliar3, txtFieldGradoConsanginidad3);
        validarAntecedentesF(txtFieldAntFamiliar4, txtFieldGradoConsanginidad4);
        validarAntecedentesF(txtFieldAntFamiliar5, txtFieldGradoConsanginidad5);
        validarMedicamentos(txtFieldMedicinaNombre1, txtFieldMedicinaDosis1, txtFieldMedicinaTiempo1);
        validarMedicamentos(txtFieldMedicinaNombre2, txtFieldMedicinaDosis2, txtFieldMedicinaTiempo2);
        validarMedicamentos(txtFieldMedicinaNombre3, txtFieldMedicinaDosis3, txtFieldMedicinaTiempo3);
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
