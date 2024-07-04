import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LoginMenu extends Menu {
    private int failedAttempts = 0;
    private LocalDateTime lastFailedAttempt;
    public void userLogin(String username, String password){
        if(User.returnUserByUsername(username)==null){
            inputOutput.printer(CheckResult.USERNAME_DOESNT_EXIST);
            return;
        }
        User user = User.returnUserByUsername(username);
        if(user.getPassword().equals(password)){
            inputOutput.printer(CheckResult.SUCCESSFUL_LOGIN);
            User.loggedInUser=user;
            return;
        }
        inputOutput.printer(CheckResult.WRONG_PASSWORD,calculateRemainingTime()+"");
        failedAttempts++;
        lastFailedAttempt = LocalDateTime.now();
        check(user.getPassword());
        inputOutput.printer(CheckResult.SUCCESSFUL_LOGIN);
        User.loggedInUser=user;

    }
    private int calculateRemainingTime() {
        if (lastFailedAttempt == null) {
            return 0;
        }
        LocalDateTime now = LocalDateTime.now();
        long elapsedTime = ChronoUnit.SECONDS.between(lastFailedAttempt, now);
        int waitTime = failedAttempts * 5;
        int remainingTime = waitTime - (int) elapsedTime;

        return Math.max(remainingTime, 0);
    }
    private void check(String password){
        if(calculateRemainingTime()==0 && Main.scanner.hasNext() && Main.scanner.nextLine().equals(password)){
            lastFailedAttempt=null;
            return;
        }
        else if(calculateRemainingTime()>0 && Main.scanner.hasNext()){
            inputOutput.printer(CheckResult.SECONDS_TRY_AGAIN,calculateRemainingTime()+"");
            Main.scanner.nextLine();
            check(password);
        }
        else if(calculateRemainingTime()==0 && Main.scanner.hasNext()){
            String input=Main.scanner.nextLine();
            if(!input.equals(password)){
                inputOutput.printer(CheckResult.WRONG_PASSWORD,calculateRemainingTime()+"");
                failedAttempts++;
                lastFailedAttempt = LocalDateTime.now();
                check(password);
            }else {
                lastFailedAttempt=null;
                return;
            }
        }

    }
//    private void check(String password) {
//        Scanner scanner = Main.scanner;
//        System.out.println(password);
//        while (true) {
//            int remainingTime = calculateRemainingTime();
//            if (remainingTime > 0) {
//                inputOutput.printer(CheckResult.SECONDS_TRY_AGAIN, remainingTime + "");
//                scanner.nextLine(); // consume the input to proceed
//                continue; // recheck remaining time after consuming input
//            }
//            if (remainingTime == 0) {
//                String input = scanner.nextLine();
//                if (!input.equals(password)) {
//                    inputOutput.printer(CheckResult.WRONG_PASSWORD, calculateRemainingTime() + "");
//                    failedAttempts++;
//                    lastFailedAttempt = LocalDateTime.now();
//                } else {
//                    inputOutput.printer(CheckResult.SUCCESSFUL_LOGIN);
//                    lastFailedAttempt = null;
//                    return; // Successful login, exit the method
//                }
//            }
//        }
//    }


    public void forgetPassword(String username){
        User user=User.returnUserByUsername(username);
        if(user==null){
            inputOutput.printer(CheckResult.USERNAME_DOESNT_EXIST);
            return;
        }
        inputOutput.printer(CheckResult.FORGET_PASSWORD_QUESTION);
        String answer = Main.scanner.nextLine();
        if(!answer.equals(user.getPassWordRecoveryAnswer())){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            return;
        }else {
            inputOutput.printer(CheckResult.NEW_PASSWORD);
            String newPassword = Main.scanner.nextLine();
            if(!isValidPassword(newPassword)){
                inputOutput.printer(CheckResult.WEAK_PASSWORD);
                return;
            }
            user.setPassword(newPassword);
            inputOutput.printer(CheckResult.SUCCESSFUL);
        }

    }

}
