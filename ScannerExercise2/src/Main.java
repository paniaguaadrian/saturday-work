import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        LoginChecker();
    }

    /**
     * LoginChecker is a program that ask for credentials to the user (username and password) and checks if
     * that data match with the one stored on the db file. If so, we will show the logs to the user. If there are
     * any mistakes on the credentials, we will show to the user the count of tries with a maximum of 3.
     * */
    private static void LoginChecker() throws IOException {
        var scanner = new Scanner(System.in);

        // external paths: DB and Logs from console
        File importedDataBase = new File("src/my-db.csv");
        File importedLogsData = new File("src/my-list.txt");

        // writer => method to add/write data to our file.
        FileWriter writer = new FileWriter(importedLogsData, true);

        // read methods for logs and users db
        Scanner readLogs = new Scanner(importedLogsData);
        Scanner readUsers = new Scanner(importedDataBase);

        var registeredUsersOnDataBase = new HashMap<String, String>(); // create a HashMap (key value pairs) to add the users from our DB and use it on our program from now
        int userPasswordTries = 0;
        int maxUserPasswordTries = 3;
        String userName;

        do{ // will loop and: 1. read every line on the external document (my-db.csv), 2. save on HashMap the userName and psw
            // until hasNextLine() is false due to don't find anything on a next line.
            String foundUser = readUsers.nextLine();
            var foundUserName = foundUser.split(",")[0];
            var foundUserPsw = foundUser.split(", ")[1];
            registeredUsersOnDataBase.put(foundUserName, foundUserPsw);
        } while(readUsers.hasNextLine());


        // Start the program and save the userName variable
        userName = checkUserName(scanner, writer);

        do{ // run the program and process while the userPasswordTries < maxUserPasswordTries

            // if in the db there's NOT a user matching the wrote user...
            if (!registeredUsersOnDataBase.containsKey(userName)) {
                userPasswordTries++;

                writeLogStatus(writer, "Date: " + Instant.now().toString() + "===" + "\n", "Incorrect userName provided by ", userName);
                errorHandlerTriesCountdown("Bad username. ", "Tries available: " + (maxUserPasswordTries - userPasswordTries));

                System.out.println("Write your username again:");
                userName = scanner.nextLine();

                if(userPasswordTries == 2) return;

            }

            // if in the db there is a user matching the wrote user...
            if (registeredUsersOnDataBase.containsKey(userName)) {
                System.out.println("Now please write your password:");
                String password = scanner.nextLine();

                // if the password machs with the one saved on DB...
                if (registeredUsersOnDataBase.get(userName).equals(password)){
                    System.out.println("Hi there, " + userName + ". Welcome aboard!");
                    writeLogStatus(writer, "Date: " + Instant.now().toString() + "===" + "\n", "Successful login by ", userName);
                    fetchAndShowLogsOnValidLogin(readLogs);
                    userPasswordTries = 3; // Stop the do while loop on success
                } else {
                    userPasswordTries++;
                    writeLogStatus(writer, "Date: " + Instant.now().toString() + "===" + "\n", "Bad login by ", userName);
                    errorHandlerTriesCountdown("Bad password. ", "Tries available: " + (maxUserPasswordTries - userPasswordTries));
                }
            }
        } while(userPasswordTries < maxUserPasswordTries);

        writer.write("===> End of program" + "\n");
        writer.close();
        scanner.close();
        readUsers.close();
    }

    /**
     * fetchAndShowLogsOnValidLogin is a method to read the logs file and return them to the user when there is a
     * valid login
     * */
    private static void fetchAndShowLogsOnValidLogin(Scanner readLogs) {
        do{
            String logs = readLogs.nextLine();
            System.out.println(logs);
        } while(readLogs.hasNextLine());
    }

    /**
     * errorHandlersTriesCountdown is a method to return an error to the user when there is an invalid login
     * */
    private static void errorHandlerTriesCountdown(String errorMessage, String availableTries) {
        System.out.println(errorMessage + availableTries);
    }

    /**
     * writeLogStatus is a method to get the inputs from the user and save them on the logs file in order to have a count
     * of the logins in our system
     * */
    private static void writeLogStatus(FileWriter writer, String date, String logMsg, String userName) throws IOException {
        writer.write(date);
        writer.write( logMsg+ userName + ".\n");
    }

    /**
     * checkUserName is a method which receives on params scanner and writer in order to start the program.
     * Save and returns the variable userName from the prompt (when the user writes his name)
     * Use that method saved on a variable.
     * */
    private static String checkUserName(Scanner scanner, FileWriter writer) throws IOException {
        String userName;
        writer.write("===> Start of program" + "\n");
        System.out.println("Hi there, please write your username:");
        userName = scanner.nextLine(); // save on userName the name wrote by the user
        return userName;
    }
}