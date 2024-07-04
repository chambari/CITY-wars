import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;







public class Main {





    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        Server.readDataFromServer();




        String fileName = "usersfile.txt";


        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        String username = parts[0].split(":")[1];
                        String password = parts[1];
                        String neakname = parts[2];
                        String email = parts[3];
                        String passwordRecoveryQuestion = parts[4];
                        User user = new User(username, password, neakname, email, passwordRecoveryQuestion);
                        users.add(user);
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





        ProgramController programController=new ProgramController();
        programController.Run();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < users.size(); i++) {
                writer.write(users.get(i).toFileFormat(i + 1));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }






    }


}