package nl.ssi.bene.todoapplicationforvincent.application.ports.outbound;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoDao {
    Optional<Todo> findTodo(Long id);
    Todo saveTodo(Todo todo);
    Iterable<Todo> findTodoByUserId(Long id);
    Optional<Todo> removeTodoById(Long id);
    List<Todo> findAllTodos();
}
