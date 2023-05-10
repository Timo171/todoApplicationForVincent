package nl.ssi.bene.todoapplicationforvincent.application.core.mapper;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Task;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.Todo;
import nl.ssi.bene.todoapplicationforvincent.application.core.domain.User;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.*;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;
import nl.ssi.bene.todoapplicationforvincent.application.ports.outbound.UserDao;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@org.mapstruct.Mapper(
        componentModel = "spring"
)
public abstract class Mapper {

    @Autowired
    private UserDao userDao;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "todos", ignore = true)
    public abstract User userDtoToUser(UserDto userDto);

    public abstract UserDto userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "todos", ignore = true)
    public abstract User updateUser(UpdateUserDto updateUserDto, @MappingTarget User user);

    public User fromUserId(Long userId) throws CannotFindUserException{
        return userDao.findUser(userId).orElseThrow(() -> new CannotFindUserException("No user found with id " + userId));
    }

    @Mapping(source = "action", target = "description")
    @Mapping(source = "finished", target = "status.finished")
    @Mapping(source = "finishedAt", target = "status.finishedTime")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "todo", ignore = true)
    @BeanMapping(builder = @Builder(disableBuilder = true))
    public abstract Task taskDtoToTask(TaskDto taskDto, @Context JpaContext ctx);

    @InheritInverseConfiguration
    @BeanMapping(builder = @Builder())
    @Mapping(source = "id", target = "id")
    public abstract TaskDto taskToTaskDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "createdBy", target = "user")
    @Mapping(source = "actions", target = "tasks")
    @Mapping(source = "finished", target = "status.finished")
    @Mapping(source = "finishedAt", target = "status.finishedTime")
    @BeanMapping(builder = @Builder(disableBuilder = true))
    public abstract Todo todoDtoToTodo(TodoDto todoDto, @Context JpaContext ctx) throws CannotFindUserException;

    @Mapping(source = "creationTime", target = "createdAt")
    @Mapping(source = "user.id", target = "createdBy")
    @Mapping(source = "tasks", target = "actions")
    @Mapping(source = "status.finished", target = "finished")
    @Mapping(source = "status.finishedTime", target = "finishedAt")
    public abstract TodoDto todoToTodoDto(Todo todo);

    @Mapping(source = "action", target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(source = "finished", target = "status.finished", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(source = "finishedAt", target = "status.finishedTime", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "todo", ignore = true)
    public abstract Task updateTask(UpdateTaskDto updateTaskDto, @MappingTarget Task task);

    @Mapping(source = "finished", target = "status.finished", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(source = "finishedAt", target = "status.finishedTime", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    public abstract Todo updateTodoStatus(UpdateTodoDto updateTodoDto, @MappingTarget Todo todo);

    public abstract UpdateTaskDto updateTodoTaskDtoToUpdateTaskDto (UpdateTodoTaskDto updateTodoTaskDto);

}
