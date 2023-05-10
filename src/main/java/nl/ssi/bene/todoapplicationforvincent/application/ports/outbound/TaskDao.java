package nl.ssi.bene.todoapplicationforvincent.application.ports.outbound;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskDao {
    Iterable<Task> saveTasks(Set<Task> tasks);
    Task saveTask(Task task);
}
