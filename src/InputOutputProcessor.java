

import java.util.Scanner;

public class InputOutputProcessor {
    public static final InputOutputProcessor instance = new InputOutputProcessor();
    final Scanner scanner = new Scanner(System.in);
    private InputOutputProcessor() {

    }
    boolean printer(CheckResult a) {
        if(a == CheckResult.EMPTY_FIELD) {

            System.out.println("Empty Field!");
            return true;
        }
        if(a == CheckResult.INVALID_USERNAME) {

            System.out.println("Invalid Username!");
            return true;
        }
        if(a == CheckResult.ALREADY_LOGGED_IN) {

            System.out.println("Somebody has already logged in!");
            return true;
        }
        if(a == CheckResult.SECURITY_QUESTION) {

            System.out.println("User created successfully. Please choose a security question: ( choose it with: question pick -q <QuestionNumber> -a <Answer> -c <AnswerConfirm> )");
            System.out.println("1-What is your father's name?");
            System.out.println("2-What is your favorite color?");
            System.out.println("3-What was the name of your first pet?");
            return true;
        }
        if(a == CheckResult.INVALID_RESPONSE) {

            System.out.println("Invalid Response! Try Again!");
            return true;
        }
        if(a == CheckResult.SUCCESSFUL) {
            System.out.println("Successful!");
            return true;
        }
        if(a == CheckResult.USERNAME_DOESNT_EXIST) {
            System.out.println("Username doesnt exist!");
            return true;
        }
        if(a == CheckResult.SUCCESSFUL_LOGIN) {
            System.out.println("user logged in successfully!");
            return true;
        }
        if(a == CheckResult.NEW_PASSWORD) {
            System.out.println("Enter your new password: ");
            return true;
        }
        if(a == CheckResult.WEAK_PASSWORD) {
            System.out.println("Password is too weak. It must be at least 8 characters long and contain a mix of uppercase, lowercase, and numbers.");
            return true;
        }
        if(a == CheckResult.CURRENT_PASSWORD_INCORRECT) {
            System.out.println("Current password is incorrect!");
            return true;
        }
        if(a == CheckResult.NEED_NEW_PASSWORD) {
            System.out.println("Please enter a new password!");
            return true;
        }
        if(a == CheckResult.PASSWORD_CHANGED_SUCCESSFULLY) {
            System.out.println("Password changed successfully!");
            return true;
        }
        if(a == CheckResult.INVALID_EMAIL) {

            System.out.println("Invalid Email!");
            return true;
        }
        if(a == CheckResult.INVALID_COMMAND) {

            System.out.println("Invalid Command!");
            return true;
        }


        return false;
    }
    boolean printer(CheckResult a,String massage) {
        if (a == CheckResult.PASSWORD_CONFIRMATION) {

            System.out.println("Your random password is: " + massage);
            System.out.println("Please enter your password: ");
            return true;
        }
        if (a == CheckResult.WRONG_PASSWORD) {

            System.out.println("Password and Username don’t match!");
            System.out.println("Try again in " + massage + " seconds ");
            return true;
        }
        if (a == CheckResult.SECONDS_TRY_AGAIN) {

            System.out.println("Try again in " + massage + " seconds ");
            return true;
        }
        if (a == CheckResult.SHOW_USER_INFORMATION) {

            String[] info=massage.split(",");
            System.out.println("Username: " + info[0]);
            System.out.println("Nickname: " + info[1]);
            System.out.println("Password: " +info[2]);
            System.out.println("Email: " + info[3]);
            return true;
        }


        return false;
    }
    public static InputOutputProcessor getInstance() {
        return instance;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}


