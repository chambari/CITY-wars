import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu extends Menu{
    public boolean createUser(String username, String password, String email, String nickname) {
        //bayad az file shamel user ha ham in mavared check shavad!!!
        if (username.isEmpty() || password.isEmpty()|| email.isEmpty() || nickname.isEmpty()){
            inputOutput.printer(CheckResult.EMPTY_FIELD);
            return false;
        }
        if (isUsernameTaken(username) || !isValidUsername(username)) {
            inputOutput.printer(CheckResult.INVALID_USERNAME);
            return false;
        }

        if (!isValidPassword(password)) {
            inputOutput.printer(CheckResult.WEAK_PASSWORD);
            return false;
        }

        if (!isValidEmail(email)) {
            inputOutput.printer(CheckResult.INVALID_EMAIL);
            return false;
        }

        User user = new User(username, password, email, nickname);
        securityQuestion();
        captcha();
        return true;
    }

}
