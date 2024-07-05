//import com.google.gson.Gson;

import java.io.*;

public class Server {
    static final String fileName = "usersfile.txt";
    public static File file = new File(fileName);
    static final String fn = "Cardfile.txt";
    public static File fileCard = new File(fn);

//    private static Gson gson = new Gson();

    public Server() {
//        owners = new ArrayList<>(Owner.getOwners());
//        restaurants = new ArrayList<>(Restaurant.getRestaurants());
//        costumers = new ArrayList<>(Costumer.getCostumers());
//        foods = new ArrayList<>(Food.getFoods());
//        deliveries = new ArrayList<>(Delivery.getDeliveries());
    }

    public static void writeDataToServer() {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("");  // file ro khali mikonim.
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (User user : User.users) {
                writer.write(user.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void readDataFromServer() {

        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String username = parts[0];
                        String password = parts[1];
                        String nickname = parts[2];
                        String email = parts[3];
                        String passWordRecoveryAnswer=parts[4];
                        String passwordRecoveryQuestionNumber = parts[5];
                        User user = new User(username, password,nickname,email);
                        user.setPasswordRecoveryQuestionNumber(Integer.parseInt(passwordRecoveryQuestionNumber));
                        user.setPassWordRecoveryAnswer(passWordRecoveryAnswer);
                    }
                    User.loggedInUser=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void readCardsFromServer()  {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileCard));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Parts = line.split(",");
                if (Parts.length >=6) {
                    String Specialcard = Parts[0];
                    String namecard = Parts[1];
                    String level = Parts[2];
                    String cardattack = Parts[3];
                    String doration = Parts[4];
                    String playerdamage = Parts[5];
                    String upgradecost = Parts[6];

                    Card card=new Card(Specialcard,namecard,Integer.parseInt(level),Integer.parseInt(doration),Integer.parseInt(playerdamage),Integer.parseInt(upgradecost),Integer.parseInt(cardattack));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void writeCardsToServer(){
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(fileCard))) {
            writer1.write("");  // file ro khali mikonim.
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(fileCard, true))) {
            for (Card card : Card.cards) {
                writer2.write(card.toFileFormatcard());
                writer2.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
