package kaplanistan.learnmicro.kaplanmicroservice.controller;

import jakarta.validation.Valid;
import kaplanistan.learnmicro.kaplanmicroservice.exception.UserNotFoundException;
import kaplanistan.learnmicro.kaplanmicroservice.model.Post;
import kaplanistan.learnmicro.kaplanmicroservice.model.User;
import kaplanistan.learnmicro.kaplanmicroservice.repository.PostRepository;
import kaplanistan.learnmicro.kaplanmicroservice.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/user")
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getById(@PathVariable Integer id) {
        var user = userRepository.getReferenceById(id);

        if (user.getId() == null) {
            throw new UserNotFoundException("userId: " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAll());
        entityModel.add(linkBuilder.withRel("all_users"));

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userRepository.save(user);
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
        var user = userRepository.getReferenceById(id);

        if (user.getId() == null) {
            throw new UserNotFoundException("userId: " + id);
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("id+" + id + " li kullanıcı silindi");
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        var user = userRepository.getReferenceById(id);

        if (user.getId() == null) {
            throw new UserNotFoundException("userId: " + id);
        }

        post.setUser(user);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
