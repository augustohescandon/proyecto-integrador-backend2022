package com.augusto.proyecto.integrador.controller;

import com.augusto.proyecto.integrador.dao.OdontologoDAOH2;
import com.augusto.proyecto.integrador.dao.PacienteDAOH2;
import com.augusto.proyecto.integrador.dao.TurnoLista;
import com.augusto.proyecto.integrador.dominio.Odontologo;
import com.augusto.proyecto.integrador.dominio.Paciente;
import com.augusto.proyecto.integrador.dominio.Turno;
import com.augusto.proyecto.integrador.service.OdontologoService;
import com.augusto.proyecto.integrador.service.PacienteService;
import com.augusto.proyecto.integrador.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService = new TurnoService(new TurnoLista());
    private PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());

    @PostMapping
    public ResponseEntity<Turno> registraTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        //controlar si los id son existentes
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getPaciente().getId());
        if(paciente!=null && odontologo!= null){
            //okey, podemos agregar el turno
            respuesta = ResponseEntity.ok(turnoService.registrarTurno(turno));
        }
        else{
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listar());
    }
}
