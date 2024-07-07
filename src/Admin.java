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
        if(!input.matches("name -n (?<Name>\\S+) card defence/attack -a (?<Attack>\\d+) duration -d (?<Duration>\\d+) player damage -p (?<Damage>\\d+) update level -l (?<Level>\\d+) update cost -c (?<Cost>.\\S+)")){
            inputOutput.printer(CheckResult.INVALID_COMMAND);
            return;
        }
        Matcher matcher=getCommandMatcher(input,"name -n (?<Name>\\S+) card defence/attack -a (?<Attack>\\d+) duration -d (?<Duration>\\d+) player damage -p (?<Damage>\\d+) update level -l (?<Level>\\d+) update cost -c (?<Cost>.\\S+)");
        if(matcher.group("Name").isEmpty() || matcher.group("Attack").isEmpty() || matcher.group("Duration").isEmpty() || matcher.group("Damage").isEmpty()|| matcher.group("Level").isEmpty() ||matcher.group("Cost").isEmpty()){
            inputOutput.printer(CheckResult.EMPTY_FIELD);
            return;
        }
        if(Card.returnCardByName(matcher.group("Name"))!=null){
            inputOutput.printer(CheckResult.CARD_EXISTS);
            return;
        }
        Card newCard= new Card(matcher.group("Name"),Integer.parseInt(matcher.group("Level")),Integer.parseInt(matcher.group("Duration")),Integer.parseInt(matcher.group("Damage")),Double.parseDouble(matcher.group("Cost")),Integer.parseInt(matcher.group("Attack")));
        inputOutput.printer(CheckResult.SUCCESSFUL);

    }
}
