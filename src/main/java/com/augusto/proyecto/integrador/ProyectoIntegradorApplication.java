package com.augusto.proyecto.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ProyectoIntegradorApplication {

	private static void cargarBD(){
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:h2:~/integradora;INIT=RUNSCRIPT FROM 'create.sql'","root","");

		} catch (Exception e) {
			e.printStackTrace();
		}
		  finally{
			  try {
				  connection.close();
			  }
			  catch (SQLException e) {
				  e.printStackTrace();
			  }
			}
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
		cargarBD();
	}

}
