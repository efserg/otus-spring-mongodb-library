package space.efremov.otus.springmongodblibrary.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.service.LibraryService;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    private final LibraryService service;

    public AuthorRestController(@Autowired LibraryService service) {
        this.service = service;
    }

    @GetMapping
    Flux<Author> all() {
        return service.getAllAuthors();
    }

    @GetMapping(value = "/{id}")
    Mono<ResponseEntity<Author>> author(@PathVariable("id") String id) {
        return service.findAuthorById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping
    Mono<ResponseEntity<Author>> create(@RequestBody AuthorInputDto input) {
        return service.createAuthor(input.getName())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return service.removeAuthor(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
