
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
            Server.readDataFromServer();

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
        programController.Run();
    }


}