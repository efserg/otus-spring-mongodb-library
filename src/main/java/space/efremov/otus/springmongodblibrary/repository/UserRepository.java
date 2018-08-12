package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ $or: [ " +
            "{ 'username' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'email' : {$regex: ?0, $options: 'i'} } " +
            "] }")
    List<User> findByAny(String field);

}
