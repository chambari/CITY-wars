import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController  {
    SignUpMenu signUp=new SignUpMenu();
    LoginMenu loginMenu=new LoginMenu();
    ProfileMenu profileMenu=new ProfileMenu();
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
    public  void Run() {
        boolean running=true;
        while (running) {
            String Command = Main.scanner.nextLine();
            CheckCommands(Command);
        }
    }
    public  Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    public  void CheckCommands(String Command) {
        if (Command.matches("user create -u (?<Username>\\S+) -p (?<Password>\\S+) (?<PasswordConfirmation>\\S+) -email (?<Email>\\S+) -n (?<Nickname>.+)")) {
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user create -u (?<Username>\\S+) -p (?<Password>\\S+) (?<PasswordConfirmation>\\S+) -email (?<Email>\\S+) -n (?<Nickname>.+)");
                if(matcher.group("username").isEmpty()|| matcher.group("password").isEmpty()|| matcher.group("Email").isEmpty()|| matcher.group("Nickname").isEmpty()){
                    inputOutput.printer(CheckResult.EMPTY_FIELD);
                    return;
                }
                signUp.createUser(matcher.group("username"), matcher.group("password"), matcher.group("Email"), matcher.group("Nickname"));
                securityQuestion();
                captcha();
            }
        } else if (Command.matches("user create -u (?<Username>\\S+) -p random -email (?<Email>\\S+) -n (?<Nickname>.+)")) {
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user create -u (?<Username>\\S+) -p random -email (?<Email>\\S+) -n (?<Nickname>.+)");
                if(matcher.group("username").isEmpty()|| matcher.group("Email").isEmpty()|| matcher.group("Nickname").isEmpty()){
                    inputOutput.printer(CheckResult.EMPTY_FIELD);
                    return;
                }
                String password=generateRandomPassword();
                passwordConformation(password);
                signUp.createUser(matcher.group("username"), password, matcher.group("Email"), matcher.group("Nickname"));
                securityQuestion();
                captcha();

            }
        }else if(Command.matches("user login -u (\\S+) -p (\\S+)")){
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user login -u (\\S+) -p (\\S+)");
                loginMenu.userLogin(matcher.group("Username"), matcher.group("password"));
            }
        }else if(Command.matches("Forgot my password -u (\\S+)")){
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "Forgot my password -u (\\S+)");
                loginMenu.forgetPassword(matcher.group("Username"));
            }
        }else if(Command.matches("log out")){
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else {
                User.loggedInUser = null;
                inputOutput.printer(CheckResult.SUCCESSFUL);
            }
        }else if(Command.matches("Show information")) {
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else{
                profileMenu.showInfo();
            }
        }else if(Command.matches("Profile change -u (?<Username>\\S+)")) {
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else {
                Matcher matcher = getCommandMatcher(Command, "Profile change -u (?<Username>\\S+)");
                profileMenu.changeUsername(matcher.group("Username"));
            }
        }else if(Command.matches("Profile change -n (?<Nickname>.+)")) {
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else {
                Matcher matcher = getCommandMatcher(Command, "Profile change -n (?<Nickname>.+)");
                profileMenu.changeNickname(matcher.group("Nickname"));

            }
        }else if(Command.matches("profile change password -o (?<OldPass>\\S+) -n (?<NewPass>\\S+)")){
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else {
                Matcher matcher = getCommandMatcher(Command, "profile change password -o (?<OldPass>\\S+) -n (?<NewPass>\\S+)");
                profileMenu.changePassword(matcher.group("OldPass"), matcher.group("NewPass"));

            }
        }else if(Command.matches("profile change -e (?<Email>\\S+)")){
            if (User.loggedInUser == null)
                inputOutput.printer(CheckResult.INVALID_COMMAND);
            else {
                Matcher matcher = getCommandMatcher(Command, "profile change -e (?<Email>\\S+)");
                profileMenu.changeEmail(matcher.group("Email"));

            }
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
        if(matcher.group("Answer").equals(matcher.group("AnswerConfirm"))){
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
    public static String generateRandomPassword() {
        final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS;
        final SecureRandom RANDOM = new SecureRandom();
        int length = 8;
        StringBuilder password = new StringBuilder(length);
        ArrayList<Character> passwordChars = new ArrayList<>(length);

        passwordChars.add(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        passwordChars.add(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        passwordChars.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));

        for (int i = 3; i < length; i++) {
            passwordChars.add(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }
    public void passwordConformation(String password){
        inputOutput.printer(CheckResult.PASSWORD_CONFIRMATION, password);
        String newPassword = Main.scanner.nextLine();
        if(!newPassword.equals(password)){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            passwordConformation(password);
        }
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