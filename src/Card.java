import java.util.ArrayList;

public class Card {


    public static ArrayList<Card> cards = new ArrayList<>();



    private int Doration;
    private String name;
    private int Level;
    private int CardAttack;
    private String SpecialCard;
    private int PlayerDamage;
    private double UpgradeCost;


    //baraye admin
    public Card(String name,int Level , int Doration , int playerDamage ,double UpgradeCost, int cardAttack  ){
        this.CardAttack=cardAttack;
        this.Doration=Doration;
        this.name=name;
        this.Level=Level;
        this.PlayerDamage=playerDamage;
        this.UpgradeCost=UpgradeCost;
        SpecialCard="Admin";
        addCard(this);

    }
    //baraykhodbarname
    public Card(String SpecialCard,String name,int Level , int Doration , int playerDamage ,double UpgradeCost, int cardAttack  ){
        this.CardAttack=cardAttack;
        this.Doration=Doration;
        this.name=name;
        this.Level=Level;
        this.PlayerDamage=playerDamage;
        this.UpgradeCost=UpgradeCost;
        this.SpecialCard=SpecialCard;
        addCard(this);

    }
    private void addCard(Card card){
        cards.add(card);
    }


    public String toFileFormatcard() {
        return (SpecialCard + "," + name+"," + Level +"," + Doration+"," +PlayerDamage+"," +UpgradeCost+","+ CardAttack);
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
    public double getUpgradeCost() {
        return UpgradeCost;
    }

    public void setUpgradeCost(double UpgradeCost) {
        this.UpgradeCost = UpgradeCost;
    }
}