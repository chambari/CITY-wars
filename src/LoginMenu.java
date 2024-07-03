import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        check(password);
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
        if(calculateRemainingTime()>0 && Main.scanner.hasNext()){
            inputOutput.printer(CheckResult.SECONDS_TRY_AGAIN,calculateRemainingTime()+"");

        }
        if(calculateRemainingTime()==0 && Main.scanner.hasNext()){
            if(!Main.scanner.nextLine().equals(password)){
                inputOutput.printer(CheckResult.WRONG_PASSWORD,calculateRemainingTime()+"");
                failedAttempts++;
                lastFailedAttempt = LocalDateTime.now();
            }else{
                return;
            }
        }
        check(password);
    }


    public void forgetPassword(String username){
        User user=User.returnUserByUsername(username);
        if(user==null){
            inputOutput.printer(CheckResult.USERNAME_DOESNT_EXIST);
            return;
        }
        inputOutput.printer(CheckResult.SECURITY_QUESTION);
        String answer = Main.scanner.nextLine();
        if(!answer.equals(user.getPassWordRecoveryAnswer())){
            inputOutput.printer(CheckResult.INVALID_RESPONSE);
            return;
        }else {
            inputOutput.printer(CheckResult.NEW_PASSWORD);
            String newPassword = Main.scanner.nextLine();
            user.setPassword(newPassword);
            inputOutput.printer(CheckResult.SUCCESSFUL);
        }

    }

}
