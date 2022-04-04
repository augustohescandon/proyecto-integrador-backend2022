package com.augusto.proyecto.integrador.controller;

import com.augusto.proyecto.integrador.dominio.Odontologo;
import com.augusto.proyecto.integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/odontologos")
    public String traerOdontologo(Model model, @RequestParam("id") int id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("matricula", odontologo.getMatricula());
        return "index";
    }


    @PostMapping
    public Odontologo registarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }

    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizar(odontologo);
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable Integer id){
        return odontologoService.buscarPorId(id);
    }

    @GetMapping()
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        String respuesta = "Error. El id ingesado no es correcto";
        if(odontologoService.buscarPorId(id) != null) {
            odontologoService.eliminar(id);
            respuesta = "Se eliminó al paciente con id =" + id;
        }
        return respuesta;
    }

}
