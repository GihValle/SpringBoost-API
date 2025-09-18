package com.example.demo.controller;

import com.example.demo.model.Tarefa;
import com.example.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;
    
    // GET - Listar todas as tarefas
    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.getAllTarefas();
    }
    
    // GET - Buscar tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.getTarefaById(id);
        return tarefa.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // POST - Criar nova tarefa
    @PostMapping
    public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.createTarefa(tarefa);
    }
    
    // PUT - Atualizar tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        Tarefa updatedTarefa = tarefaService.updateTarefa(id, tarefaDetails);
        if (updatedTarefa != null) {
            return ResponseEntity.ok(updatedTarefa);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE - Remover tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarefa(@PathVariable Long id) {
        boolean deleted = tarefaService.deleteTarefa(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}