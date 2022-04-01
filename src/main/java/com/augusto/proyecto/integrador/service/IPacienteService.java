package com.augusto.proyecto.integrador.service;

import com.augusto.proyecto.integrador.dominio.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listaPacientes();
    Paciente buscarPorEmail(String email);

}
