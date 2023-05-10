package nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


}
