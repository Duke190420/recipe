package recipes.businessLayer;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class UserRepository {
    final private Map<String, User> users = new ConcurrentHashMap<>();

    public User findUserByEmail(@RequestParam String email) {
        return users.get(email);
    }

    public void save(@RequestParam User user) {
        users.put(user.getEmail(), user);
    }

    public boolean userExist(@RequestParam User user) {
        return users.containsKey(user.getEmail());
    }
}
