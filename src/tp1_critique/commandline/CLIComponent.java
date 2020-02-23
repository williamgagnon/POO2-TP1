package tp1_critique.commandline;

import java.util.Scanner;

public class CLIComponent implements CLI {
    public final static Scanner scanner = new Scanner(System.in);

    public CLIComponent() {
    }

    @Override
    public String printAndGetCommand(String instructions) {
        String result;

        System.out.println(instructions);
        result = scanner.nextLine();

        return result;
    }
}
