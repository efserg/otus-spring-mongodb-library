package space.efremov.otus.springmongodblibrary.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;

import java.util.List;

public interface LibraryService {
    Mono<Author> findAuthorById(String id);

    Flux<Author> getAllAuthors();

    Mono<Void> removeAuthor(String id);

    Mono<Author> createAuthor(String name);

    Flux<Book> getAllBooks();

    Mono<Book> findBookById(String id);

    Mono<Void> removeBook(String id);

    Mono<Book> createBook(String title, String isbn, Integer year, String publisher, List<String> authorIds, List<String> tags);
}
