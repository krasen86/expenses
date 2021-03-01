package se.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.expense.model.Category;
import se.expense.model.User;
import se.expense.repository.CategoryRepository;
import se.expense.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    ResponseEntity<?> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        User addedUser = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/users/" + addedUser.getUserID())).body(addedUser);

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user) throws URISyntaxException {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
           User addedUser = userRepository.save(user);
            return ResponseEntity.ok().body(addedUser);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id)  {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
