import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController  {
    SignUpMenu signUp=new SignUpMenu();
    LoginMenu loginMenu=new LoginMenu();
    ProfileMenu profileMenu=new ProfileMenu();
    Admin admin=new Admin();
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
    public boolean running=true;
    public void Run() {
//        boolean running=true;
        while (running) {
            String Command = Main.scanner.nextLine();
            CheckCommands(Command);
        }
    }

    public  Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }
    public  void CheckCommands(String Command) {
        if (Command.matches("user create -u (?<Username>\\S+) -p (?<Password>\\S+) (?<PasswordConfirmation>\\S+) -email (?<Email>\\S+) -n (?<Nickname>.+)")) {
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user create -u (?<Username>\\S+) -p (?<Password>\\S+) (?<PasswordConfirmation>\\S+) -email (?<Email>\\S+) -n (?<Nickname>.+)");
                if(matcher.group("Username").isEmpty()|| matcher.group("Password").isEmpty()|| matcher.group("Email").isEmpty()|| matcher.group("Nickname").isEmpty()){
                    inputOutput.printer(CheckResult.EMPTY_FIELD);
                    return;
                }
                signUp.createUser(matcher.group("Username"), matcher.group("Password"), matcher.group("Email"), matcher.group("Nickname"));
            }
        } else if (Command.matches("user create -u (?<Username>\\S+) -p random -email (?<Email>\\S+) -n (?<Nickname>.+)")) {
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user create -u (?<Username>\\S+) -p random -email (?<Email>\\S+) -n (?<Nickname>.+)");
                if(matcher.group("Username").isEmpty()|| matcher.group("Email").isEmpty()|| matcher.group("Nickname").isEmpty()){
                    inputOutput.printer(CheckResult.EMPTY_FIELD);
                    return;
                }
                String password=generateRandomPassword();
                passwordConformation(password);
                signUp.createUser(matcher.group("Username"), password, matcher.group("Email"), matcher.group("Nickname"));
            }
        }else if(Command.matches("user login -u (?<Username>\\S+) -p (?<Password>\\S+)")){
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "user login -u (?<Username>\\S+) -p (?<Password>\\S+)");
                loginMenu.userLogin(matcher.group("Username"), matcher.group("Password"));
            }
        }else if(Command.matches("Forgot my password -u (?<Username>\\S+)")){
            if (User.loggedInUser != null)
                inputOutput.printer(CheckResult.ALREADY_LOGGED_IN);
            else {
                Matcher matcher = getCommandMatcher(Command, "Forgot my password -u (?<Username>\\S+)");
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
        }else if(Command.matches("Exit")){
            running=false;
        }else if(Command.matches("login admin (?<Pass>\\S+)")){
            Matcher matcher = getCommandMatcher(Command,"login admin (?<Pass>\\S+)");
            if(!Admin.password.equals(matcher.group("Pass")) ){
                inputOutput.printer(CheckResult.INCORRECT_ADMIN_PASSWORD);
                return;
            }
            Admin.loggedInAdmin=true;
            inputOutput.printer(CheckResult.ADMIN_COMMANDS);
            String input=Main.scanner.nextLine();
            switch (input) {
                case "1":
                    admin.addCard();
                    break;
                case "2":
                    admin.editCard();
                    break;
                case "3":
                    admin.removeCard();
                    break;
                case "4":
                    admin.showAllPlayer();
                    break;
                default:
                    inputOutput.printer(CheckResult.INVALID_RESPONSE);
                    break;
            }
            inputOutput.printer(CheckResult.ADMIN_LOGOUT);
            Admin.loggedInAdmin=false;

        }


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



}