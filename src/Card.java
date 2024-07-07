import java.util.ArrayList;

public class Card {

    public static ArrayList<Card> cards = new ArrayList<>();

    private int Doration;
    private String name;
    private int Level;
    private int CardAttack;
    private String SpecialCard;
    private int PlayerDamage;
    private int UpgradeCost;
    private int Price;
    private String Character;


    //baraye admin
    public Card(String name,int Level , int Doration , int playerDamage ,int UpgradeCost, int cardAttack, int Price, String Character ){
        this.CardAttack=cardAttack;
        this.Doration=Doration;
        this.name=name;
        this.Level=Level;
        this.PlayerDamage=playerDamage;
        this.UpgradeCost=UpgradeCost;
        SpecialCard="Admin";
        this.Price=Price;
        this.Character=Character;
        addCard(this);

    }
    //baraykhodbarname
    public Card(String SpecialCard,String name,int cardAttack , int Doration , int playerDamage ,int price,int Upgradelevel ,int UpgradeCost,String Character){
        this.SpecialCard=SpecialCard;
        this.name=name;
        this.CardAttack=cardAttack;
        this.Doration=Doration;
        this.PlayerDamage=playerDamage;
        this.Price=price;
        this.Level=Upgradelevel;
        this.UpgradeCost=UpgradeCost;
        this.Character=Character;

        addCard(this);

    }
    private void addCard(Card card){
        cards.add(card);
    }


    public String toFileFormatcard() {
        return (SpecialCard+","+name+","+CardAttack+","+Doration+","+PlayerDamage+","+Price+","+Level+","+UpgradeCost+","+Character);
    }

    public static Card returnCardByName(String name){
        for(Card card : cards){
            if(card.getName().equals(name)){
                return card;
            }
        }
        return null;
    }

    // Getter and Setter for Doration
    public int getDoration() {
        return Doration;
    }

    public void setDoration(int Doration) {
        this.Doration = Doration;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Level
    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    // Getter and Setter for CardDefence


    // Getter and Setter for CardAttack
    public int getCardAttack() {
        return CardAttack;
    }

    public void setCardAttack(int CardAttack) {
        this.CardAttack = CardAttack;
    }


    // Getter and Setter for SpecialCard
    public String getSpecialCard() {
        return SpecialCard;
    }

    public void setSpecialCard(String SpecialCard) {
        this.SpecialCard = SpecialCard;
    }

    // Getter and Setter for PlayerDamage
    public int getPlayerDamage() {
        return PlayerDamage;
    }

    public void setPlayerDamage(int PlayerDamage) {
        this.PlayerDamage = PlayerDamage;
    }

    // Getter and Setter for UpgradeCost
    public int getUpgradeCost() {
        return UpgradeCost;
    }

    public void setUpgradeCost(int UpgradeCost) {
        this.UpgradeCost = UpgradeCost;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getCharacter() {
        return Character;
    }

    public void setCharacter(String character) {
        Character = character;
    }

    //in tabe baraye chek kardan Upgradelevel hast;
    //baraye user fild level ro bnvis
    public static boolean cheakUpgradelevel(User user , Card card){
        if(user.getLevel()>=card.getLevel()){
            return true;
        }
        return false;
    }



    public static boolean cheakUpgradecost(User user,Card card){
        if(user.getCoins()>=card.getUpgradeCost()){
            int upgc=card.getUpgradeCost();
            card.setUpgradeCost((upgc*5)/4);
            return true;
        }
        return false;
    }
}