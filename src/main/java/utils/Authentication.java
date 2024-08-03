package main.java.utils;

import main.java.models.User;
import main.java.services.database.DatabaseUsers;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Authentication {
    public static User run() {
        System.out.println("Welcome to the TO DO LIST APP");
        printQuestion();

        Scanner in = new Scanner(System.in);
        int opt = in.nextInt();

        boolean onSelecting = true;
        User user = null;
        DatabaseUsers db = new DatabaseUsers();

        do {
            if (Objects.equals(opt, 1)) {
                user = login(in, db);
                if (user != null) {
                    System.out.println("Done!");
                    onSelecting = false;
                } else {
                    opt = 3;
                }
            } else if (Objects.equals(opt, 2)) {
                user = register(in, db);
                if (user != null) {
                    System.out.println("Done!");
                    onSelecting = false;
                } else {
                    opt = 3;
                }
            } else {
                printQuestion();
                opt = in.nextInt();
            }
        } while (onSelecting);

        return user;
    }

    public static void printQuestion() {
        System.out.println("Select one option:");
        System.out.println("1 - Login");
        System.out.println("2 - Register\n");
    }

    public static User login(Scanner in, DatabaseUsers db) {
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.println("---------------");
        System.out.print("Password:");
        String password = in.nextLine();
        return db.getAuth(username, password);
    }

    public static User register(Scanner in, DatabaseUsers db) {
        System.out.println("Create a new User...");
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.println("\n---------------\n");
        System.out.print("Password:");
        String password = in.nextLine();
        return db.getAuth(username, password);
    }
}
