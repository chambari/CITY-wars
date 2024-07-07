import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
    public void ShowPlayerCoin(){
        inputOutput.printer(CheckResult.SHOW_PLAYER_COIN,User.loggedInUser.getCoins()+"");
    }


    public void showPlayerCard(){
        for( Card card: User.loggedInUser.cards){
            String info= card.getName()+","+card.getCardAttack()+","+card.getUpgradeCost()+","+card.getDoration()+","+card.getLevel()+","+card.getPlayerDamage();
            inputOutput.printer(CheckResult.SHOW_PLAYER_CARD,info);
        }
    }



    //eslah shavad!!!
    public ArrayList<Card> showCardThatUserDosentHave(User user, ArrayList<Card> specialcard){
        ArrayList<Card> cardthatuserdosenthave = new ArrayList<Card>();
        boolean x;
        for (int i =0 ; i<specialcard.size();i++){
            x=true;
            for(int j=0;j<user.usercard.size();j++){
                if(specialcard.get(i).getName().equals(user.usercard.get(j).getName())){
                    x=false;
                    break;
                }
            }
            if(x){
                cardthatuserdosenthave.add(specialcard.get(i));
            }
        }
        return cardthatuserdosenthave;
    }




    public Card SHOWcardthatUSERdosenthave(User user , ArrayList<Card>specialCard,int adadbarayekartkharidarishode){
        ArrayList<Card>cards=new ArrayList<Card>();
        cards=showcardthatuserdosenthave(user,specialCard);
        for(int i=0 ; i<cards.size();i++){
            System.out.println(i+")  "+"card name :"+cards.get(i).getName()+"special card:"+cards.get(i).getSpecialCard()+"card attack :"+cards.get(i).getCardAttack()+"card price :"+cards.get(i).getPrice());
        }
        return cards.get(adadbarayekartkharidarishode);
    }




    public void ADDnewCard(User user , Card card){
        user.usercard.add(card);
    }

    public void upgradecard(String name , User user, String upgrade){
        int j=0;
        for(int i=0 ; i<user.usercard.size();i++){
            if(user.usercard.get(i).getName().equals(name)){
                j=i;
                break;
            }
        }
        if(upgrade.equals("playerdamage")){
            int oldlevel=user.usercard.get(j).getLevel();
            user.usercard.get(j).setLevel(oldlevel+1);
        }
    }
    public void buynewcard(User user ,  Card newcard){
        long pricecard=newcard.getPrice();
        long userscoins=user.getCoins();

        if(pricecard>userscoins){
            System.out.println("can not buy");
        }
        else {
            user.setCoins(userscoins - pricecard);
            user.usercard.add(newcard);

        }
    }
}
