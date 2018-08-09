package space.efremov.otus.springmongodblibrary.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(of = {"id", "username", "email"})
@EqualsAndHashCode(of = {"email"})
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
