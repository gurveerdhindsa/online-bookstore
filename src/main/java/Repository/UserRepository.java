package Repository;
import BookStore.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * CRUD repository for User Entity
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByUserId(long userId);
    Optional<User> findByLastName(String lastName);


}


