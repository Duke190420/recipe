package recipes.controllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.businessLayer.User;
import recipes.businessLayer.UserRepository;
import recipes.serviceLayer.EmailValidator;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/api/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        if (!EmailValidator.validateEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword().trim().length() < 8 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        if (!userRepo.userExist(user)) {
            user.setRole("ROLE_USER");
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
