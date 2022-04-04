package com.augusto.proyecto.integrador.dao;

import com.augusto.proyecto.integrador.dominio.Odontologo;
import com.augusto.proyecto.integrador.dominio.Paciente;
import com.augusto.proyecto.integrador.dominio.Turno;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoLista implements IDao<Turno>{

    private List<Turno> turnos;

    public TurnoLista(){
        turnos = new ArrayList<>();
        PacienteDAOH2 pacienteDAOH2 = new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
        Paciente paciente = pacienteDAOH2.buscarPorId(1);
        Odontologo odontologo = odontologoDAOH2.buscarPorId(1);
        Turno turno = new Turno(1, paciente, odontologo, LocalDate.of(2022, 03,01));
        turnos.add(turno);
    }

    @Override
    public List<Turno> listarElementos() {
        return turnos;
    }

    @Override
    public Turno buscarPorId(int id) {
        for (Turno turno:turnos) {
            if (turno.getId()==id){
                return turno;
            }
        }
        return null;
    }

    @Override
    public Turno buscarPorEmail(String email) {
        return null;
    }

    @Override
    public Turno guardar(Turno turnoNuevo) {
        turnos.add(turnoNuevo);
        return turnoNuevo;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return turno;
    }

    @Override
    public void eliminar(int id) {
        for (Turno turno:turnos) {
            if (turno.getId()==id){
                turnos.remove(turno);
            }
        }
    }
}
