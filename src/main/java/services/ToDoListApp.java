package main.java.services;

import main.java.models.Task;
import main.java.models.User;
import main.java.services.database.DatabaseTasks;
import main.java.utils.Authentication;

import java.util.Objects;
import java.util.Scanner;

public class ToDoListApp {

    public static void run() {
        System.out.println("Starting app...");
        Scanner in = new Scanner(System.in);
        System.out.println("Login page");

        User user = Authentication.run();
        menu(user);
    }

    private static void menu(User user) {
        System.out.println("Welcome "+user.getName()+"!");

        DatabaseTasks db = new DatabaseTasks();
        boolean onSelect = true;
        int opt;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("-----------MENU-----------");
            System.out.println("1 - Show tasks   |   2 - New task");
            System.out.println("3 - Edit task    |   4 - User settings");
            opt = in.nextInt();
            switch (opt) {
                case 1:
                    showTasks(db);
                    break;
                case 2:
                    newTask(db);
                    break;
                case 3:
                    editTask(db);
                    break;
                case 4:
                    userSettings(user);
                    break;
                default:
                    System.out.println("Option not exists... Try again!");
                    break;
            }
            onSelect = endMenu();
        } while(onSelect);

        System.out.println("Finishing application...");
    }

    private static boolean endMenu() {
        System.out.println("Finish application?");
        System.out.println("1 - Yes   |   2 - No");
        Scanner in = new Scanner(System.in);
        int opt = in.nextInt();
        return opt != 1;
    }
    private static void showTasks(DatabaseTasks db) {
        if (db.getAll().size() > 0) {
            System.out.println("\n");
            db.getAll().forEach(t -> System.out.println(t.toString()));
            System.out.println("\n");
        } else {
            System.out.println("No tasks available...");
        }
    }

    private static void newTask(DatabaseTasks db) {
        System.out.println("Creating a new task...");

        Scanner in = new Scanner(System.in);

        System.out.println("Name:");
        String name = in.nextLine();

        System.out.println("Description:");
        String description = in.nextLine();

        int id = db.getAll().size() + 1;
        db.store(new Task(id, name, description));
    }

    private static void editTask(DatabaseTasks db) {
        System.out.println("Editing a task...");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the ID:");
        int id = in.nextInt();

        Task task = db.getById(id);

        do {
            if (Objects.isNull(task)) {
                System.out.println("No tasks found... Try other ID!\nEnter the ID:");
                id = in.nextInt();
                task = db.getById(id);
            }
        } while (Objects.isNull(task));

        System.out.println("Current data:"+task.toString());

        System.out.println("Name:");
        String name = in.nextLine();
        task.setDescription(name);

        System.out.println("Description:");
        String description = in.nextLine();
        task.setDescription(description);

        db.update(id, task);
    }

    private static void userSettings(User user) {

    }
}
