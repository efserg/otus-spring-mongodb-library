package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.efremov.otus.springmongodblibrary.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Query(value = "{ 'name' : {$regex: ?0, $options: 'i'} }")
    List<Author> findByNameContains(String name);

    Optional<Author> findByName(String name);
}
