package recipes.serviceLayer;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_REGEX = ".+@.+\\..+";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(@RequestParam String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}