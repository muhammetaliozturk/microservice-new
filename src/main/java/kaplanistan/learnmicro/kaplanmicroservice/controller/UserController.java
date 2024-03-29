package kaplanistan.learnmicro.kaplanmicroservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import kaplanistan.learnmicro.kaplanmicroservice.exception.UserNotFoundException;
import kaplanistan.learnmicro.kaplanmicroservice.model.User;
import kaplanistan.learnmicro.kaplanmicroservice.service.UserDaoService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDaoService userDaoService;

    public UserController (UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping
    public List<User> getAll() {
        return userDaoService.getAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getById(@PathVariable Integer id) {
        var user = userDaoService.getUser(id);

        if (user == null) {
            throw new UserNotFoundException("userId: " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAll());
        entityModel.add(linkBuilder.withRel("all_users"));

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userDaoService.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
        //ok da kullanılabilir
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        var user = userDaoService.getUser(id);

        if (user == null) {
            throw new UserNotFoundException("userId: " + id);
        }

        userDaoService.deleteUser(id);

        return ResponseEntity.ok("id+" + id + " li kullanıcı silindi");
    }
}
