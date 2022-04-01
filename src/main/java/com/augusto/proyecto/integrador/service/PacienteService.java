package com.augusto.proyecto.integrador.service;

import com.augusto.proyecto.integrador.dao.IDao;
import com.augusto.proyecto.integrador.dominio.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    // trabajar con el DAO y maneja los datos de la BD H2
    @Override
    public List<Paciente> listaPacientes() {
        return pacienteIDao.listarElementos();
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        return pacienteIDao.buscarPorEmail(email);
    }
}
