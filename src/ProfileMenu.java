public class ProfileMenu extends Menu {
    public void showInfo(){
        User user=User.loggedInUser;
        String info =   user.getUsername() + "," +
                        user.getPassword() + "," +
                        user.getNickname() + "," +
                        user.getEmail();
        inputOutput.printer(CheckResult.SHOW_USER_INFORMATION, info);
    }
    public void changeUsername(String newUsername){
        User user=User.loggedInUser;
        if (isUsernameTaken(newUsername) && !isValidUsername(newUsername)) {
            inputOutput.printer(CheckResult.INVALID_USERNAME);
            return;
        }
        user.setUsername(newUsername);
        inputOutput.printer(CheckResult.SUCCESSFUL);
    }
    public void changeNickname(String newNickname){
        User user=User.loggedInUser;
        user.setNickname(newNickname);
        inputOutput.printer(CheckResult.SUCCESSFUL);
    }
    public void changePassword(String oldPassword,String newPassword){
        User user=User.loggedInUser;
        if(!user.getPassword().equals(oldPassword)){
            inputOutput.printer(CheckResult.CURRENT_PASSWORD_INCORRECT);
            return;
        }
        if (!isValidPassword(newPassword)) {
            inputOutput.printer(CheckResult.WEAK_PASSWORD);
            return ;
        }
        if(oldPassword.equals(newPassword)){
            inputOutput.printer(CheckResult.NEED_NEW_PASSWORD);
            return;
        }
        captcha();
        user.setPassword(newPassword);
        inputOutput.printer(CheckResult.PASSWORD_CHANGED_SUCCESSFULLY);
    }
    public void changeEmail(String newEmail){
        User user=User.loggedInUser;
        if(!isValidEmail(newEmail)){
            inputOutput.printer(CheckResult.INVALID_EMAIL);
            return;
        }
        user.setEmail(newEmail);
        inputOutput.printer(CheckResult.SUCCESSFUL);
    }

}
