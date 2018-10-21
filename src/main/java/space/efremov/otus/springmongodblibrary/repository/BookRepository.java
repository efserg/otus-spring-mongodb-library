package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import space.efremov.otus.springmongodblibrary.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    @Query(value = "{ $or: [ " +
            "{ 'title' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'publisher' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'year' : {$regex: ?0} }, " +
            "{ 'isbn' : {$regex: ?0, $options: 'i'} } " +
            "] }")
    Flux<Book> findByAny(String field);

}
