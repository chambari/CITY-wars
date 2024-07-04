//import com.google.gson.Gson;

import java.io.*;

public class Server {
    static final String fileName = "usersfile.txt";
    public static File file = new File(fileName);

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
            writer.write("");  // فایل را خالی می‌کنیم
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

}
