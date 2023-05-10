package nl.ssi.bene.todoapplicationforvincent;

import lombok.extern.slf4j.Slf4j;
import nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository.TaskRepository;
import nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository.TodoRepository;
import nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository.UserRepository;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Status;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Set;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TodoRepository todoRepository, TaskRepository taskRepository){
        return args -> {
            if(!userRepository.findById(1L).isPresent()) {
                User user = userRepository.save(User.builder().name("system").build());
                log.info("Preloading " + user);
                Task task = Task.builder()
                        .description("system")
                        .status(Status.builder().finished(true).finishedTime(Instant.now()).build())
                        .build();
                Todo todo = Todo.builder()
                        .user(user)
                        .status(Status.builder().finished(true).finishedTime(Instant.now()).build())
                        .build();
                task.setTodo(todo);
                todo.setTasks(Set.of(task));
                log.info("Preloading" + todoRepository.save(todo));
            }
        };
    }

}
