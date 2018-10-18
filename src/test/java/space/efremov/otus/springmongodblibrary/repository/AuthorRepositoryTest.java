package space.efremov.otus.springmongodblibrary.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorRepositoryTest {

//    @Autowired
//    private AuthorRepository repository;
//
//    @Test
//    public void authorCrudTest() {
//        final Author brian = new Author("Brian Wilson Kernighan");
//        final Author author = repository.save(brian);
//        final Optional<Author> find = repository.findById(author.getId());
//        assertThat(find).isNotEmpty();
//        assertThat(find.get()).isEqualTo(brian);
//        repository.delete(find.get());
//        final Optional<Author> notExist = repository.findById(author.getId());
//        assertThat(notExist).isEmpty();
//    }
//
//    @Test
//    public void getNotExistAuthorByIdTest() {
//        final String notExistAuthorId = "5b703688f2e12f8d24432a38";
//        final Optional<Author> author = repository.findById(notExistAuthorId);
//        assertThat(author).isEmpty();
//    }
//
//    @Test
//    public void findAuthorAllTest() {
//        final Author richardStallman = new Author("Richard Matthew Stallman");
//        final Author dennisRitchie = new Author("Dennis MacAlistair Ritchie");
//        final Author andrewTanenbaum = new Author("Andrew Stuart Tanenbaum");
//        final List<Author> authors = Arrays.asList(richardStallman, dennisRitchie, andrewTanenbaum);
//        repository.saveAll(authors);
//
//        final List<Author> find = repository.findAll();
//        assertThat(find).containsOnly(richardStallman, dennisRitchie, andrewTanenbaum);
//
//        repository.deleteAll();
//        assertThat(repository.findAll()).isNullOrEmpty();
//    }
//
//    @Test
//    public void findByNameContainsTest() {
//        final Author donaldKnuth = new Author("Donald Ervin Knuth");
//        repository.save(donaldKnuth);
//        final List<Author> authors = repository.findByNameContains("ervin");
//        assertThat(authors).containsOnly(donaldKnuth);
//        final List<Author> notExists = repository.findByNameContains("ervik");
//        assertThat(notExists).isNullOrEmpty();
//        repository.deleteAll();
//        assertThat(repository.findAll()).isNullOrEmpty();
//    }
//
//    @Test
//    public void findByNameTest() {
//        final Author martinFowler = new Author("Martin Fowler");
//        repository.save(martinFowler);
//        final Optional<Author> maybeAuthor = repository.findByName("Martin Fowler");
//        assertThat(maybeAuthor).isNotEmpty();
//        assertThat(maybeAuthor.get()).isEqualTo(martinFowler);
//
//        final Optional<Author> notExist = repository.findByName("Martin ");
//        assertThat(notExist).isEmpty();
//
//        repository.deleteAll();
//        assertThat(repository.findAll()).isNullOrEmpty();
//    }

}