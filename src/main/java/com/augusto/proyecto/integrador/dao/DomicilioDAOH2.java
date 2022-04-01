package com.augusto.proyecto.integrador.dao;

import com.augusto.proyecto.integrador.dominio.Domicilio;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
@Component
public class DomicilioDAOH2 implements IDao <Domicilio>{

    private static Connection getConnetion() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/integradora","root","");

    }

    @Override
    public List<Domicilio> listarElementos() {
        return null;
    }

    @Override
    public Domicilio buscarPorId(int id) {
        Connection connection =null;
        Domicilio domicilio = null;

        try{
            connection = getConnetion();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM domicilios WHERE id= ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2),rs.getInt(3),
                                        rs.getString(4),rs.getString(5));
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
        return domicilio;

    }

    @Override
    public Domicilio buscarPorEmail(String email) {
        return null;
    }
}
