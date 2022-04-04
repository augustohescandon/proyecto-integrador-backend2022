package com.augusto.proyecto.integrador.dao;

import com.augusto.proyecto.integrador.dominio.Odontologo;
import com.augusto.proyecto.integrador.dominio.Paciente;
import com.augusto.proyecto.integrador.dominio.Domicilio;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component //para que spring la tenga en cuenta como parte importante
public class PacienteDAOH2 implements IDao<Paciente>{

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/integradora","root","");

    }
    @Override
    public List<Paciente> listarElementos() {
        Connection connection = null;
        List<Paciente> listaDePacientes = new ArrayList<>();
        Paciente paciente= null;
        Domicilio domicilio = null;
        Odontologo odontologo = null;

        try{
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();

            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id_domicilio = rs.getInt(7); // me quedo la duda
                int id_odontologo = rs.getInt(8);
                domicilio= domicilioDAOH2.buscarPorId(id_domicilio);
                odontologo = odontologoDAOH2.buscarPorId(id_odontologo);
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6).toLocalDate(),
                        domicilio,
                        odontologo);

                //agrego el paciente encontrado a la lista de pacientes
                listaDePacientes.add(paciente);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return listaDePacientes;
    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente= null;
        Domicilio domicilio = null;
        Odontologo odontologo = null;

        try{
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();

            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                //si encuentro el correo obtendo el id
                int id_domicilio = rs.getInt(7);
                int id_odontologo = rs.getInt(8);
                //uso el dao de domicilio para buscar todos los datos de domicilio
                domicilio= domicilioDAOH2.buscarPorId(id_domicilio);
                odontologo = odontologoDAOH2.buscarPorId(id_odontologo);
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6).toLocalDate(),
                        domicilio,
                        odontologo);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        Connection connection = null;
        Paciente paciente= null;
        Domicilio domicilio = null;
        Odontologo odontologo = null;

        try{
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();

            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE email LIKE ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                //si encuentro el correo obtendo el id
                int id_domicilio = rs.getInt(7);
                int id_odontologo = rs.getInt(8);
                //uso el dao de domicilio para buscar todos los datos de domicilio
                domicilio= domicilioDAOH2.buscarPorId(id_domicilio);
                odontologo = odontologoDAOH2.buscarPorId(id_odontologo);
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6).toLocalDate(),
                        domicilio,
                        odontologo);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente guardar(Paciente elemento) {
        Connection connection= null;
        try {
            connection = getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            Domicilio domicilio = domicilioDAOH2.guardar(elemento.getDomicilio());
            elemento.getDomicilio().setId(domicilio.getId());

            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            Odontologo odontologo = odontologoDAOH2.guardar(elemento.getOdontologo());
            elemento.getOdontologo().setId(odontologo.getId());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nombre, apellido, email, dni, fecha_ingreso, domicilio_id, odontologo_id) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getEmail());
            preparedStatement.setString(4, elemento.getDni());
            preparedStatement.setDate(5, Date.valueOf(elemento.getFechaIngreso()));
            preparedStatement.setInt(6, elemento.getDomicilio().getId());
            preparedStatement.setInt(7, elemento.getOdontologo().getId());

            preparedStatement.executeUpdate();
            ResultSet claves = preparedStatement.getGeneratedKeys();
            while (claves.next()){
                elemento.setId(claves.getInt(1));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return elemento;
    }

    @Override
    public Paciente actualizar(Paciente elemento) {
        Connection connection= null;
        try {
            connection = getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            Domicilio domicilio = domicilioDAOH2.guardar(elemento.getDomicilio());

            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            Odontologo odontologo = odontologoDAOH2.guardar(elemento.getOdontologo());


            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pacientes SET nombre=?, apellido=?, email=?, dni=?, fecha_ingreso=?, domicilio_id=?, odontologo_id=? WHERE id=?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getEmail());
            preparedStatement.setString(4, elemento.getDni());
            preparedStatement.setDate(5, Date.valueOf(elemento.getFechaIngreso()));
            preparedStatement.setInt(6, elemento.getDomicilio().getId());
            preparedStatement.setInt(7, elemento.getOdontologo().getId());
            preparedStatement.setInt(8, elemento.getId());
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return elemento;
    }

    @Override
    public void eliminar(int id) {
        Connection connection= null;
        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pacientes WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }


}
