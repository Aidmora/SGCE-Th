package app;

import obj.AntecedenteFamiliar;
import obj.AntecedentePersonal;
import obj.Medicamentos;
import obj.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private Connection conexion;
    private Statement statement;
    private boolean conectado = false;

    private static String IP = "localhost", PUERTO = "5432", BD = "SGCE-Th", USER = "soporte", PASS = "ariel";

    public ConexionBD() {
    }

    public void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://" + IP + ":" + PUERTO + "/" + BD, USER, PASS);
            statement = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean guardarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (paciente_nombre, paciente_cedula, paciente_direccion, paciente_correo, paciente_telefono, paciente_fecha_nac, paciente_fecha_creacion,paciente_ap, paciente_af, paciente_m) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombresCompletos());
            ps.setString(2, paciente.getNumeroCedulaIdentidad());
            ps.setString(3, paciente.getDireccionDomiciliaria());
            ps.setString(4, paciente.getCorreoElectronico());
            ps.setString(5, paciente.getNumeroDeTelefono());
            ps.setDate(6, Date.valueOf(paciente.getFechaDeNacimiento()));
            ps.setDate(7, Date.valueOf(paciente.getFechaCreacion()));
            ps.setInt(8,paciente.getAntPersonal());
            ps.setInt(9, paciente.getAntFamiliar());
            ps.setInt(10, paciente.getMedicamento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPaciente(Paciente pacienteAct, String cedula) {
        String sql = "UPDATE paciente SET paciente_nombre = ?, paciente_cedula = ?, paciente_direccion = ?, paciente_correo = ?, paciente_telefono = ?, paciente_fecha_nac = ? WHERE paciente_cedula = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, pacienteAct.getNombresCompletos());
            ps.setString(2, pacienteAct.getNumeroCedulaIdentidad());
            ps.setString(3, pacienteAct.getDireccionDomiciliaria());
            ps.setString(4, pacienteAct.getCorreoElectronico());
            ps.setString(5, pacienteAct.getNumeroDeTelefono());
            ps.setDate(6, Date.valueOf(pacienteAct.getFechaDeNacimiento()));
            ps.setString(7, cedula);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarAF(AntecedenteFamiliar afAct, String cedula) {
        String sql = "UPDATE antecedente_familiar set af_nombre1 = ?, af_grado1 = ?, af_nombre2 = ?, af_grado2 = ?, af_nombre3 = ?, af_grado3 = ?, af_nombre4 = ?, af_grado4 = ?,af_nombre5 = ?, af_grado5 = ?   WHERE af_id =(SELECT paciente_af FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, afAct.getNombre1());
            ps.setInt(2, afAct.getGrado1());
            ps.setString(3, afAct.getNombre2());
            ps.setInt(4, afAct.getGrado2());
            ps.setString(5, afAct.getNombre3());
            ps.setInt(6, afAct.getGrado3());
            ps.setString(7, afAct.getNombre4());
            ps.setInt(8, afAct.getGrado4());
            ps.setString(9, afAct.getNombre5());
            ps.setInt(10, afAct.getGrado5());
            ps.setString(11, cedula);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarAP(AntecedentePersonal apAct, String cedula) {
        String sql = "UPDATE antecedente_personal set ap_nombre1 = ?, ap_tiempo1 = ?, ap_nombre2 = ?, ap_tiempo2 = ?, ap_nombre3 = ?, ap_tiempo3 = ?, ap_nombre4 = ?, ap_tiempo4 = ?,ap_nombre5 = ?, ap_tiempo5 = ?   WHERE ap_id =(SELECT paciente_af FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, apAct.getNombre1());
            ps.setFloat(2, apAct.getTiempo1());
            ps.setString(3, apAct.getNombre2());
            ps.setFloat(4, apAct.getTiempo2());
            ps.setString(5, apAct.getNombre3());
            ps.setFloat(6, apAct.getTiempo3());
            ps.setString(7, apAct.getNombre4());
            ps.setFloat(8, apAct.getTiempo4());
            ps.setString(9, apAct.getNombre5());
            ps.setFloat(10, apAct.getTiempo5());
            ps.setString(11, cedula);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarMedicamento(Medicamentos med, String cedula) {
        String sql = "UPDATE medicamento SET medicamento_nombre1 = ?, medicamento_dosis1 = ?, medicamento_tiempo1 = ?, " +
                "medicamento_nombre2 = ?, medicamento_dosis2 = ?, medicamento_tiempo2 = ?, " +
                "medicamento_nombre3 = ?, medicamento_dosis3 = ?, medicamento_tiempo3 = ? " +
                "WHERE medicamento_id = (SELECT paciente_m FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, med.getNombre1());
            ps.setString(2, med.getDosis1());
            ps.setFloat(3, med.getTiempo1());
            ps.setString(4, med.getNombre2());
            ps.setString(5, med.getDosis2());
            ps.setFloat(6, med.getTiempo2());
            ps.setString(7, med.getNombre3());
            ps.setString(8, med.getDosis3());
            ps.setFloat(9, med.getTiempo3());
            ps.setString(10, cedula);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int getIDAntecedentes() throws SQLException {
        String sql = "SELECT MAX(ap_id) FROM antecedente_personal";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de antecedente personal.");
            }
        }
    }
    public int getIDAF() throws SQLException {
        String sql = "SELECT MAX(af_id) FROM antecedente_familiar";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de antecedente familiar.");
            }
        }
    }


    public int getIDMedicamento() throws SQLException {
        String sql = "SELECT MAX(medicamento_id) FROM medicamento";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de medicamento");
            }
        }
    }

    public AntecedenteFamiliar getAfConCedula(String cedulaBuscada){
        String sql = "SELECT * FROM antecedente_familiar WHERE  af_id = (SELECT paciente_af FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedulaBuscada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String af1 = rs.getString("af_nombre1");
                    int afG1 = rs.getInt("af_grado1");
                    String af2 = rs.getString("af_nombre2");
                    int afG2 = rs.getInt("af_grado2");
                    String af3 = rs.getString("af_nombre3");
                    int afG3 = rs.getInt("af_grado3");
                    String af4 = rs.getString("af_nombre4");
                    int afG4 = rs.getInt("af_grado4");
                    String af5 = rs.getString("af_nombre5");
                    int afG5 = rs.getInt("af_grado5");
                    AntecedenteFamiliar af = new AntecedenteFamiliar(af1, afG1, af2, afG2, af3, afG3, af4, afG4, af5, afG5);
                    return af;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el antecedente familiar", e);
        }
    }



    public AntecedentePersonal getAPConCedula(String cedulaBuscada){
        String sql = "SELECT * FROM antecedente_personal WHERE ap_id = (SELECT paciente_af FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedulaBuscada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String ap1 = rs.getString("ap_nombre1");
                    float apT1 = rs.getFloat("ap_tiempo1");
                    String ap2 = rs.getString("ap_nombre2");
                    float apT2 = rs.getFloat("ap_tiempo2");
                    String ap3 = rs.getString("ap_nombre3");
                    float apT3 = rs.getFloat("ap_tiempo3");
                    String ap4 = rs.getString("ap_nombre4");
                    float apT4 = rs.getFloat("ap_tiempo4");
                    String ap5 = rs.getString("ap_nombre5");
                    float apT5 = rs.getFloat("ap_tiempo5");
                    AntecedentePersonal ap = new AntecedentePersonal(ap1, apT1, ap2, apT2, ap3, apT3, ap4, apT4, ap5, apT5);
                    return ap;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el antecedente personal", e);
        }
    }

    public Medicamentos getMedicamentoCedula(String cedulaBuscada){
        String sql = "SELECT * FROM medicamento WHERE medicamento_id = (SELECT paciente_af FROM paciente WHERE paciente_cedula = ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedulaBuscada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String m1 = rs.getString("medicamento_nombre1");
                    String mDosis1 = rs.getString("medicamento_dosis1");
                    float mTiempo1 = rs.getFloat("medicamento_tiempo1");
                    String m2 = rs.getString("medicamento_nombre2");
                    String mDosis2 = rs.getString("medicamento_dosis2");
                    float mTiempo2 = rs.getFloat("medicamento_tiempo2");
                    String m3 = rs.getString("medicamento_nombre3");
                    String mDosis3 = rs.getString("medicamento_dosis3");
                    float mTiempo3 = rs.getFloat("medicamento_tiempo3");

                    Medicamentos med = new Medicamentos(m1, mDosis1, mTiempo1, m2, mDosis2, mTiempo2, m3, mDosis3, mTiempo3);
                    return med;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el medicamento", e);
        }
    }

    public List<Paciente> getPacientes() throws SQLException {
        String sql = "SELECT * FROM PACIENTE ORDER BY paciente_nombre";
        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String cedula = rs.getString("paciente_cedula");
                String nombre = rs.getString("paciente_nombre");
                String direccion = rs.getString("paciente_direccion");
                String correo = rs.getString("paciente_correo");
                String telefono = rs.getString("paciente_telefono");
                LocalDate fechaNac = rs.getDate("paciente_fecha_nac").toLocalDate();
                LocalDate fechaCreacion = rs.getDate("paciente_fecha_creacion").toLocalDate();
                int antPersonal = rs.getInt("paciente_ap");
                int antFamiliar = rs.getInt("paciente_af");
                int medicamento = rs.getInt("paciente_m");

                Paciente paciente = new Paciente(nombre, cedula, direccion, telefono, correo, fechaNac, fechaCreacion, antPersonal,antFamiliar,medicamento);
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pacientes", e);
        }
        return pacientes;
    }

    public Paciente getPacientePorCedula(String cedulaBuscada){
        String sql = "SELECT * FROM paciente WHERE paciente_cedula = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedulaBuscada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String cedula = rs.getString("paciente_cedula");
                    String nombre = rs.getString("paciente_nombre");
                    String direccion = rs.getString("paciente_direccion");
                    String correo = rs.getString("paciente_correo");
                    String telefono = rs.getString("paciente_telefono");
                    LocalDate fechaNac = rs.getDate("paciente_fecha_nac").toLocalDate();
                    LocalDate fechaCreacion = rs.getDate("paciente_fecha_creacion").toLocalDate();
                    int antPersonal = rs.getInt("paciente_ap");
                    int antFamiliar = rs.getInt("paciente_af");
                    int medicamento = rs.getInt("paciente_m");

                    Paciente paciente = new Paciente(nombre, cedula, direccion, telefono, correo, fechaNac, fechaCreacion, antPersonal,antFamiliar,medicamento);
                    return  paciente;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el paciente", e);
        }
    }

    public List<Paciente> getPacientesPorNombre(String nombreBuscado) {
        String sql = "SELECT * FROM paciente WHERE paciente_nombre LIKE ? ORDER BY paciente_nombre";

        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + nombreBuscado + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String cedula = rs.getString("paciente_cedula");
                    String nombre = rs.getString("paciente_nombre");
                    String direccion = rs.getString("paciente_direccion");
                    String correo = rs.getString("paciente_correo");
                    String telefono = rs.getString("paciente_telefono");
                    LocalDate fechaNac = rs.getDate("paciente_fecha_nac").toLocalDate();
                    LocalDate fechaCreacion = rs.getDate("paciente_fecha_creacion").toLocalDate();
                    int antPersonal = rs.getInt("paciente_ap");
                    int antFamiliar = rs.getInt("paciente_af");
                    int medicamento = rs.getInt("paciente_m");

                    Paciente paciente = new Paciente(nombre, cedula, direccion, telefono, correo, fechaNac, fechaCreacion, antPersonal, antFamiliar, medicamento);
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el paciente", e);
        }
        return pacientes;
    }

    public List<Paciente> getPacientesPorMes(int mesBuscado) {
        String sql = "SELECT * FROM paciente WHERE EXTRACT(MONTH FROM paciente_fecha_nac) = ? ORDER BY paciente_nombre";

        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, mesBuscado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String cedula = rs.getString("paciente_cedula");
                    String nombre = rs.getString("paciente_nombre");
                    String direccion = rs.getString("paciente_direccion");
                    String correo = rs.getString("paciente_correo");
                    String telefono = rs.getString("paciente_telefono");
                    LocalDate fechaNac = rs.getDate("paciente_fecha_nac").toLocalDate();
                    LocalDate fechaCreacion = rs.getDate("paciente_fecha_creacion").toLocalDate();
                    int antPersonal = rs.getInt("paciente_ap");
                    int antFamiliar = rs.getInt("paciente_af");
                    int medicamento = rs.getInt("paciente_m");

                    Paciente paciente = new Paciente(nombre, cedula, direccion, telefono, correo, fechaNac, fechaCreacion, antPersonal, antFamiliar, medicamento);
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el paciente", e);
        }
        return pacientes;
    }

    public AntecedenteFamiliar getAntecedenteFamiliar(int idAntFamiliar) {
        String sql = "SELECT * FROM antecedente_familiar WHERE af_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idAntFamiliar);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AntecedenteFamiliar antecedenteFamiliar = new AntecedenteFamiliar();

                    antecedenteFamiliar.setNombre1(rs.getString("af_nombre1"));
                    antecedenteFamiliar.setGrado1(rs.getInt("af_grado1"));
                    antecedenteFamiliar.setNombre2(rs.getString("af_nombre2"));
                    antecedenteFamiliar.setGrado2(rs.getInt("af_grado2"));
                    antecedenteFamiliar.setNombre3(rs.getString("af_nombre3"));
                    antecedenteFamiliar.setGrado3(rs.getInt("af_grado3"));
                    antecedenteFamiliar.setNombre4(rs.getString("af_nombre4"));
                    antecedenteFamiliar.setGrado4(rs.getInt("af_grado4"));
                    antecedenteFamiliar.setNombre5(rs.getString("af_nombre5"));
                    antecedenteFamiliar.setGrado5(rs.getInt("af_grado5"));

                    return antecedenteFamiliar;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el antecedente familiar", e);
        }
    }

    public AntecedentePersonal getAntecedentePersonal(int idAntPersonal) {
        String sql = "SELECT * FROM antecedente_personal WHERE ap_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idAntPersonal);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AntecedentePersonal antecedentePersonal = new AntecedentePersonal();

                    antecedentePersonal.setNombre1(rs.getString("ap_nombre1"));
                    antecedentePersonal.setTiempo1(rs.getFloat("ap_tiempo1"));
                    antecedentePersonal.setNombre2(rs.getString("ap_nombre2"));
                    antecedentePersonal.setTiempo2(rs.getFloat("ap_tiempo2"));
                    antecedentePersonal.setNombre3(rs.getString("ap_nombre3"));
                    antecedentePersonal.setTiempo3(rs.getFloat("ap_tiempo3"));
                    antecedentePersonal.setNombre4(rs.getString("ap_nombre4"));
                    antecedentePersonal.setTiempo4(rs.getFloat("ap_tiempo4"));
                    antecedentePersonal.setNombre5(rs.getString("ap_nombre5"));
                    antecedentePersonal.setTiempo5(rs.getFloat("ap_tiempo5"));
                    return antecedentePersonal;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el antecedente personal", e);
        }
    }

    public Medicamentos getMedicamento (int idMedicamento) {
        String sql = "SELECT * FROM medicamento WHERE medicamento_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMedicamento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medicamentos medicamentos = new Medicamentos();

                    medicamentos.setNombre1(rs.getString("medicamento_nombre1"));
                    medicamentos.setDosis1(rs.getString("medicamento_dosis1"));
                    medicamentos.setTiempo1(rs.getFloat("medicamento_tiempo1"));
                    medicamentos.setNombre2(rs.getString("medicamento_nombre2"));
                    medicamentos.setDosis2(rs.getString("medicamento_dosis2"));
                    medicamentos.setTiempo2(rs.getFloat("medicamento_tiempo2"));
                    medicamentos.setNombre3(rs.getString("medicamento_nombre3"));
                    medicamentos.setDosis3(rs.getString("medicamento_dosis3"));
                    medicamentos.setTiempo3(rs.getFloat("medicamento_tiempo3"));

                    return medicamentos;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los medicamentos", e);
        }
    }

    public List<String> getPacienteCedulas() throws SQLException {
        String sql = "SELECT paciente_cedula FROM paciente";
        List<String> cedulas = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cedulas.add(rs.getString("paciente_cedula"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener las cédulas de los pacientes", e);
        }

        if (cedulas.isEmpty()) {
            throw new SQLException("No se encontraron cédulas de pacientes en la base de datos");
        }

        return cedulas;
    }
    public boolean guardarAntPersonal(AntecedentePersonal antecedentePersonal) {
        String sql = "INSERT INTO public.antecedente_personal(ap_nombre1, ap_tiempo1, ap_nombre2, ap_tiempo2, ap_nombre3, ap_tiempo3, ap_nombre4, ap_tiempo4, ap_nombre5, ap_tiempo5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, antecedentePersonal.getNombre1());
            ps.setObject(2, antecedentePersonal.getTiempo1(), java.sql.Types.FLOAT);            ps.setString(3, antecedentePersonal.getNombre2());
            ps.setObject(4, antecedentePersonal.getTiempo2(), java.sql.Types.FLOAT);            ps.setString(5, antecedentePersonal.getNombre3());
            ps.setObject(6, antecedentePersonal.getTiempo3(), java.sql.Types.FLOAT);            ps.setString(7, antecedentePersonal.getNombre4());
            ps.setObject(8, antecedentePersonal.getTiempo4(), java.sql.Types.FLOAT);            ps.setString(9, antecedentePersonal.getNombre4());
            ps.setObject(10, antecedentePersonal.getTiempo5(), java.sql.Types.FLOAT);            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarAntFamiliar(AntecedenteFamiliar antecedenteFamiliar) {
        String sql = "INSERT INTO antecedente_familiar(af_nombre1, af_grado1, af_nombre2, af_grado2, af_nombre3, af_grado3, af_nombre4, af_grado4, af_nombre5, af_grado5)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, antecedenteFamiliar.getNombre1());
            ps.setObject(2, antecedenteFamiliar.getGrado1(), java.sql.Types.FLOAT);
            ps.setString(3, antecedenteFamiliar.getNombre2());
            ps.setObject(4, antecedenteFamiliar.getGrado2(), java.sql.Types.FLOAT);
            ps.setString(5, antecedenteFamiliar.getNombre3());
            ps.setObject(6, antecedenteFamiliar.getGrado3(), java.sql.Types.FLOAT);
            ps.setString(7, antecedenteFamiliar.getNombre4());
            ps.setObject(8, antecedenteFamiliar.getGrado4(), java.sql.Types.FLOAT);
            ps.setString(9, antecedenteFamiliar.getNombre5());
            ps.setObject(10, antecedenteFamiliar.getGrado5(), java.sql.Types.FLOAT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarMedicamento(Medicamentos medicamento) {
        String sql = "INSERT INTO medicamento(medicamento_nombre1, medicamento_dosis1, medicamento_tiempo1, medicamento_nombre2, medicamento_dosis2, medicamento_tiempo2, medicamento_nombre3, medicamento_dosis3, medicamento_tiempo3)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNombre1());
            ps.setString(2, medicamento.getDosis1());
            ps.setObject(3, medicamento.getTiempo1(), java.sql.Types.FLOAT);
            ps.setString(4, medicamento.getNombre2());
            ps.setString(5, medicamento.getDosis2());
            ps.setObject(6, medicamento.getTiempo2(), java.sql.Types.FLOAT);
            ps.setString(7, medicamento.getNombre3());
            ps.setString(8, medicamento.getDosis3());
            ps.setObject(9, medicamento.getTiempo3(), java.sql.Types.FLOAT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cerrar() throws SQLException {
        try {
            if (conexion != null) {
                conexion.close();
            }
            if (statement != null) {
                statement.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
