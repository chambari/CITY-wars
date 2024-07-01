import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu {

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
