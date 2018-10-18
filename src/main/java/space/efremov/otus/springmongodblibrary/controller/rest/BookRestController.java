package space.efremov.otus.springmongodblibrary.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.efremov.otus.springmongodblibrary.domain.Book;
import space.efremov.otus.springmongodblibrary.service.LibraryService;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final LibraryService service;

    public BookRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping
    Flux<Book> all() {
        return service.getAllBooks();
    }

    @GetMapping(value = "/{id}")
    Mono<ResponseEntity<Book>> book(@PathVariable("id") String id) {
        return service.findBookById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ResponseEntity<Book>> create(@RequestBody BookInputDto input) {
        return service.createBook(input.getTitle(), input.getIsbn(), input.getYear(), input.getPublisher(), input.getAuthorIds(), input.getTags())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return service.removeBook(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
