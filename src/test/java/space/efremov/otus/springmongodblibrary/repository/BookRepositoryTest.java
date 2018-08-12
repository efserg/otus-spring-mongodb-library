package space.efremov.otus.springmongodblibrary.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.efremov.otus.springmongodblibrary.domain.Author;
import space.efremov.otus.springmongodblibrary.domain.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void bookCrudTest() {
        final Author dennis = new Author("Dennis MacAlistair Ritchie");
        final Author brian = new Author("Brian Wilson Kernighan");
        authorRepository.saveAll(Arrays.asList(dennis, brian));
        final String bellLabs = "AT&T Bell Labs";
        final List<String> tags = Arrays.asList("C", "Classic");
        final Book theCProgrammingLanguage = new Book("The C Programming Language", Arrays.asList(dennis, brian), bellLabs, 1978, "0-13-110163-3", tags);
        final Book save = repository.save(theCProgrammingLanguage);
        final Optional<Book> book = repository.findById(save.getId());
        assertThat(book).contains(theCProgrammingLanguage);
    }

    @Test
    public void getNotExistBookByIdTest() {
        final String notExistBookId = "5b703688f2e12f8d24432a38";
        final Optional<Book> book = repository.findById(notExistBookId);
        assertThat(book).isEmpty();
    }

}