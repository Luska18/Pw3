package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.TarefaCreationDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService service;


    @GetMapping
    public List<TarefaResponseDTO> listar(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tarefa> postar(@RequestBody TarefaCreationDTO body) {
        try {
            Tarefa tarefaCriada = service.postar(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().header("ERRO!", e.getMessage()).build();
        }
    }
}