package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    @Query(value = "{ 'name' : {$regex: ?0, $options: 'i'} }")
    Flux<Author> findByNameContains(String name);

    Mono<Author> findByName(String name);
}
