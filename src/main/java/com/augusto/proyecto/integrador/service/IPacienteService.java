package com.augusto.proyecto.integrador.service;

import com.augusto.proyecto.integrador.dominio.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarPorEmail(String email);

    //clase25
    Paciente guardar(Paciente paciente);
    Paciente actualizar(Paciente paciente);

    //Case26
    Paciente buscarPorId(Integer id);

    //Clase27
    void eliminar(Integer id);


}
