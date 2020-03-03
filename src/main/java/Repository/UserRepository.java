package Repository;

import Bookstore.Book;
import Bookstore.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {



}
