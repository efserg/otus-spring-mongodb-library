package space.efremov.otus.springmongodblibrary.controller.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.Application;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.service.LibraryService;

import java.util.HashMap;
import java.util.Map;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
@RunWith(SpringRunner.class)
public class AuthorRestControllerTest {

    @MockBean
    private LibraryService service;

    @Autowired
    private WebTestClient client;

    private final String andrewName = "Andrew Stuart Tanenbaum";

    private final Author richard = new Author("Richard Matthew Stallman");
    private final Author brian = new Author("Brian Kernighan");
    private final Author dennis = new Author("Dennis MacAlistair Ritchie");
    private final Author andrew = new Author(andrewName);

    private final String richardId = "richard";
    private final String brianId = "brian";
    private final String dennisId = "dennis";
    private final String andrewId = "andrew";

    private final String unknownId = "unknonw";

    private final Map<String, Author> store = new HashMap<String, Author>() {{
        put(richardId, richard);
        put(brianId, brian);
        put(dennisId, dennis);
    }};

    @Before
    public void init() {

        richard.setId(richardId);
        dennis.setId(dennisId);
        brian.setId(brianId);
        andrew.setId(andrewId);

        Mockito.when(service.findAuthorById(richardId)).thenReturn(Mono.just(richard));
        Mockito.when(service.findAuthorById(unknownId)).thenReturn(Mono.empty());

        Mockito.when(service.getAllAuthors()).thenReturn(Flux.fromIterable(store.values()));

        Mockito.when(service.createAuthor(andrewName)).thenReturn(Mono.just(andrew));
        Mockito.when(service.removeAuthor(richardId)).thenReturn(Mono.empty());

    }

    @Test
    public void authorControllerGetMethodShouldReturnAuthorById() {
        client.get().uri("/authors/{id}", richardId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Author.class)
                .isEqualTo(richard);
    }

    @Test
    public void authorControllerGetMethodShouldReturnNotFoundForUnknownId() {
        client.get().uri("/authors/{id}", unknownId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void authorControllerShouldReturnCreatedAuthor() {
        client.post().uri("/authors")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(andrew))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Author.class)
                .isEqualTo(andrew);
    }

    @Test
    public void authorControllerShouldReturnAllAuthors() {
        client.get().uri("/authors")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Author.class)
                .hasSize(3)
                .contains(store.get(richardId), store.get(brianId), store.get(dennisId));
    }

    @Test
    public void authorControllerDeleteMethodShouldReturnNoContentIfAuthorExists() {
        client.delete().uri("/authors/{id}", richardId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

}