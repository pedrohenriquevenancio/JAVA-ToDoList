package main.java.services;

import main.java.models.Task;
import main.java.models.User;
import main.java.services.database.DatabaseTasks;
import main.java.services.database.DatabaseUsers;
import main.java.utils.Authentication;

import java.util.*;
import java.util.stream.Collectors;

public class ToDoListApp {
    public static void run() {
        System.out.println("Starting app...");
        Scanner in = new Scanner(System.in);
        System.out.println("Login page");

        boolean onSelect = true;
        DatabaseUsers dbUsers = new DatabaseUsers();
        DatabaseTasks dbTasks = new DatabaseTasks();

        do {
            User user = Authentication.run(dbUsers);
            onSelect = menu(dbTasks, user);
        } while(onSelect);

        in.close();
        System.out.println("Finishing application...");
    }

    private static boolean menu(DatabaseTasks db, User user) {
        System.out.println("Welcome "+user.getName()+"!");

        boolean onSelect = true;
        int opt;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("                    ----------MENU----------                ");
            System.out.println("1 - Show tasks      |      2 - New task    |     3 - Edit task");
            System.out.println("4 - User settings   |      5 - Logout      |     6 - Close app");
            opt = in.nextInt();
            switch (opt) {
                case 1:
                    showTasks(db, user);
                    break;
                case 2:
                    newTask(db, user);
                    break;
                case 3:
                    editTask(db);
                    break;
                case 4:
                    userSettings(user);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Option not exists... Try again!");
                    break;
            }
            onSelect = opt != 5 && endMenu();
        } while(onSelect);
        if (opt != 5) {
            System.out.println("Going to Login page...");
        }
        in.close();
        return opt != 6;
    }

    private static boolean endMenu() {
        System.out.println("Go to Login Page?\n1 -> Yes   |   Press any key -> No");
        Scanner in = new Scanner(System.in);
        int opt = in.nextInt();
        in.close();
        return opt != 1;
    }

    private static void showTasks(DatabaseTasks db, User user) {
        db.getAll().forEach(t -> System.out.println(t.toString()));
        List<Task> tasks = db.getAll().stream().filter(t -> Objects.equals(user.getId(), t.getUserId())).toList();
        if (!tasks.isEmpty()) {
            System.out.println("\n");
            tasks.forEach(t -> System.out.println(t.toString()));
            System.out.println("\n");

            Scanner in = new Scanner(System.in);
            int loop = 1;
            do {
                System.out.println("Would you like to check a task as 'Done'?\n1 -> Yes   |   Press any key -> No");
                int opt = in.nextInt();
                if (opt == 1) {
                    System.out.println("Enter the task ID:");
                    int id = in.nextInt();
                    db.checkTaskAsDone(id);
                }
                System.out.println("1 -> Choose another task   |   Press any key -> Back to menu");
                loop = in.nextInt();
            } while(loop);
            in.close();
        } else {
            System.out.println("No tasks available...");
        }
    }

    private static void newTask(DatabaseTasks db, User user) {
        System.out.println("Creating a new task...");

        Scanner in = new Scanner(System.in);

        System.out.println("Name:");
        String name = in.nextLine();

        System.out.println("Description:");
        String description = in.nextLine();

        int id = db.getAll().size() + 1;
        db.store(new Task(id, user.getId(), name, description));
        in.close();
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

        db.update(id, new Task(id, task.getUserId(), task.getName(), task.getDescription()));
        in.close();
    }

    private static void userSettings(User user) {

    }
}
