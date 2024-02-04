package kaplanistan.learnmicro.kaplanmicroservice.repository;

import kaplanistan.learnmicro.kaplanmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserRepository extends JpaRepository<User, Integer> {
}
