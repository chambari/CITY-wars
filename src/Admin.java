import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {
    public static boolean loggedInAdmin=false;
    public static final String password="admin";
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }
    public void addCard(){
        inputOutput.printer(CheckResult.NEW_CARD_INFO);
        String input=Main.scanner.nextLine();
        if(!input.matches("name -n (?<Name>\\S+) card defence/attack -a (?<Attack>\\d+) duration -d (?<Duration>\\d+) player damage -p (?<Damage>\\d+) update level -l (?<Level>\\d+) update cost -c (?<Cost>\\S+) price -p (?<Price>\\S+) Character -t (?<Character>\\S+)")){
            inputOutput.printer(CheckResult.INVALID_COMMAND);
            return;
        }
        Matcher matcher=getCommandMatcher(input,"name -n (?<Name>\\S+) card defence/attack -a (?<Attack>\\d+) duration -d (?<Duration>\\d+) player damage -p (?<Damage>\\d+) update level -l (?<Level>\\d+) update cost -c (?<Cost>\\S+) price -p (?<Price>\\S+) Character -t (?<Character>\\S+)");
        if(matcher.group("Name").isEmpty() || matcher.group("Attack").isEmpty() || matcher.group("Duration").isEmpty() || matcher.group("Damage").isEmpty()|| matcher.group("Level").isEmpty() ||matcher.group("Cost").isEmpty()|| matcher.group("Price").isEmpty() || matcher.group("Character").isEmpty()){
            inputOutput.printer(CheckResult.EMPTY_FIELD);
            return;
        }
        if(Card.returnCardByName(matcher.group("Name"))!=null){
            inputOutput.printer(CheckResult.CARD_EXISTS);
            return;
        }
        Card newCard= new Card(matcher.group("Name"),Integer.parseInt(matcher.group("Level")),Integer.parseInt(matcher.group("Duration")),Integer.parseInt(matcher.group("Damage")),Integer.parseInt(matcher.group("Cost")),Integer.parseInt(matcher.group("Attack")),Integer.parseInt(matcher.group("Price")), matcher.group("Character"));
        inputOutput.printer(CheckResult.SUCCESSFUL);

    }
    public void editCard(){
        printAllCards();
        inputOutput.printer(CheckResult.ENTER_NUMBER);
        inputOutput.printer(CheckResult.BACK_TO_PREVIOUS_MENU);
        String input=Main.scanner.nextLine();
        if(input.equals("back")){
            inputOutput.printer(CheckResult.ADMIN_LOGOUT);
            loggedInAdmin=false;
            ProgramController programController=new ProgramController();
            programController.Run();
            return;
        }
        int numCard=Integer.parseInt(input);
        numCard--;
        Card card=Card.cards.get(numCard);
        String info= card.getName()+","+card.getCardAttack()+","+card.getUpgradeCost()+","+card.getDoration()+","+card.getLevel()+","+card.getPlayerDamage()+","+card.getPrice()+","+card.getCharacter();
        inputOutput.printer(CheckResult.CARD_PROPERTY,info);
        inputOutput.printer(CheckResult.ENTER_NUMBER);
        inputOutput.printer(CheckResult.BACK_TO_PREVIOUS_MENU);
         input=Main.scanner.nextLine();
         if(input.equals("back")){
             editCard();
         }
        inputOutput.printer(CheckResult.ENTER_NEW_VALUE);
        String newValue=Main.scanner.nextLine();
        inputOutput.printer(CheckResult.ARE_YOU_SURE_EDIT);
        if(Main.scanner.nextLine().equals("y")) {
            switch (input) {
                case "1":
                    if(Card.returnCardByName(newValue)==null) {
                        card.setName(newValue);
                    }
                    else {
                        inputOutput.printer(CheckResult.CARD_NAME_EXISTS);
                        return;
                    }
                    break;
                case "2":
                    card.setCardAttack(Integer.parseInt(newValue));
                    break;
                case "3":
                    card.setUpgradeCost(Integer.parseInt(newValue));
                    break;
                case "4":
                    card.setDoration(Integer.parseInt(newValue));
                    break;
                case "5":
                    card.setLevel(Integer.parseInt(newValue));
                    break;
                case "6":
                    card.setPlayerDamage(Integer.parseInt(newValue));
                    break;
                case "7":
                    card.setPrice(Integer.parseInt(newValue));
                    break;
                case "8":
                    card.setCharacter(newValue);
                    break;
                default:
                    inputOutput.printer(CheckResult.INVALID_RESPONSE);
                    break;
            }
            inputOutput.printer(CheckResult.SUCCESSFUL);
        }
    }
    public void removeCard() {
        printAllCards();
        inputOutput.printer(CheckResult.ENTER_NUMBER);
        inputOutput.printer(CheckResult.BACK_TO_PREVIOUS_MENU);
        String input = Main.scanner.nextLine();
        if (input.equals("back")) {
            inputOutput.printer(CheckResult.ADMIN_LOGOUT);
            loggedInAdmin=false;
            ProgramController programController = new ProgramController();
            programController.Run();
            return;
        }
        int numCard = Integer.parseInt(input);
        numCard--;
        Card card = Card.cards.get(numCard);
        inputOutput.printer(CheckResult.ARE_YOU_SURE_REMOVE);
        if (Main.scanner.nextLine().equals("y")) {
            Card.cards.remove(card);
            for(User user: User.users){
                user.cards.remove(card);
            }
            inputOutput.printer(CheckResult.SUCCESSFUL);
        }
    }
    public void showAllPlayer(){
        for(User user: User.users) {
            String info= user.getUsername()+","+user.getLevel()+","+user.getCoins();
            inputOutput.printer(CheckResult.SHOW_ALL_PLAYERS,info);
        }
    }
    public void printAllCards(){
        int i=1;
        for(Card card :Card.cards){
            inputOutput.printer(CheckResult.PRINT_ALL_CARDS,(i++)+"- "+card.getName());
        }
    }
}
