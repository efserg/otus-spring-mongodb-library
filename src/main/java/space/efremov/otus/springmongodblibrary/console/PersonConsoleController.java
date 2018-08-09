package space.efremov.otus.springmongodblibrary.console;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import space.efremov.otus.springmongodblibrary.domain.User;
import space.efremov.otus.springmongodblibrary.exception.EntityNotFoundException;
import space.efremov.otus.springmongodblibrary.repository.UserRepository;

import java.util.List;

@ShellComponent
@ShellCommandGroup("user")
@Transactional(readOnly = true)
public class PersonConsoleController {

    private final UserRepository userRepository;

    public PersonConsoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ShellMethod(value = "Add user to DB.", key = {"user add", "user-add"})
    @Transactional
    public User add(@ShellOption(help = "user username. Use quotes if you need first name and last name, e.g. \"John Smith jr.\"", value = {"username", "name"}) String username,
                    @ShellOption(help = "user email") String email) {
        return userRepository.save(new User(username, email));
    }

    @ShellMethod(value = "Remove user from DB.", key = {"user remove", "user-remove"})
    @Transactional
    public void remove(@ShellOption(help = "user ID. You can use \"user list\" command to found ID", value = {"user-id", "uid", "userId", "id"}) String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @ShellMethod(value = "Get user from DB.", key = {"user get", "user-get"})
    public User get(@ShellOption(help = "user ID.", value = {"user-id", "uid", "userId", "id"}) String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ShellMethod(value = "Get all users from DB.", key = {"user list", "user-list"})
    public List<User> list() {
        return userRepository.findAll();
    }
}
