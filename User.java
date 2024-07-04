import java.util.ArrayList;

public class User {







    public static User loggedInUser=null;
    private String Username, Password, Nickname, Email, PassWordRecoveryAnswer;
    private int PasswordRecoveryQuestionNumber;
    private long Level=1, HP=100, XP = 0, Coins=0;
    //    ArrayList<Card> cards = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    User(String Username, String Password, String Nickname, String Email, int QuestionNumber, String Answer){
        this.Username = Username;
        this.Password = Password;
        this.Nickname = Nickname;
        this.Email = Email;
        this.PasswordRecoveryQuestionNumber =QuestionNumber;
        this.PassWordRecoveryAnswer = Answer;
        addUser(this);
        loggedInUser=this;
    }

    User(String Username, String Password, String Nickname, String Email){
        this.Username = Username;
        this.Password = Password;
        this.Nickname = Nickname;
        this.Email = Email;
        addUser(this);
        loggedInUser=this;
    }
    public void levelUp(){
        while (XP>=(Level*100)){
            XP-=Level*100;
            Level++;
        }
    }
    public static User returnUserByUsername(String username){
        for (User user : users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    private void addUser(User user){
        users.add(user);
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getHP() {
        return HP;
    }

    public void setHP(long HP) {
        this.HP = HP;
    }

    public long getXP() {
        return XP;
    }

    public void setXP(long XP) {
        this.XP = XP;
    }

    public long getCoins() {
        return Coins;
    }

    public void setCoins(long coins) {
        Coins = coins;
    }

    public String getPassWordRecoveryAnswer() {
        return PassWordRecoveryAnswer;
    }

    public void setPassWordRecoveryAnswer(String passWordRecoveryAnswer) {
        PassWordRecoveryAnswer = passWordRecoveryAnswer;
    }

    public int getPasswordRecoveryQuestionNumber() {
        return PasswordRecoveryQuestionNumber;
    }

    public void setPasswordRecoveryQuestionNumber(int passwordRecoveryQuestionNumber) {
        PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
    }
    public String toFileFormat(int userNumber) {
        return "User" + userNumber + ":" + getUsername() + "," + getPassword() + "," + getNickname() + "," + getEmail() + "," + getPasswordRecoveryQuestionNumber();
    }
}