package nl.ssi.bene.todoapplicationforvincent.application.core.service;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.User;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.*;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindTaskException;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindTodoException;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;
import nl.ssi.bene.todoapplicationforvincent.application.core.mapper.JpaContext;
import nl.ssi.bene.todoapplicationforvincent.application.core.mapper.Mapper;
import nl.ssi.bene.todoapplicationforvincent.application.ports.inbound.TodoInbound;
import nl.ssi.bene.todoapplicationforvincent.application.ports.inbound.UserInbound;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.TaskDao;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.TodoDao;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements UserInbound, TodoInbound {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private Mapper mapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) throws CannotFindUserException{
        Todo todo = mapper.todoDtoToTodo(todoDto, new JpaContext());
        todo = todoDao.saveTodo(todo);
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public TodoDto getTodo(Long id) throws CannotFindTodoException {
        Todo todo = todoDao.findTodo(id).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + id));
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getTodosOfUser(Long id) throws CannotFindUserException {
        User user = userDao.findUser(id).orElseThrow(() -> new CannotFindUserException("Cannot find user with id " + id));
        return user.getTodos().stream().map(todo -> mapper.todoToTodoDto(todo)).toList();
    }

    @Override
    public TodoDto removeTodo(Long id) throws CannotFindTodoException {
        Todo todo = todoDao.removeTodoById(id).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + id));
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public TodoDto addTaskToTodo(Long id, TaskDto taskDto) {
        Todo todo = todoDao.findTodo(id).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + id));
        Task task = mapper.taskDtoToTask(taskDto, new JpaContext(todo));
        todo.getTasks().add(task);
        todoDao.saveTodo(todo);
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public TodoDto updateTask(Long idTodo, Long idTask, UpdateTaskDto updateTaskDto) {
        Todo todo = todoDao.findTodo(idTodo).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + idTodo));
        Task task = updateTaskInternally(todo, idTask, updateTaskDto);
        taskDao.saveTask(task);
        return mapper.todoToTodoDto(todo);
    }

    private Task updateTaskInternally(Todo todo, Long idTask, UpdateTaskDto updateTaskDto) {
        Task task = todo.getTasks().stream().filter(t -> t.getId() == idTask).findFirst().orElseThrow(() -> new CannotFindTaskException("Cannot find task with id " + idTask));
        return mapper.updateTask(updateTaskDto, task);
    }

    @Override
    public TodoDto updateTodo(Long id, UpdateTodoDto updateTodoDto) {
        Todo todo = todoDao.findTodo(id).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + id));
        if(updateTodoDto.getCreatedBy() != null && !updateTodoDto.getCreatedBy().equals(todo.getUser().getId())) {
            User user = userDao.findUser(updateTodoDto.getCreatedBy()).orElseThrow(() -> new CannotFindUserException("Cannot find user with id " + id));
            todo.setUser(user);
        }
        mapper.updateTodoStatus(updateTodoDto, todo);
        updateTodoDto.getActions().forEach(updateTodoTaskDto -> updateTaskInternally(todo, updateTodoTaskDto.getId(), mapper.updateTodoTaskDtoToUpdateTaskDto(updateTodoTaskDto)));
        todoDao.saveTodo(todo);
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoDao.findAllTodos();
        return todos.stream().map(mapper::todoToTodoDto).toList();
    }

    @Override
    public TodoDto removeTask(Long idTodo, Long idTask) {
        Todo todo = todoDao.findTodo(idTodo).orElseThrow(() -> new CannotFindTodoException("Cannot find todo with id " + idTodo));
        Task task = todo.getTasks().stream().filter(t -> t.getId() == idTask).findFirst().orElseThrow(() -> new CannotFindTaskException("Cannot find task with id " + idTask));
        todo.getTasks().remove(task);
        todoDao.saveTodo(todo);
        return mapper.todoToTodoDto(todo);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.userDtoToUser(userDto);
        user = userDao.saveUser(user);
        return mapper.userToUserDto(user);
    }

    @Override
    public UserDto getUser(Long id) throws CannotFindUserException {
        User user = userDao.findUser(id).orElseThrow(() -> new CannotFindUserException("Cannot find user with id " + id));
        return mapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) throws CannotFindUserException {
        User user = userDao.findUser(id).orElseThrow(() -> new CannotFindUserException("Cannot find user with id " + id));
        user = mapper.updateUser(updateUserDto, user);
        user = userDao.saveUser(user);
        return mapper.userToUserDto(user);
    }

    @Override
    public UserDto removeUser(Long id) throws CannotFindUserException {
        User user = userDao.removeUserById(id).orElseThrow(() -> new CannotFindUserException("Cannot find user with id " + id));
        return mapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAllUsers();
        return users.stream().map(mapper::userToUserDto).toList();
    }

}
