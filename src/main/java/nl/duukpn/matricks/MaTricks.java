package nl.duukpn.matricks;

import nl.duukpn.matricks.entities.Fraction;
import nl.duukpn.matricks.entities.Matrix;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaTricks {

    private static final Scanner input = new Scanner(System.in);
    public static final Map<String, Fraction> fractions = new HashMap<>();
    public static final Map<String, Matrix> matrices = new HashMap<>();

    public static void main(String... args) {
        System.out.println("----------------------=*=----------------------");
        System.out.println("Welcome to MaTricks! Please enter a command.\n");
        while (true) {
            String[] command = input.nextLine().split("\\s+");
            if (command.length == 0) continue;

            switch (command[0]) {
                case "d":
                case "def":
                case "define":
                    define(command);
                    break;
                case "p":
                case "print":
                    //TODO implement
                case "q":
                case "quit":
                    return;
                default:
                    System.err.println("Syntax error: unknown command");
            }
        }
    }

    private static void define(String[] command) {
        try {
            switch (command[1]) {
                case "fraction":
                case "constant":
                case "value":
                    Fraction value = Fraction.parseFraction(command[3]);
                    if (value == null) return;

                    fractions.put(command[2], value);
                    break;
                case "matrix":
                    try {
                        Matrix matrix = Matrix.read(Integer.parseInt(command[3]), Integer.parseInt(command[4]), input);
                        matrices.put(command[2], matrix);

                        System.out.println("Successfully saved " + command[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Size should be an integer");
                    } catch (Matrix.MatrixFormatException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.err.println("Not a valid variable type");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing argument for command");
        }
    }

}
