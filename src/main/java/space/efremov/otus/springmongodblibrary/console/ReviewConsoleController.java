package space.efremov.otus.springmongodblibrary.console;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import space.efremov.otus.springmongodblibrary.domain.Book;
import space.efremov.otus.springmongodblibrary.domain.Review;
import space.efremov.otus.springmongodblibrary.domain.User;
import space.efremov.otus.springmongodblibrary.exception.EntityNotFoundException;
import space.efremov.otus.springmongodblibrary.repository.BookRepository;
import space.efremov.otus.springmongodblibrary.repository.UserRepository;


import java.util.List;

@ShellComponent
@ShellCommandGroup("review")
public class ReviewConsoleController {

    private final BookRepository bookRepository;
    private final UserRepository personDao;

    public ReviewConsoleController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.personDao = userRepository;
    }

    @ShellMethod(value = "Add book review to DB.", key = {"review add", "review-add"})
    public Review addReview(@ShellOption(help = "User ID.", value = {"user-id", "uid", "userId"}) String userId,
                            @ShellOption(help = "Book ID.", value = {"book-id", "bid", "bookId"}) String bookId,
                            @ShellOption(help = "Your review.", value = {"text", "review-text", "reviewText"}) String text) {
        final User user = personDao.findById(userId).orElseThrow(EntityNotFoundException::new);
        final Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        final Review review = new Review(user, text);
        book.addReview(review);
        bookRepository.save(book);
        return review;
    }

    @ShellMethod(value = "Get all reviews from DB.", key = {"review list", "review-list"})
    public List<Review> list(@ShellOption(help = "Book ID.", value = {"book-id", "bid", "bookId"}) String bookId) {
        final Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        return book.getReviews();
    }
}
