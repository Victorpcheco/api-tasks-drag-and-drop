package Api.tasks.drag_and_drop.service;

import Api.tasks.drag_and_drop.dto.TaskDTO;
import Api.tasks.drag_and_drop.dto.TaskUpdateDTO;
import Api.tasks.drag_and_drop.entities.TaskEntity;
import Api.tasks.drag_and_drop.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository repository;

    public List<TaskEntity> listarTasks(TaskEntity tarefas){
        return repository.findAll();
    }

    public TaskEntity createTask(TaskDTO tarefaDTO){


        // converter DTO para a entidade
        TaskEntity tarefa = new TaskEntity();
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setStatus(tarefaDTO.getStatus());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setUsuario_id(tarefaDTO.getUsuario_id());
        tarefa.setData_criacao(LocalDateTime.now());
        return repository.save(tarefa);

    }

    public TaskEntity updateTask(Long id, TaskUpdateDTO tarefaUpdateDTO) {
        TaskEntity tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefa.setTitulo(tarefaUpdateDTO.getTitulo());
        tarefa.setDescricao(tarefaUpdateDTO.getDescricao());
        tarefa.setStatus(tarefaUpdateDTO.getStatus());
        return repository.save(tarefa);

    }

    public void deleteTask(Long id) {
        Optional<TaskEntity> task = repository.findById(id);
        if (task.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Tarefa não encontrada!");
        }
    }

    public void updateStatusTask (Long id, String newStatus) {
        Optional<TaskEntity> task = repository.findById(id);
        if (task.isPresent()){
            TaskEntity existingTask = task.get();
            existingTask.setStatus(newStatus);
            repository.save(existingTask);

        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }


}
