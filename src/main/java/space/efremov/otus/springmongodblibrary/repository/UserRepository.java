package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
