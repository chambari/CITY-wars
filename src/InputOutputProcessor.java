

import java.util.Scanner;

public class InputOutputProcessor {
    public static final InputOutputProcessor instance = new InputOutputProcessor();
    final Scanner scanner = new Scanner(System.in);
    private InputOutputProcessor() {

    }
    boolean printer(CheckResult a) {
        if(a == CheckResult.ALL_COMMANDS) {

            System.out.println("Welcome to City Wars. Please write your commands in the suggested ways.");
            System.out.println("----------------------------------------------------------------");
            System.out.println("-creating user : user create -u <Username> -p <Password> <PasswordConfirmation> -email <Email> -n <Nickname>");
            System.out.println("-creating user with random password: user create -u <Username> -p random -email <Email> -n <Nickname>");
            System.out.println("-logging in: user login -u <Username> -p <Password>");
            System.out.println("-if you forgot your pass: Forgot my password -u <Username>");
            System.out.println("-changing username: Profile change -u <Username>");
            System.out.println("-changing nickname: Profile change -n <Nickname>");
            System.out.println("-changing email: profile change -e <Email>");
            System.out.println("-changing pass: profile change password -o <OldPass> -n <NewPass>");
            System.out.println("-log out");
            System.out.println("-Show information");
            System.out.println("-Show cards");
            System.out.println("-Start Game ");
            System.out.println("-Show game history");
            System.out.println("-login admin : login admin <pass> ");
            System.out.println("-Close the program: Exit");
            return true;
        }
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
        if(a == CheckResult.FORGET_PASSWORD_QUESTION) {
            System.out.println("Write your answer:");
            System.out.println("1-What is your father's name?");
            System.out.println("2-What is your favorite color?");
            System.out.println("3-What was the name of your first pet?");
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
        if(a == CheckResult.NEW_CARD_INFO) {

            System.out.println("New Card Information:(name -n <Name> card defence/attack -a <Attack> duration -d <Duration> player damage -p <Damage> update level -l <Level> update cost -c <Cost> price -p <Price> Character -t <Character>");
            System.out.println("Name:");
            System.out.println("Attack(between 10 and 100):");
            System.out.println("Duration(between 1 and 5):");
            System.out.println("Player Damage(between 10 and 50):");
            System.out.println("Upgrade Level:");
            System.out.println("Upgrade Cost:");
            System.out.println("Price:");
            System.out.println("Character:");
            return true;
        }
        if(a == CheckResult.CARD_EXISTS) {

            System.out.println("Card already Exists!");
            return true;
        }
        if(a == CheckResult.INCORRECT_ADMIN_PASSWORD){
            System.out.println("Admin`s password is incorrect!");
            return true;
        }
        if(a == CheckResult.ADMIN_COMMANDS){

            System.out.println("-Chose a number:  1-Add Card         2-Edit Card     3-Remove card   4-Show players ");
            return true;
        }
        if(a == CheckResult.ADMIN_LOGOUT){
            System.out.println("Admin logged out!");
            return true;
        }
        if(a == CheckResult.ENTER_NUMBER){
            System.out.println("Please enter a number: ");
            return true;
        }
        if(a == CheckResult.BACK_TO_PREVIOUS_MENU){
            System.out.println("Enter 'back' if you want to return to the previous menu.");
            return true;
        }
        if(a == CheckResult.ENTER_NEW_VALUE){
            System.out.println("Enter your new value: ");
            return true;
        }
        if(a == CheckResult.ARE_YOU_SURE_EDIT){
            System.out.print("Are you sure you want to edit this card?(y/n): ");
            return true;
        }
        if(a == CheckResult.ARE_YOU_SURE_REMOVE){
            System.out.print("Are you sure you want to remove this card?(y/n): ");
            return true;
        }
        if(a == CheckResult.CARD_NAME_EXISTS) {

            System.out.println("Card name already Exists!");
            return true;
        }

        //BAKHSH MARBOOOT BE STORE!!!!


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
        if (a == CheckResult.PRINT_ALL_CARDS) {


            System.out.println(massage);
            return true;
        }
        if (a == CheckResult.CARD_PROPERTY){

            String[] info=massage.split(",");
            System.out.println("1- Name: " +info[0]);
            System.out.println("2- Card Attack/Defence point: " + info[1]);
            System.out.println("3- Upgrade Cost: " + info[2]);
            System.out.println("4- Card Duration: " + info[3]);
            System.out.println("5- Upgrade Level: " + info[4]);
            System.out.println("6- Player Damage " + info[5]);
            System.out.println("7- Price: " +info[6]);
            System.out.println("8- Character: " +info[7]);
            return true;
        }
        if (a == CheckResult.SHOW_ALL_PLAYERS) {

            String[] info=massage.split(",");
            System.out.println("Username: "+info[0]+" , Level: "+info[1]+" , Amount of Coins: "+info[2]);
            return true;
        }
        //BAKHSH MARBOOOT BE STORE!!!!
        if (a == CheckResult.SHOW_PLAYER_COIN) {

            System.out.println("Amount of coins are: "+massage);
            return true;
        }
        if (a == CheckResult.SHOW_PLAYER_CARD) {

            String[] info=massage.split(",");
            System.out.println("Card Name: "+info[0]+" , Card Attack/Defence point: "+info[1]+"Card Upgrade Cost: "+info[2]+" , Card Duration: "+info[3]+", Card Level: "+info[4]+", and card player damage: "+info[5]);
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


