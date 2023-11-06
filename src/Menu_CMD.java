package src;
import java.util.Scanner;


public class Menu_CMD {
    private Scanner scanner = new Scanner(System.in);

    public String readInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
