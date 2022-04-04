package com.augusto.proyecto.integrador.dao;

import com.augusto.proyecto.integrador.dominio.Odontologo;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/integradora","root","");

    }

    @Override
    public List<Odontologo> listarElementos() {
        Connection connection = null;
        List<Odontologo> listaDeOdontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologo");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));

                //agrego el paciente encontrado a la lista de pacientes
                listaDeOdontologos.add(odontologo);
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
        return listaDeOdontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Connection connection =null;
        Odontologo odontologo = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologo WHERE id= ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
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
        return odontologo;
    }

    @Override
    public Odontologo buscarPorEmail(String email) {
        return null;
    }

    @Override
    public Odontologo guardar(Odontologo elemento) {
        Connection connection= null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO odontologo(nombre, apellido, matricula) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getMatricula());

            preparedStatement.executeUpdate();
            ResultSet claves = preparedStatement.getGeneratedKeys();
            while (claves.next()){
                elemento.setId(claves.getInt(1));
            }
            preparedStatement.close();
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
    public Odontologo actualizar(Odontologo elemento) {
        Connection connection= null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE odontologo SET nombre=?, apellido=?, matricula=? WHERE id=?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getMatricula());
            preparedStatement.setInt(4, elemento.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM odontologo WHERE id=?");
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
