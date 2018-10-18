package space.efremov.otus.springmongodblibrary.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.service.LibraryService;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    private final LibraryService service;

    public AuthorRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping
    Flux<Author> all() {
        return service.getAllAuthors();
    }

    @GetMapping(value = "/{id}")
    Mono<Author> author(@PathVariable("id") String id) {
        return service.findAuthorById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Mono<Author> create(@RequestBody AuthorInputDto input) {
        return service.createAuthor(input.getName());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Mono<Void> delete(@PathVariable("id") String id) {
        return service.removeAuthor(id);
    }

}
