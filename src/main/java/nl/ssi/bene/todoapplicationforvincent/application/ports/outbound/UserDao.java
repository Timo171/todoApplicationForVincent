package nl.ssi.bene.todoapplicationforvincent.application.ports.outbound;

import nl.ssi.bene.todoapplicationforvincent.application.core.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao {
    Optional<User> findUser(Long id);
    User saveUser(User user);
    Optional<User> removeUserById(Long id);
    List<User> findAllUsers();
}
