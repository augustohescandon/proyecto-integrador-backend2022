package com.augusto.proyecto.integrador.service;

import com.augusto.proyecto.integrador.dao.IDao;
import com.augusto.proyecto.integrador.dominio.Odontologo;
import com.augusto.proyecto.integrador.dominio.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listarElementos();
    }

    public Odontologo buscarPorId(int id){
        return odontologoIDao.buscarPorId(id);
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoIDao.actualizar(odontologo);
    }

    public void eliminar(Integer id) {
        odontologoIDao.eliminar(id);
    }

}
