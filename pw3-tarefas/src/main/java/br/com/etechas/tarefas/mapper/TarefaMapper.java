package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.dto.TarefaCreationDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    Tarefa toEntity(TarefaCreationDTO dto);
    List<TarefaResponseDTO> toResponseDTOList(List<Tarefa> tarefa);
}
