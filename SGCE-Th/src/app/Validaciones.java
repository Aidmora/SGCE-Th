package app;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    public static boolean validarCedula(String cd){
        StringTokenizer cedula;
        int[] digitos = new int[10];
        boolean validado = true;
        try {
            if (cd.trim().length()== 10) { // Se verifica la longitud del String cedula
                cedula = new StringTokenizer(cd, "1234567890", true);
                int i = 0;

                while (cedula.hasMoreTokens() && i < 10) {
                    digitos[i] = Integer.parseInt(cedula.nextToken());
                    i++;
                }
                int codigoProvincia = digitos[0] * 10 + digitos[1];
                if ((codigoProvincia < 25 && codigoProvincia > 0) || codigoProvincia == 30) {
                    if (digitos[2] < 6) {
                        int suma = 0;
                        for (int j = 0; j < digitos.length; j++) {
                            if (j % 2 == 0) { // Método de validación llamado "Módulo de 10"

                                digitos[j] = digitos[j] * 2;

                                if (digitos[j] >= 10) {
                                    digitos[j] = digitos[j] - 9;
                                }
                            }
                            suma = suma + digitos[j];
                        }
                        if (suma % 10 == 0) {
                            validado = true;
                        } else if (10 - (suma % 10) == digitos[9]) {
                            validado = true;
                        } else {
                            validado = false;
                        }
                    } else {
                        validado = false;
                    }
                } else {
                    validado = false;
                }
            } else {
                validado = false;
            }
        } catch (NumberFormatException e) { // Captura excepciones sobre los tipos de datos ingresados
            validado = false;
        }
        return validado;
    }

    public static boolean validarNombre(String nombre){
        String regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$";
        return nombre.trim().matches(regex);
    }
    public static boolean validarNombreA(String nombre){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚ0-9\\s.,;()\\-]{1,50}$";
        return nombre.trim().matches(regex);
    }
    public static boolean validarNombreM(String nombre){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚ0-9\\s.,;()\\-]{1,50}$";
        return nombre.trim().matches(regex);
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().length() != 10) {
            return false;
        }
        if (!telefono.startsWith("09")) {
            return false;
        }
        for (char c : telefono.trim().toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarDireccion(String direccion) {
        String ALLOWED_CHARACTERS = "^[a-zA-Z0-9 .\\-#/]*$";
        if (direccion == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(ALLOWED_CHARACTERS);
        return pattern.matcher(direccion.trim()).matches();
    }

    public static boolean validarCorreo(String correo){
        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(patron);
        return pattern.matcher(correo.trim()).matches();
    }
    public static boolean validarFechaNac(MFXDatePicker fechaNac){
        if (fechaNac.getValue() == null) {
            return false;
        }
        return true;
    }
    public static boolean validarTiempoAP(String tiempo) {
        if (tiempo == null || tiempo.trim().isEmpty()) {
            return false;
        }
        try {
            return Float.parseFloat(tiempo) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarGradoCon(String grado){
        if (grado == null || grado.trim().isEmpty()) {
            return false;
        }
        try {
            int gradoInt = Integer.parseInt(grado);
            return gradoInt >= 1 && gradoInt <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarMesNac(String mes){
        String regex = "^(enero|febrero|marzo|abril|mayo|junio|julio|agosto|septiembre|octubre|noviembre|diciembre)$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mes.trim());

        return matcher.matches();
    }
    public static boolean validarDosis(String dosis){
        String regex = "^[a-zA-Z0-9 ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dosis.trim());
        return matcher.matches();
    }
    public static void cambiarForamtoFecha(MFXDatePicker dateFechaNac) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        };
        //dateFechaNac.setConverter(converter);
    }
}