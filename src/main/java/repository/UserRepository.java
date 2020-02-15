package repository;
import bookstore.User;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository for User Entity
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstName(String firstName);
    User findByUserId(long userId);
    User findByLastName(String lastName);


}


