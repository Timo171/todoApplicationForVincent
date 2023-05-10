package nl.ssi.bene.todoapplicationforvincent.application.ports.inbound;

import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateUserDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UserDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;

import java.util.List;

public interface UserInbound {

    public UserDto addUser(UserDto user);

    public UserDto getUser(Long id) throws CannotFindUserException;

    UserDto updateUser(Long id, UpdateUserDto updateUserDto) throws CannotFindUserException;

    UserDto removeUser(Long id) throws CannotFindUserException;

    List<UserDto> getAllUsers();
}
