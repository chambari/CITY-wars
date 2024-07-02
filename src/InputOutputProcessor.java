

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

            System.out.println("Someone has already logged in!");
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
        if(a == CheckResult.COST_ERROR) {
            System.out.println("Invalid Cost!");
            return true;
        }
        if(a == CheckResult.ID_ERROR) {
            System.out.println("Invalid id!");
            return true;
        }
        if(a == CheckResult.INVALID_COMMAND) {

            System.out.println("Invalid Command!");
            return true;
        }
        if(a == CheckResult.NOT_ENOUGH_CREDIT) {

            System.out.println("Low Credit!");
            return true;
        }
        if(a == CheckResult.PASSWORD_ERROR) {


            System.out.println("Invalid Password!");
            return true;
        }
        if(a == CheckResult.INVALID_PERCENT) {

            System.out.println("Invalid Percent");
            return true;
        }
        if(a == CheckResult.USER_NAME_ERROR) {

            System.out.println("Invalid Username!");
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


