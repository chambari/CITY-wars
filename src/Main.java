
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static InputOutputProcessor inputOutput = InputOutputProcessor.getInstance();
    public static void main(String[] args) throws FileNotFoundException {
       Server.readDataFromServer();
       Server.readCardsFromServer();

//        File file = new File("graph.txt");
//        Scanner raeder = new Scanner(file);
//        String[] firstline = raeder.nextLine().split(" ");
//        int node = Integer.parseInt(firstline[0]);
//        Graph graph = new Graph(node);
//        int mline = Integer.parseInt(firstline[1]);
//        for (int i = 0; i < mline; i++) {
//            String[] lines = raeder.nextLine().split(" ");
//            int source = Integer.parseInt(lines[0]);
//            int dest = Integer.parseInt(lines[1]);
//            int weight = Integer.parseInt(lines[2]);
//            graph.addEdge(source - 1, dest - 1, weight);
//       }
//            launch(args);
        ProgramController programController=new ProgramController();
        inputOutput.printer(CheckResult.ALL_COMMANDS);
        programController.Run();
        Server.writeDataToServer();
        Server.writeCardsToServer();
    }

}











/*
user create -u Arminkh -p 12345678fG 12345678fG -email armin@gmail.com -n armin
question pick -q 1 -a baba -c baba
log out
user login -u Arminkh -p 12345678fG
Profile change -u Armin
user login -u Armin -p 12345678fG
Profile change -n armin
profile change password -o 12345678fG -n 12345678fG
Show information
log out
Forgot my password -u Armin
login admin admin
name -n dragon card defence/attack -a 50 duration -d 4 player damage -p 20 update level -l 3 update cost -c 12 price -p 100 Character -t shooter
Admin,dragon,3,4,20,12,50
 */