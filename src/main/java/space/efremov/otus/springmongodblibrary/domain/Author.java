package space.efremov.otus.springmongodblibrary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Author {

    @Id
    private String id;

    private String name;
}
