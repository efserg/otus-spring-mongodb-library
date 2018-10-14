package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import space.efremov.otus.springmongodblibrary.domain.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    @Query(value = "{ $or: [ " +
            "{ 'username' : {$regex: ?0, $options: 'i'} }, " +
            "{ 'email' : {$regex: ?0, $options: 'i'} } " +
            "] }")
    Flux<User> findByAny(String field);

}
