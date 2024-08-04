package main.java.utils;

import main.java.models.User;
import main.java.services.database.DatabaseUsers;

import java.util.Objects;
import java.util.Scanner;

public class Authentication {
    public static User run(DatabaseUsers db) {
        System.out.println("Welcome to the TO DO LIST APP");
        printQuestion();

        Scanner in = new Scanner(System.in);
        int opt = in.nextInt();

        boolean onSelecting = true;
        User user = null;

        do {
            if (Objects.equals(opt, 1)) {
                user = login(db);
                if (user != null) {
                    onSelecting = false;
                } else {
                    System.out.println("Incorrect credentials... Try again!\n");
                    opt = 3;
                }
            } else if (Objects.equals(opt, 2)) {
                user = register(db);
                if (user != null) {
                    onSelecting = false;
                } else {
                    System.out.println("Incorrect credentials... Try again!\n");
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

    public static User login(DatabaseUsers db) {
        Scanner in = new Scanner(System.in);
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();
        System.out.println("---------------");
        return db.getAuth(username, password);
    }

    public static User register(DatabaseUsers db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Create a new User...");
        System.out.println("---------------");
        System.out.print("Name:");
        String name = in.nextLine();
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();
        System.out.println("---------------");
        int id = db.getAll().size() + 1;
        db.store(new User(id, username, password, name));
        return db.getAuth(username, password);
    }
}
