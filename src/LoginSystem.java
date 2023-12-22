import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String position;

    public User(String username, String password, String position) {
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }
}

class UserDatabase {
    private Map<String, User> users;
    private String databaseFileName;

    public UserDatabase(String databaseFileName) {
        this.users = new HashMap<>();
        this.databaseFileName = databaseFileName;
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(databaseFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    User user = new User(parts[0], parts[1], parts[2]);
                    users.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseFileName))) {
            for (User user : users.values()) {
                writer.write(user.getUsername() + "," + user.getPassword()+ "," + user.getPosition());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public User validatePosition(String username, String password) {
        User user = users.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }
}

public class LoginSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDatabase userDatabase = new UserDatabase("userDatabase.txt");

    public static void main(String[] args) {
        // Example: Register a new user
        loginUser();
        //registerUser();

        // Example: Login
        //loginUser();
    }

    private static void registerUser() {
        System.out.println("=== User Registration ===");
        String position ="";
        do {
            System.out.println("=== User Registration ===");
            System.out.print("Enter a position: Supplier(S) / Customer(C) : ");
            String pos = scanner.nextLine();

            switch (pos.toLowerCase()) {
                case "s":
                    position = "Supplier";
                    break;
                case "c":
                    position = "Customer";
                    break;
                default:
                    System.out.println("Invalid input. Please enter 'S' for Supplier or 'C' for Customer.");
                    position = "redo";
            }

        } while (position.equals("redo"));

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        // Check if the username already exists
        if (userDatabase.validateUser(username, "")) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Create a new user and add it to the database
        User newUser = new User(username, password,position);
        userDatabase.addUser(newUser);

        System.out.println("Registration successful!");
    }

    private static void loginUser() {
        System.out.println("\n=== User Login ===");
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (userDatabase.validateUser(enteredUsername, enteredPassword)) {
                System.out.println("Login successful!");
                loginSuccessful = true;

                User authenticatedUser = userDatabase.validatePosition(enteredUsername, enteredPassword);
                String pos = authenticatedUser.getPosition();

                switch (pos) {
                    case "Admin":
                        System.out.print("in Admin");
                        Admin admin = new Admin();
                        break;
                    case "Customer":
                        System.out.print("in Customer");
                        break;
                    case "Supplier":
                        System.out.print("in Supplier");
                        break;
                    // additional cases as needed
                    default:
                    System.out.print("Error");

                }


            } else {
                System.out.println("Invalid username or password. Please try again.");
            }

        } while (!loginSuccessful);
    }
}