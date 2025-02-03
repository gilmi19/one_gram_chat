package main.org.example;

import main.org.example.entity.User;
import main.org.example.exception.SessionException;
import main.org.example.exception.UserNotFoundException;
import main.org.example.repository.UserRepository;
import main.org.example.service.MessageService;
import main.org.example.service.UserService;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class OneGramChat {
    private static final UserService userService = new UserService(new UserRepository());
    private static final MessageService messageService = new MessageService(userService);

    private static User currentUser = null;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("""
                ==================
                ONE GRAM CHAT V1.0
                ==================
                -
                -
                -
                -
                """);
        Thread.sleep(1000);
        System.out.print("Enter command:");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (scanner.nextLine()) {
                case "-signup" -> signUp(scanner);
                case "-signin" -> signIn(scanner);
                case "-signout" -> signOut();
                case "-readmessages" -> readMessages(currentUser);
                case "-textmessages" -> textMessage(scanner);
                case "-exit" -> exit();
                case "-help" -> getHelp();
                case "-findall" -> findAll();
                default -> System.out.println("Unknown command");
            }
             }
    }

    private static void findAll() {
       // System.out.println(Arrays.toString(userService.findAll()));
        userService.printAll();
    }

    public static void getHelp() {
        System.out.println("""
                Commands for OneGramChat:
                -signup - registration
                -signin - authorisation
                -signout - exit from current account
                -textmessages - text message
                -readmessages - read all messages
                -exit - exit from program
                -help - get list commands
                """);
    }

    public static void textMessage(Scanner scanner) {
        System.out.println("Enter content text message:\n");
        String content = scanner.nextLine();
        System.out.println("Enter user to name");
        String userToName = scanner.nextLine();
        try {
            messageService.sendMessage(userToName, content, currentUser);
            System.out.println("message sent");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readMessages(User currentUser) {
        try {
            messageService.readMessage(currentUser);
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void signOut() {
        currentUser = null;
        System.out.println("Successful exit!");
    }

    public static void setCurrentUser(User currentUser) {
        OneGramChat.currentUser = currentUser;
    }

    public static void exit() {
        System.out.println("Exit from OneGramChat.");
        System.exit(0);
    }

    /**
     * зарегистрироваться
     */
    private static void signUp(Scanner scanner) {
        System.out.println("Create new User processing\n");
        User user = inputData(scanner);
        userService.add(user);
        System.out.println("You has been successfully registered!");
    }

    /**
     * войти или авторизоваться
     */
    public static void signIn(Scanner scanner) {
        System.out.println("Authorisation\n");
        User newUser = inputData(scanner);

        try {
            User user = userService.findByNameAndPassword(newUser.getName(), newUser.getPassword());
            setCurrentUser(user);
            System.out.println("You has been authorized");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static User inputData(Scanner scanner) {
        System.out.println("Enter your username: ");
        String userName = createUserName(scanner);
        System.out.println("Enter your password: ");
        String password = createPassword(scanner);

        return new User(userName, password);
    }

    private static String createUserName(Scanner scanner) {
        return scanner.nextLine();
    }

    private static String createPassword(Scanner scanner) {
        return scanner.nextLine();
    }
}
