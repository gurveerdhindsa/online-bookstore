package Repository;

import Bookstore.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends MongoRepository<User, Long> {
    @Override
    Optional<User> findById(Long id);


}
