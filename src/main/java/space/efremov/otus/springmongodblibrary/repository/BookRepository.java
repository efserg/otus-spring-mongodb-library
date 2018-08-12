package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query(value = "{ $or: [ " +
            "{ 'title' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'publisher' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'year' : {$regex: ?0} }, " +
            "{ 'isbn' : {$regex: ?0, $options: 'i'} } " +
            "] }")
    List<Book> findByAny(String field);


}
