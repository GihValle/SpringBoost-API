package com.example.demo.service;

import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }
    
    public Optional<Tarefa> getTarefaById(Long id) {
        return tarefaRepository.findById(id);
    }
    
    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    public Tarefa updateTarefa(Long id, Tarefa tarefaDetails) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setNome(tarefaDetails.getNome());
            tarefa.setDataEntrega(tarefaDetails.getDataEntrega());
            tarefa.setResponsavel(tarefaDetails.getResponsavel());
            return tarefaRepository.save(tarefa);
        }
        return null;
    }
    
    public boolean deleteTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}