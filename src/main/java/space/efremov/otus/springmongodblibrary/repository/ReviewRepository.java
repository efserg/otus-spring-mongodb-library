package space.efremov.otus.springmongodblibrary.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import space.efremov.otus.springmongodblibrary.domain.Review;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
}
