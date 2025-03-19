package Api.tasks.drag_and_drop.controllers;

import Api.tasks.drag_and_drop.dto.TaskDTO;
import Api.tasks.drag_and_drop.dto.TaskUpdateDTO;
import Api.tasks.drag_and_drop.dto.UpdateStatusTaskDTO;
import Api.tasks.drag_and_drop.entities.TaskEntity;
import Api.tasks.drag_and_drop.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<TaskEntity> allTasks(TaskEntity tarefasModel){
        return service.listarTasks(tarefasModel);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskDTO tarefaDTO)   {
        TaskEntity createdTask = service.createTask(tarefaDTO);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO tarefaUpdateDTO){
        TaskEntity updateTask = service.updateTask(id, tarefaUpdateDTO);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        try {
            service.deleteTask(id);
            return ResponseEntity.ok("Tarefa excluída com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada");
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatusTask(@PathVariable Long id, @RequestBody UpdateStatusTaskDTO updateStatusTaskDTO){
        try {
            service.updateStatusTask(id, updateStatusTaskDTO.getStatus());
            return ResponseEntity.ok("Status da tarefa atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada.");
        }

    }
}
