package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.efremov.otus.springmongodblibrary.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
