import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    /*
         TODO Write a program that ask for a username and password and if they are correct authorize the login else answer "ACCESS DENIED".
          For now just create two user a "user" and an "admin"
        */

        var scanner = new Scanner(System.in);

        var users = new HashMap<String, String>(); // Create an array with key value pair
        users.put("member", "1234"); // userName, password
        users.put("admin", "12345");

        // * Ask for the userName and save it on the userName variable
        System.out.println("Hi there, please write your username:");
        String userName = scanner.nextLine();

        // * If the HashMap users doesn't contain the username variable, stop the program and show an error message
        if (!users.containsKey(userName)){
            System.out.println("User no valid. Try again");
        }

        // * If the Hashmap users contains the username variable, proceed.
        if (users.containsKey(userName)) {
            // * Ask for a password...
            System.out.println("Now please write your password:");
            String password = scanner.nextLine();

            // ! Check if the password match with the userName declared before and on the HashMap
            if (users.get(userName).equals(password)){
                System.out.println("Hi there, " + userName + ". Welcome aboard!");
            } else {
                System.out.println("ACCESS DENIED");
            }
        }

        scanner.close();

    }
}