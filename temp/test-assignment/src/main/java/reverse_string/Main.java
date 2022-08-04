package reverse_string;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the string: ");
        String line = scanner.nextLine();
        String result = Converse.lineReversalAndTiming(line);
        System.out.println(result);
    }
}
