package space.efremov.otus.springmongodblibrary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Book {

    @Id
    private String id;

    private String title;

    @DBRef
    private List<Author> authors;

    private String publisher;

    private List<String> tags;

    @DBRef
    private List<Review> reviews;
}
