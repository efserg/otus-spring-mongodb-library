package space.efremov.otus.springmongodblibrary.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import space.efremov.otus.springmongodblibrary.domain.Author;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;
    private final String richardName = "Richard Matthew Stallman";
    private final Author richard = new Author(richardName);
    private final Author brian = new Author("Brian Kernighan");
    private final Author dennis = new Author("Dennis MacAlistair Ritchie");

    @Test
    public void authorRepositoryShouldSaveAuthorAndFoundHimByName() {
        repository.save(richard).block();

        Mono<Author> authorMono = repository.findByName(richardName);

        StepVerifier.create(authorMono)
                .assertNext(author -> {
                    assertEquals(richardName, author.getName());
                    assertNotNull(author.getId());
                })
                .expectComplete()
                .verify();

    }

    @Test
    public void authorRepositoryShouldReturnAllAuthors() {
        repository.save(richard).block();
        repository.save(brian).block();
        repository.save(dennis).block();

        final Flux<Author> authors = repository.findAll();

        StepVerifier.create(authors)
                .recordWith(ArrayList::new)
                .expectNextCount(3)
                .consumeRecordedWith(results -> {
                    assertThat(results).hasSize(3);
                    assertThat(results)
                            .contains(
                                    richard,
                                    brian,
                                    dennis);
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void authorRepositoryShouldRemoveAuthor() {
        final Author author = repository.save(richard).block();
        assertThat(author).isNotNull();
        final String id = author.getId();
        assertThat(author.getName()).isEqualTo(richardName);
        assertThat(id).isNotNull();

        repository.delete(author);

        StepVerifier.create(repository.findById(id)).assertNext(a -> assertThat(a).isNull());

    }


}