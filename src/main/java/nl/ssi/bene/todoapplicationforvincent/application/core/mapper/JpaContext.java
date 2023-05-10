package nl.ssi.bene.todoapplicationforvincent.application.core.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

@AllArgsConstructor
@NoArgsConstructor
public class JpaContext {

    private Todo todo;

    @BeforeMapping
    public void setEntity(@MappingTarget Todo todo) {
        this.todo = todo;
    }

    @AfterMapping
    public void establishRelation(@MappingTarget Task task) {
        task.setTodo( todo );
    }
}
