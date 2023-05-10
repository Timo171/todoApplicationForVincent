package nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.User;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.TaskDao;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.TodoDao;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Repository
public class DaoImpl implements TaskDao, TodoDao, UserDao {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Iterable<Task> saveTasks(Set<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Todo> findTodo(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Iterable<Todo> findTodoByUserId(Long id) {
        return todoRepository.findTodoByUserId(id);
    }

    @Override
    public Optional<Todo> removeTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todoRepository.deleteById(id);
        return todo;
    }

    @Override
    public List<Todo> findAllTodos() {
        return StreamSupport.stream(todoRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> removeUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }
}
