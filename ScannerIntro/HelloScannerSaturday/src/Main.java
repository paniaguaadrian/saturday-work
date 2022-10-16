import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // !  Read input from console
//        demoInput();

        // ! Read from a file
        readFromFile();


        // ! Write to a file
//        writeToFile();
    }

    private static void readFromFile() throws FileNotFoundException {
        File targetFile = new File("src/my-list.txt");
        Scanner reader = new Scanner(targetFile);
        var line = reader.nextLine();
        System.out.println(line);
        reader.close();
    }

    private static void writeToFile() throws IOException {
        var scanner = new Scanner(System.in);
        String command;

        do{
            System.out.println("What do you want to do?");
            command = scanner.nextLine();
            var writer = new FileWriter("src/my-list.txt", true);
            writer.write(command + "\n");
            writer.close();
        } while(!command.equals("exit"));

        scanner.close();
    }

    private static void demoInput() {
        var scanner = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        System.out.println("What's your code?");
        var userCode = scanner.nextInt();

        if(userCode == 13) {
            System.out.println("You can enter");
        } else System.out.println("Access denied");

        scanner.close();
    }
}