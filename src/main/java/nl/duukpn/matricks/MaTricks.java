package nl.duukpn.matricks;

import nl.duukpn.matricks.entities.Fraction;
import nl.duukpn.matricks.entities.Matrix;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MaTricks {

    private static final Scanner input = new Scanner(System.in);
    public static final Map<String, Fraction> fractions = new HashMap<>();
    public static final Map<String, Matrix> matrices = new HashMap<>();

    public static void main(String... args) {
        System.out.println("----------------------=*=----------------------");
        System.out.println("Welcome to MaTricks! Please enter a command.\n");
        while (true) {
            String[] command = input.nextLine().split("\\s+");
            if (command.length == 0 || command[0].equals("")) continue;

            switch (command[0].toLowerCase()) {
                case "d":
                case "def":
                case "define":
                    define(command);
                    break;
                case "p":
                case "print":
                    print(command);
                    break;
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
            switch (command[1].toLowerCase()) {
                case "fraction", "constant", "value", "var", "variable" -> {
                    Fraction value;
                    try {
                        value = Fraction.parseFraction(command[3]);
                    } catch (Fraction.FractionFormatException e) {
                        System.err.println(e.getMessage());
                        return;
                    }

                    fractions.put(command[2], value);
                    System.out.println("Successfully defined " + command[2] + " as " + value.toString());
                }
                case "matrix" -> {
                    String[] size = command[3].split("[xX]");
                    if (size.length != 2) System.err.println("Incorrect size format");
                    try {
                        Matrix matrix = Matrix.read(Integer.parseInt(size[0]), Integer.parseInt(size[1]), input);
                        matrices.put(command[2], matrix);

                        System.out.println("Successfully saved " + command[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Size should be an integer");
                    } catch (Matrix.MatrixFormatException e) {
                        System.err.println(e.getMessage());
                    }
                }
                default -> System.err.println("Not a valid variable type");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing argument for command");
        }
    }

    private static void print(String[] command) {
        try {
            switch (command[1].toLowerCase()) {
                case "fraction", "constant", "value", "var", "variable" -> {
                    String name = command[2];
                    Fraction var = fractions.get(name);
                    if (var != null) {
                        System.out.println(name + " = " + var.toString());
                    } else {
                        System.err.println("Variable " + name + " does not exist");
                    }
                }
                case "matrix" -> {
                    String name = command[2];
                    Matrix var = matrices.get(name);
                    if (var != null) {
                        System.out.println(name + ":\n" + var.toString());
                    } else {
                        System.err.println("Variable " + name + " does not exist");
                    }
                }
                default -> {
                    String name = command[1];
                    if (fractions.containsKey(name) && matrices.containsKey(name)) {
                        boolean repeat = false;
                        do {
                            if (!repeat) {
                                System.out.print("Value " + name + " or matrix " + name + "? ");
                            } else {
                                System.out.print("Please enter 'value' or 'matrix': ");
                            }

                            repeat = false;

                            switch (input.nextLine().toLowerCase().strip()) {
                                case "value" -> printFraction(name);
                                case "matrix" -> printMatrix(name);
                                default -> repeat = true;
                            }
                        } while (repeat);
                    } else if (fractions.containsKey(name)) {
                        printFraction(name);
                    } else if (matrices.containsKey(name)) {
                        printMatrix(name);
                    } else {
                        System.err.println("The provided variable name does not exist");
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing argument for command");
        }
    }

    private static void printFraction(String name) {
        Fraction var = fractions.get(name);
        if (var != null) {
            System.out.println(name + " = " + var.toString());
        } else {
            System.err.println("Variable " + name + " does not exist");
        }
    }

    private static void printMatrix(String name) {
        Matrix var = matrices.get(name);
        if (var != null) {
            System.out.println(name + ":\n" + var.toString());
        } else {
            System.err.println("Variable " + name + " does not exist");
        }
    }

}
