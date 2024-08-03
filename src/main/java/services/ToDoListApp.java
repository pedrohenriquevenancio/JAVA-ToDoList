package main.java.services;

import main.java.models.User;
import main.java.utils.Authentication;

import java.util.Optional;
import java.util.Scanner;

public class ToDoListApp {

    public static void run() {
        System.out.println("Starting app...");
        Scanner in = new Scanner(System.in);
        System.out.println("Login page");

        User user = Authentication.run();
    }

}
