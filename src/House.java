public class House {

    private int attack;
    private int playerdamage;
    private String name;







    public void setAttack(int a){
        attack =a;
    }
    public void setPlayerdamage(int d){
        playerdamage=d;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public int getAttack(){
        return attack;
    }
    public int getPlayerdamage(){
        return playerdamage;
    }


    public void GetCard(Card card){
        this.attack =card.getCardAttack();
        this.name=card.getName();
        this.playerdamage=card.getPlayerDamage();
    }













}
