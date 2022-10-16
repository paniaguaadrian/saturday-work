import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String stringInput = "Welcome to Barcelona 2022.";
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,10};

        // TODO Write a Java program to count the letters, spaces, numbers and other characters of an input string
        countAllCharactersInString(stringInput);

        // TODO Write a Java program to print the odd numbers from 1 to 20
        printOddNumbers(numbers);

        // TODO Write a Java program to count the number of even and odd elements in a given array
        numOfEvenAndOdd(numbers);
    }

    public static void countAllCharactersInString(String stringInput){
        int lettersCount = 0;
        int numsCount = 0;
        int specialCount = 0;
        int spacesCount = 0;

        for (int i = 0; i < stringInput.length(); i++) {
            char stringChars = stringInput.charAt(i);
            if (stringChars >= 'A' && stringChars <= 'Z' || stringChars >= 'a' && stringChars < 'z'){
                lettersCount++;
            } else if (stringChars >= '0' && stringChars <= '9'){
                numsCount ++;
            } else if (stringChars == ' '){
                spacesCount++;
            } else {
                specialCount++;
            }
        }

        System.out.println("Number of letters: " + lettersCount);
        System.out.println("Number of numbers: " + numsCount);
        System.out.println("Number of Special Characters: " + specialCount);
        System.out.println("Number of spaces: " + spacesCount);

        System.out.println("All characters on string has a value of: " + stringInput.length());
    }

    public static void printOddNumbers(int[] numbers){
        System.out.println("Printed odd numbers: ");
        for (int i = 0; i < numbers.length; i = i + 2) {
            System.out.println(i);
        }
    }

    public static void numOfEvenAndOdd(int[] numbers){
        List<Integer> oddNumbers = new ArrayList<Integer>();
        List<Integer> evenNumbers = new ArrayList<Integer>();


        for (int number : numbers) {
            if (number % 2 == 0) {
                oddNumbers.add(number);
            } else {
                evenNumbers.add(number);
            }
        }
        System.out.println("Odd Numbers: " + oddNumbers);
        System.out.println("Even Numbers: " + evenNumbers);
    }
}