package com.github.duukpn.matricks;

import java.util.Scanner;

public class MaTricks {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String... args) {

        System.out.println("Welcome to MaTricks! Please enter a command.");

        String[] command = input.nextLine().split("\\s+");

        try {
            switch (command[0]) {
                case "q":
                case "quit":
                    return;
                default:
                    System.err.println("Syntax error");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Syntax error");
        }
    }

}
