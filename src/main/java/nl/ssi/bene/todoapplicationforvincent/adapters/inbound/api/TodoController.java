package nl.ssi.bene.todoapplicationforvincent.adapters.inbound.api;

import nl.ssi.bene.todoapplicationforvincent.application.core.dto.TaskDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.TodoDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateTaskDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateTodoDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindTaskException;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindTodoException;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;
import nl.ssi.bene.todoapplicationforvincent.application.core.mapper.Mapper;
import nl.ssi.bene.todoapplicationforvincent.application.ports.inbound.TodoInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoInbound todoInbound;

    @Autowired
    private Mapper mapper;

    @GetMapping("/{id}")
    public TodoDto getTodo(@PathVariable Long id){
        return todoInbound.getTodo(id);
    }

    @GetMapping("/of_user/{id}")
    public List<TodoDto> getTodoByUser(@PathVariable Long id){
        return todoInbound.getTodosOfUser(id);
    }

    @GetMapping("/all")
    public List<TodoDto> getAllTodos(){
        return todoInbound.getAllTodos();
    }

    @PostMapping("/add")
    public TodoDto addTodo(@RequestBody TodoDto todoDto){
        return todoInbound.addTodo(todoDto);
    }

    @PostMapping("/{id}/task/add")
    public TodoDto addTaskToTodo(@PathVariable Long id, @RequestBody TaskDto taskDto){
        return todoInbound.addTaskToTodo(id, taskDto);
    }

    @PutMapping("/{idTodo}/task/{idTask}")
    public TodoDto updateTask(@PathVariable Long idTodo, @PathVariable Long idTask, @RequestBody UpdateTaskDto updateTaskDto){
        return todoInbound.updateTask(idTodo, idTask, updateTaskDto);
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable Long id, @RequestBody UpdateTodoDto updateTodoDto){
        return todoInbound.updateTodo(id, updateTodoDto);
    }

    @DeleteMapping("/{id}")
    public TodoDto deleteTodo(@PathVariable Long id){
        return todoInbound.removeTodo(id);
    }

    @DeleteMapping("/{idTodo}/task/{idTask}")
    public TodoDto deleteTask(@PathVariable Long idTodo, @PathVariable Long idTask){
        return todoInbound.removeTask(idTodo, idTask);
    }

    @ExceptionHandler(value = {CannotFindUserException.class, CannotFindTodoException.class, CannotFindTaskException.class} )
    public ResponseEntity<String> handleCannotFindException(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @GetMapping("/view")
    public ModelAndView view(Model model){
        return new ModelAndView("view", "todos", todoInbound.getAllTodos());
    }

}
