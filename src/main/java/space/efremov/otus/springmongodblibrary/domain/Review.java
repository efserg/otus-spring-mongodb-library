package space.efremov.otus.springmongodblibrary.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(of = {"id", "text", "create", "user"})
@EqualsAndHashCode(of = {"create", "user"})
public class Review {

    @Id
    private String id;

    private String text;

    private LocalDateTime create;

    @DBRef
    private User user;

    public Review(User user, String text) {
        this.text = text;
        this.user = user;
        this.create = LocalDateTime.now();
    }
}
