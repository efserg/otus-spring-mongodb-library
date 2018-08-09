package space.efremov.otus.springmongodblibrary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Review {

    @Id
    private String id;

    private String text;

    private LocalDateTime create;

    @DBRef
    private User user;

}
