import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
     Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


     boolean isUsernameTaken(String username) {
        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z1-9_]*$");
    }

     boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit;
    }

     boolean isValidEmail(String email) {
        return email.matches("\\S+@\\S+\\.com");
    }
    public void captcha(){
        String captcha = AsciiArtCaptcha.generateCaptcha();
        String input=Main.scanner.nextLine();
        if(!input.equals(captcha)){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            captcha();
        }
        inputOutput.printer(CheckResult.SUCCESSFUL);

    }

}
