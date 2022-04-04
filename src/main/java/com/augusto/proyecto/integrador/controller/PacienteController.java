package com.augusto.proyecto.integrador.controller;

import com.augusto.proyecto.integrador.dominio.Paciente;
import com.augusto.proyecto.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    //los metodos del controlador  se usa a traves de un servicio

    //creo un atributo de tipo PacienteService, no se crea un objeto new Pacie... porque
    //los datos son obtenidos del Dao a traves del servicio
    private final PacienteService pacienteService; //creo un objeto de tipo pacienteSe.. para usar el controller
    @Autowired //conecta al modelo con el controlador
    public PacienteController(PacienteService pacienteService) { //de donde se passa
        this.pacienteService = pacienteService;
    }



    //metodo que resuelve la solicitud de la vista
    @GetMapping("/paciente")
    //obtengo el email que viene del endpoint
    public String traerPaciente(Model model, @RequestParam("email") String email){
        //buscar al paciente con el email mediante el servicio , el controller usa al servicio para saber donde buscar los datos pedidos por la vista
        Paciente paciente = pacienteService.buscarPorEmail(email);
        //model me permite devolver atributos que luego se lo pasamos al modelo
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());

        return "index";
    }


    @PostMapping
    public Paciente registarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizar(paciente);
    }

    //clase26
    @GetMapping("/{id}")
    public Paciente buscarPaciente(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    @GetMapping()
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteService.listarPacientes();
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        String respuesta = "Error. El id ingesado no es correcto";
        if(pacienteService.buscarPorId(id) != null) {
            pacienteService.eliminar(id);
            respuesta = "Se eliminó al paciente con id =" + id;
        }
        return respuesta;
    }
}
