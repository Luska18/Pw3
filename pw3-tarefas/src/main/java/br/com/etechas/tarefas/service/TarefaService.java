package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaCreationDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public boolean deleteById(Long id){
        var tarefa = repository.findById(id);
        if(tarefa.isEmpty()){
            return false;
        }
        if (tarefa.get().isPending()) {
            repository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Tarefa em progresso ou concluída");
    }

    public Tarefa postar(TarefaCreationDTO dto) {
        if (dto.dataLimite().isBefore(LocalDate.now())) {
            throw new RuntimeException("Data limite não pode ser anterior a data atual");
        }
        Tarefa tarefa = tarefaMapper.toEntity(dto);
        tarefa.setStatus(StatusEnum.PENDING);
        return repository.save(tarefa);
    }
}

