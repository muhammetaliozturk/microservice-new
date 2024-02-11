package kaplanistan.learnmicro.kaplanmicroservice.repository;

import kaplanistan.learnmicro.kaplanmicroservice.model.Post;
import kaplanistan.learnmicro.kaplanmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
