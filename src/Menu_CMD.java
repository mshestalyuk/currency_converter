package src;
import java.util.Scanner;


public class Menu_CMD implements InputReader {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


}
