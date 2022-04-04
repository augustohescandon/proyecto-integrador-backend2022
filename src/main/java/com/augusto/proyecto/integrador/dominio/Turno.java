package com.augusto.proyecto.integrador.dominio;

import java.time.LocalDate;

public class Turno {

    private int id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;

    public Turno() {

    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno(int id, Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }
}
