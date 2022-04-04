package com.augusto.proyecto.integrador.dominio;

import java.time.LocalDate;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;
    private Odontologo odontologo;

    public Paciente(){

    }

    public Paciente(String nombre, String apellido, String email, String dni, LocalDate fechaIngreso, Domicilio domicilio, Odontologo odontologo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.odontologo = odontologo;
    }

    public Paciente(int id, String nombre, String apellido, String email, String dni, LocalDate fechaIngreso, Domicilio domicilio, Odontologo odontologo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.odontologo = odontologo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setId(int id) {
        this.id = id;
    }
}
