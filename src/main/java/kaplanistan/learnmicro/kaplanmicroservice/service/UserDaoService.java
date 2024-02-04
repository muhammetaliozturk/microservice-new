package kaplanistan.learnmicro.kaplanmicroservice.service;

import kaplanistan.learnmicro.kaplanmicroservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> users = new ArrayList<>();

    private static Integer idCount = 0;

    static {
        users.add(new User(++idCount,"Kara Kaplan", LocalDate.now().minusYears(32)));
        users.add(new User(++idCount, "Beyaz Kaplan", LocalDate.now().minusYears(28)));
        users.add(new User(++idCount, "Sarı Kaplan", LocalDate.now().minusYears(4)));
    }

    public List<User> getAll() {
        return users;
    }

    public void addUser(User user) {
        user.setId(++idCount);
        users.add(user);
    }

    public User getUser(Integer id) {
        return users.stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void deleteUser(Integer id) {
        users.removeIf(x -> x.getId().equals(id));
    }
}
