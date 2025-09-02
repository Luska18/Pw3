package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> findAll(){

        return tarefaMapper.toResponseDTOList(repository.findAll());
    }

    public boolean excuirPorId(Long id)
    {
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível econntrar a tarefa com o Id" + id));

        if (!"PENDING".equalsIgnoreCase(String.valueOf((tarefa.getStatus()))))
        {
            throw new RuntimeException("Tarefa em progresso ou concluída");
        }

        repository.delete(tarefa);
        return true;
    }
}
