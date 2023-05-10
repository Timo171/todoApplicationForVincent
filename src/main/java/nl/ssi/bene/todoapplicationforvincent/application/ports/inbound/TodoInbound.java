package nl.ssi.bene.todoapplicationforvincent.application.ports.inbound;

import nl.ssi.bene.todoapplicationforvincent.application.core.dto.TaskDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.TodoDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateTaskDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateTodoDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindTodoException;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;

import java.util.List;

public interface TodoInbound {

    public TodoDto addTodo(TodoDto todoDto) throws CannotFindUserException;

    public TodoDto getTodo(Long id) throws CannotFindTodoException;

    public List<TodoDto> getTodosOfUser(Long id) throws CannotFindUserException;

    public TodoDto removeTodo(Long id) throws CannotFindTodoException;

    TodoDto addTaskToTodo(Long id, TaskDto taskDto);

    TodoDto updateTask(Long idTodo, Long idTask, UpdateTaskDto updateTaskDto);

    TodoDto updateTodo(Long id, UpdateTodoDto updateTodoDto);

    List<TodoDto> getAllTodos();

    TodoDto removeTask(Long idTodo, Long idTask);
}
