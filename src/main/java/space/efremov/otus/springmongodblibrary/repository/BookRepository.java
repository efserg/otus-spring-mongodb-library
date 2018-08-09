package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
