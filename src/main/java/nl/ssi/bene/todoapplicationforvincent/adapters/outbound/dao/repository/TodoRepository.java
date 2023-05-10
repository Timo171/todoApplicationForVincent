package nl.ssi.bene.todoapplicationforvincent.adapters.outbound.dao.repository;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>{

    Iterable<Todo> findTodoByUserId(Long id);

}
