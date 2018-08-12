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
public class UserConsoleController {

    private final UserRepository userRepository;

    public UserConsoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ShellMethod(value = "Add user to DB.", key = {"user add", "user-add"})
    @Transactional
    public User add(@ShellOption(help = "User username. Use quotes if you need first name and last name, e.g. \"John Smith jr.\"", value = {"username", "name"}) String username,
                    @ShellOption(help = "User email") String email) {
        return userRepository.save(new User(username, email));
    }

    @ShellMethod(value = "Remove user from DB.", key = {"user remove", "user-remove"})
    @Transactional
    public void remove(@ShellOption(help = "User ID. You can use \"user find\" command to found ID", value = {"user-id", "uid", "userId", "id"}) String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @ShellMethod(value = "Find user from DB.", key = {"user find", "user-find"})
    public List<User> find(@ShellOption(help = "Username or email, or part of them. Can be empty if you want to find all users", defaultValue = "") String field) {
        return userRepository.findByAny(field);
    }

    @ShellMethod(value = "Get user from DB by ID.", key = {"user get", "user-get"})
    public User get(@ShellOption(help = "User ID.", defaultValue = "", value = {"id", "uid", "userId", "user-id"}) String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
