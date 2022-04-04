package com.augusto.proyecto.integrador.service;

import com.augusto.proyecto.integrador.dao.IDao;
import com.augusto.proyecto.integrador.dominio.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;

    public TurnoService(IDao<Turno> turnoIDao){
        this.turnoIDao = turnoIDao;
    }

    public Turno registrarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public List<Turno> listar(){
        return turnoIDao.listarElementos();
    }
}
