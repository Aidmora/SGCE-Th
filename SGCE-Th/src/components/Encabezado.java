package components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Encabezado extends AnchorPane {
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private MenuItem
            itemRegPagoPendiente,
            itemRegPagoRealizado,
            itemConsPagoPendiente,
            itemConsPagoRealizado,
            itemActPagoPendiente,
            itemActPagoRealizado;


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
        btnCerrarSesion.setOnAction(this::controlarAction);

        // PACIENTES

        // MÉDICOS

        // TRATAMIENTOS

        // CITAS

        // PAGOS
        itemRegPagoPendiente.setOnAction(this::controlarAction);
        itemRegPagoRealizado.setOnAction(this::controlarAction);
        itemConsPagoPendiente.setOnAction(this::controlarAction);
        itemConsPagoRealizado.setOnAction(this::controlarAction);
        itemActPagoPendiente.setOnAction(this::controlarAction);
        itemActPagoRealizado.setOnAction(this::controlarAction);

        // ADMINISTRACIÓN

    }

    @FXML
    private void controlarAction(ActionEvent event) {
        if (event.getSource() == btnCerrarSesion)
            navigateTo("/fxml/Login.fxml");

        // PACIENTES

        // MÉDICOS

        // TRATAMIENTOS

        // CITAS

        // PAGOS
        if (event.getSource() == itemRegPagoPendiente) {
            navigateTo("/fxml/Pagos/RegistrarPagoPendiente.fxml");
        } else if (event.getSource() == itemRegPagoRealizado) {
            navigateTo("/fxml/Pagos/RegistrarPagoRealizado.fxml");
        } else if (event.getSource() == itemConsPagoPendiente) {
            navigateTo("/fxml/Pagos/ConsultarPagoPendiente.fxml");
        } else if (event.getSource() == itemConsPagoRealizado) {
            navigateTo("/fxml/Pagos/ConsultarPagoRealizado.fxml");
        } else if (event.getSource() == itemActPagoPendiente) {
            navigateTo("/fxml/Pagos/ActualizarPagoPendiente.fxml");
        } else if (event.getSource() == itemActPagoRealizado) {
            navigateTo("/fxml/Pagos/ActualizarPagoRealizado.fxml");
        }

        // ADMINISTRACIÓN
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
