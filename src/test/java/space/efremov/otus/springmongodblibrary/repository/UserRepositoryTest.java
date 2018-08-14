package space.efremov.otus.springmongodblibrary.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.efremov.otus.springmongodblibrary.domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void userCrudTest() {
        final User pupkin = new User("vasilii_pupkin", "pupkin@mail.ru");
        final User user = repository.save(pupkin);
        final Optional<User> find = repository.findById(user.getId());
        assertThat(find).isNotEmpty();
        assertThat(find.get()).isEqualTo(pupkin);
        repository.delete(find.get());
        final Optional<User> notExist = repository.findById(user.getId());
        assertThat(notExist).isEmpty();
    }

    @Test
    public void getNotExistUserByIdTest() {
        final String notExistUserId = "5b703688f2e12f8d24432a38";
        final Optional<User> user = repository.findById(notExistUserId);
        assertThat(user).isEmpty();
    }

    @Test
    public void findUserAllTest() {
        final User normalverbraucher = new User("normalverbraucher@company.de", "Otto.Normalverbraucher");
        final User svensson = new User("medelsvensson@company.sw", "Medelsvensson");
        final User rossi = new User("mario_rossi@company.it", "MarioRossi");

        final List<User> users = Arrays.asList(normalverbraucher, svensson, rossi);
        repository.saveAll(users);

        final List<User> find = repository.findAll();
        assertThat(find).containsOnly(normalverbraucher, svensson, rossi);

        repository.deleteAll();
        assertThat(repository.findAll()).isNullOrEmpty();
    }

    @Test
    public void findByAny() {
        final User joe = new User("joe_bloggs@company.uk", "JoE_BlOgGs");
        repository.save(joe);
        final List<User> users = repository.findByAny("e_b");
        assertThat(users).containsOnly(joe);
        final List<User> notExists = repository.findByAny("block");
        assertThat(notExists).isNullOrEmpty();
        repository.deleteAll();
        assertThat(repository.findAll()).isNullOrEmpty();
    }

}