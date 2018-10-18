package space.efremov.otus.springmongodblibrary.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;
import space.efremov.otus.springmongodblibrary.repository.AuthorRepository;
import space.efremov.otus.springmongodblibrary.repository.BookRepository;

import java.util.List;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public LibraryService(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Mono<Author> findAuthorById(final String id) {
        return authorRepository.findById(id);
    }

    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Mono<Void> removeAuthor(final String id) {
        return findAuthorById(id).flatMap(authorRepository::delete);
    }

    public Mono<Author> createAuthor(final String name) {
        final Author author = new Author(name);
        return authorRepository.save(author);
    }

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> findBookById(final String id) {
        return bookRepository.findById(id);
    }

    public Mono<Void> removeBook(final String id) {
        return findBookById(id).flatMap(bookRepository::delete);
    }

    public Mono<Book> createBook(final String title, final String isbn, final Integer year, final String publisher, final List<String> authorIds, final List<String> tags) {
        return getAuthors(authorIds)
                .collectList()
                .flatMap(authors -> {
                    final Book book = new Book(title, authors, publisher, year, isbn, tags);
                    return bookRepository.save(book);
                });
    }

    private Flux<Author> getAuthors(final List<String> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

}
