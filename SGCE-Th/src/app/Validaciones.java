package app;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Validaciones {
    public static boolean validarCedula(String cd){
        StringTokenizer cedula;
        int[] digitos = new int[10];
        boolean validado = true;
        try {
            if (cd.length() == 10) { // Se verifica la longitud del String cedula
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
        return nombre.matches(regex);
    }

    public static boolean validarTelefono(String telefono){
        if (telefono == null || telefono.length() != 10) {
            return false;
        }
        for (char c : telefono.toCharArray()) {
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
        return pattern.matcher(direccion).matches();
    }

    public static boolean validarCorreo(String correo){
        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(patron);
        return pattern.matcher(correo).matches();
    }

}
