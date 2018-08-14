package space.efremov.otus.springmongodblibrary.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(of = {"id", "authors", "title", "publisher", "year", "isbn"})
@EqualsAndHashCode(of = {"authors", "title", "publisher", "year", "isbn"})
public class Book {

    @Id
    private String id;

    private String title;

    @DBRef
    private List<Author> authors;

    private String publisher;

    private List<String> tags;

    private Integer year;

    private String isbn;

    @DBRef
    private List<Review> reviews;

    public Book(String title, List<Author> authors, String publisher, Integer year, String isbn, List<String> tags) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.tags = tags;
        this.year = year;
        this.isbn = isbn;
    }

    public void addReview(Review review) {
        if (this.reviews == null) this.reviews = new ArrayList<>();
        this.reviews.add(review);
    }

    public void addAuthor(Author author) {
        if (this.authors == null) this.authors = new ArrayList<>();
        this.authors.add(author);
    }
}
