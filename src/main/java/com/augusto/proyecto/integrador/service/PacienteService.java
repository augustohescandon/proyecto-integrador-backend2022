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
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarElementos();
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        return pacienteIDao.buscarPorEmail(email);
    }

    //clase 25
    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {
        pacienteIDao.eliminar(id);
    }


}
