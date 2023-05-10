package nl.ssi.bene.todoapplicationforvincent.adapters.inbound.api;

import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UpdateUserDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.dto.UserDto;
import nl.ssi.bene.todoapplicationforvincent.application.core.exceptions.CannotFindUserException;
import nl.ssi.bene.todoapplicationforvincent.application.core.mapper.Mapper;
import nl.ssi.bene.todoapplicationforvincent.application.ports.inbound.UserInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos/user")
public class UserController {

    @Autowired
    private UserInbound userInbound;

    @Autowired
    private Mapper mapper;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userInbound.getUser(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userInbound.getAllUsers();
    }

    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto userDto){
        return userInbound.addUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto){
        return userInbound.updateUser(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id) {
        return userInbound.removeUser(id);
    }

    @ExceptionHandler(CannotFindUserException.class)
    public ResponseEntity<String> handleCannotFindException(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
