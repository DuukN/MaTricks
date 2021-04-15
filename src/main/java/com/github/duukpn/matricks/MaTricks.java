package com.github.duukpn.matricks;

import java.util.Scanner;

public class MaTricks {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String... args) {
        System.out.println("----------------------=*=----------------------");
        System.out.println("Welcome to MaTricks! Please enter a command.\n");
        while (true) {
            Scanner command = new Scanner(input.nextLine());
            if (!command.hasNext()) continue;

            switch (command.next()) {
                case "q":
                case "quit":
                    return;
                default:
                    System.err.println("Syntax error: unknown command");
            }
        }
    }

}
