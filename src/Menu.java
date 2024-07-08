import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
     Matcher getCommandMatcher(String input, String regex) {
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(input);
         matcher.find();
         return matcher;
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
        }else {
            inputOutput.printer(CheckResult.SUCCESSFUL);
        }

    }

        public static void mathEquation(){
        String answer=MathEquation.generateEquation();
            if (!Main.scanner.nextLine().equals(answer) ){
                inputOutput.printer(CheckResult.INVALID_RESPONSE);
                mathEquation();
            }
            else {
                inputOutput.printer(CheckResult.SUCCESSFUL);
            }
    }

    public void securityQuestion(){
        inputOutput.printer(CheckResult.SECURITY_QUESTION);
        String nextCommand=Main.scanner.nextLine();
        if (!nextCommand.matches("question pick -q (?<QuestionNumber>\\d) -a (?<Answer>\\S+) -c (?<AnswerConfirm>\\S+)")){
            securityQuestion();
        }
        Matcher matcher=getCommandMatcher(nextCommand,"question pick -q (?<QuestionNumber>\\d) -a (?<Answer>\\S+) -c (?<AnswerConfirm>\\S+)");
        if (matcher.group("QuestionNumber").isEmpty() || matcher.group("Answer").isEmpty()||matcher.group("AnswerConfirm").isEmpty()){
            inputOutput.printer(CheckResult.EMPTY_FIELD);
            securityQuestion();
        }
        if(!matcher.group("Answer").equals(matcher.group("AnswerConfirm"))){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            securityQuestion();
        }
        try {
            int a=Integer.parseInt(matcher.group("QuestionNumber"));
        }catch (NumberFormatException E){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            securityQuestion();
        }
        User.loggedInUser.setPasswordRecoveryQuestionNumber(Integer.parseInt(matcher.group("QuestionNumber")));
        User.loggedInUser.setPassWordRecoveryAnswer(matcher.group("Answer"));

    }

}
