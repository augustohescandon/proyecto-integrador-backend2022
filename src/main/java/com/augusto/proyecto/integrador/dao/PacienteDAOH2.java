package com.augusto.proyecto.integrador.dao;

import com.augusto.proyecto.integrador.dominio.Paciente;
import com.augusto.proyecto.integrador.dominio.Domicilio;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component //para que spring la tenga en cuenta como parte importante
public class PacienteDAOH2 implements IDao<Paciente>{

    private static Connection getConnetion() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/integradora","root","");

    }
    @Override
    public List<Paciente> listarElementos() {
        Connection connection = null;
        List<Paciente> listaDePacientes = new ArrayList<>();
        Paciente paciente= null;
        Domicilio domicilio = null;

        try{
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            connection = getConnetion();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id_domicilio = rs.getInt(7); // me quedo la duda
                domicilio= domicilioDAOH2.buscarPorId(id_domicilio);
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                                        rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), domicilio );

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

        return null;
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        Connection connection = null;
        Paciente paciente= null;
        Domicilio domicilio = null;

        try{
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            connection = getConnetion();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE email LIKE ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                //si encuentro el correo obtendo el id
                int id_domicilio = rs.getInt(7);
                //uso el dao de domicilio para buscar todos los datos de domicilio
                domicilio= domicilioDAOH2.buscarPorId(id_domicilio);
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), domicilio );

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
}
