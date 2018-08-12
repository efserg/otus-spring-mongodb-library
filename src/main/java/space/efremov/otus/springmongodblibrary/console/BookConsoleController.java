package space.efremov.otus.springmongodblibrary.console;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;
import space.efremov.otus.springmongodblibrary.exception.EntityNotFoundException;
import space.efremov.otus.springmongodblibrary.repository.AuthorRepository;
import space.efremov.otus.springmongodblibrary.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("book")
@Transactional(readOnly = true)
public class BookConsoleController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookConsoleController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @ShellMethod(value = "Add book to DB.", key = {"book add", "book-add"})
    public Book add(
            @ShellOption(help = "Book title. Use quotes if you need, e.g. \"Large Scale Machine Learning with Python\".", value = {"title", "book-title", "bookTitle"}) String title,
            @ShellOption(help = "Book ISBN.", value = {"isbn", "book-isbn", "bookIsbn"}) String isbn,
            @ShellOption(help = "Book year.", value = {"year", "book-year", "bookYear"}) Integer year,
            @ShellOption(help = "Publisher.", value = {"publisher"}) String publisher,
            @ShellOption(help = "Author list, separated by \",\". Use \"author find\" command to find exist authors.", defaultValue = "", value = {"authors", "author-list", "authorList"}) String authorNames,
            @ShellOption(help = "Tag list, separated by \",\".", defaultValue = "", value = {"tags", "tag-list", "tagList"}) String tagList
    ) {
        final List<Author> authors = Arrays.stream(authorNames.split(",")).map(name -> {
            final Optional<Author> author = authorRepository.findByName(name);
            return author.orElseGet(() -> authorRepository.save(new Author(name)));
        }).collect(Collectors.toList());
        final List<String> tags = Arrays.asList(tagList.split(","));
        return bookRepository.save(new Book(title, authors, publisher, year, isbn, tags));
    }

    @Transactional
    @ShellMethod(value = "Remove book from DB.", key = {"book remove", "book-remove"})
    public void remove(@ShellOption(help = "Book ID. You can use \"book find\" without parameters to found ID", value = {"book-id", "bid", "bookId", "id"}) String id) {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @ShellMethod(value = "Find book from DB.", key = {"book-find", "book find"})
    public List<Book> find(@ShellOption(help = "Any field of book or part of field (book title, author, tag, ISBN, year, publisher). Can be empty if you want to find all books", defaultValue = "") String field) {
        return bookRepository.findByAny(field);
    }

    @ShellMethod(value = "Get book from DB by ID.", key = {"book get", "book-get"})
    public Book get(@ShellOption(help = "Book ID. You can use \"book find\" without parameters to found ID", value = {"id", "bid", "bookId", "book-id"}) String id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
