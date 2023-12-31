import java.io.*;
import java.util.*;

public class Customer extends User  {
    private Vector<Book> bookList;
    //private static Vector<OrderManagement> orders;

    public Customer(){}

    public Customer(String id, String name ,String pw, String mail, int roleID, String fName, String lName){
        super(id, name, pw, mail, roleID,fName,lName);
    }

    public static void registration() throws IOException{
        Vector<Customer> cust = new Vector<Customer>();
        cust = getCustomersfromFile();
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\nEnter your username (No spaces in-between): ");
        String username = sc.nextLine();

        for(Customer c:cust){
            while (c.getUserName().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                System.out.print("\nEnter a new username (No spaces in-between): ");
                username = sc.nextLine();
            }
        }

        username = username.replaceAll("\\s",""); //replacing spaces with no-space.

        System.out.print("Enter your first name: ");
        String nameFirst = sc.nextLine();

        System.out.print("Enter your last name: ");
        String nameLast = sc.nextLine();  

        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        System.out.print("Enter a password: ");
        String pw = sc.nextLine();

        System.out.print("Re-enter your password: ");
        String confirmPassword = sc.nextLine();

        //Input_Validation
        while (pw.equals(confirmPassword) == false) {
            System.out.println("Passwords do not match. Please re-enter your password.");
            System.out.print("Enter a password: ");
            pw = sc.nextLine();

            System.out.print("Re-enter your password: ");
            confirmPassword = sc.nextLine();
        }

        String userID = username+"Cust"; //creating a fixed userID with username that they created first.
        Customer c = new Customer(userID, username, pw, email, 2,nameFirst,nameLast);
        addCustomersIntoFile(c);
        sc.close();

    }
   
    @Override
    protected int viewMenu(){ //Polymorphism
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║          Customer Menu        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. View Books Catalog         ║");
            System.out.println("║ 2. Order Books                ║");
            System.out.println("║ 3. Manage Account             ║");
            System.out.println("║ 4. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");

            System.out.print("\n\n Enter the option (1-4) : ");
            option = sc.nextInt();

            if(option < 1 || option > 5){
                System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
            }

        }while(option < 1 || option > 5);
        return option;
    }

    //Trying something new to work with Aggregation
    public static Vector<Customer> getCustomersfromFile() throws FileNotFoundException {
        Vector<Customer> customers = new Vector<Customer>();
        Vector<User> users = User.readFromUserFile(2);

        for (User user : users) {
            Customer customer = new Customer(user.getUserID(), user.getUserName(), user.getPassword(), user.getEmail(), user.getUserRole(),user.getName().getfName(),user.getName().getlName());
            customers.add(customer);
        }
        return customers;
    }

    public static void addCustomersIntoFile(Customer c) throws IOException{
        PrintWriter outputFile = new PrintWriter(new FileWriter("userDatabase.txt",true));
        String fullName =  c.getName().getfName()+"_"+c.getName().getlName();
        outputFile.write(c.getUserID()+ " "+c.getUserName()+ " "+c.getPassword()+ " "+c.getEmail()+ " "+fullName+" "+2+"\n");
        outputFile.close();
        // customers.add(c);

        System.out.println("Your username : "+c.getUserName()+"\nYour Password: "+c.getPassword());
    }

    public void viewAllCustomers(Vector<Customer> customers) throws FileNotFoundException {
       System.out.println("+-----------------+---------------------+---------------------+---------------------+");
       System.out.println("|  Customer ID    |    Username         |     Full Name       |        Email        |");
       System.out.println("+-----------------+---------------------+---------------------+---------------------+");
        
        for (Customer u : customers) {
            System.out.printf("| %-15s | %-19s | %-19s | %-19s |%n", u.getUserID(), u.getUserName(), u.getName().getfName() + " " + u.getName().getlName(), u.getEmail());
        }
        
       System.out.println("+-----------------+---------------------+---------------------+---------------------+");
        

        System.out.print("Press Enter to continue...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }

        public static void displayCustomer(User u){
            System.out.println("+-----------------+---------------------+---------------------+---------------------+");
            System.out.println("|  Customer ID    |    Username         |     Full Name       |        Email        |");
            System.out.println("+-----------------+---------------------+---------------------+---------------------+");
        
            System.out.printf("| %-15s | %-19s | %-19s | %-19s |%n", u.getUserID(), u.getUserName(), u.getName().getfName() + " " + u.getName().getlName(), u.getEmail());
        
            System.out.println("+-----------------+---------------------+---------------------+---------------------+");
        
    }

    public static void updateCustomerAcc(User currUser) throws IOException{
        Scanner scan = new Scanner(System.in);
        int option;
        String newValue = "";
        do {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            displayCustomer(currUser);
            System.out.println("\n\n");
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║          Manage Account       ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Update Username            ║");
            System.out.println("║ 2. Update Password            ║");
            System.out.println("║ 3. Update Email               ║");
            System.out.println("║ 4. Update Name                ║");
            System.out.println("║ 5. Back                       ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("\nEnter your option (1-5): ");
            option = scan.nextInt();

            if(option < 1 || option >5){
                System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
            }
        } while (option < 1 || option >5);
        scan.nextLine();

        switch (option) {
            case 1:
                System.out.print("\nEnter new username : ");
                newValue = scan.nextLine();
                currUser.setUserName(newValue);
                break;
            case 2:
                System.out.print("\nEnter new password: ");
                newValue= scan.nextLine();

                System.out.print("\nRe-enter your password: ");
                String confirmPassword = scan.nextLine();


                while (newValue.equals(confirmPassword) == false) {
                    System.out.println("Passwords do not match. Please re-enter your password.");
                    System.out.print("\nEnter a password: ");
                    newValue = scan.nextLine();

                    System.out.print("\nRe-enter your password: ");
                    confirmPassword = scan.nextLine();
                }
                currUser.setPassword(newValue);
                break;
            case 3:
                System.out.print("\nEnter new email : ");
                newValue = scan.nextLine();
                currUser.setEmail(newValue);
                break;
            case 4:
                System.out.print("\nEnter new first name : ");
                newValue = scan.nextLine();
                currUser.getName().setfName(newValue);
                System.out.print("\nEnter new last name : ");
                newValue= scan.nextLine();
                currUser.getName().setlName(newValue);
                break;
            
            case 5:
                return;
        
            default:
                break;
        }
        Vector<User> us = new Vector<User>();
        us = User.readAllUsers();
        FileWriter file = new FileWriter("userDatabase.txt",false);
        for (User c : us) {
                String fullName = c.getName().getfName()+"_"+c.getName().getlName();
                if(c.getUserID().equals(currUser.getUserID())){
                    String username = currUser.getUserName().replaceAll(" ", "");
                    String em = currUser.getEmail().replaceAll(" ", "");
                    fullName = currUser.getName().getfName()+"_"+currUser.getName().getlName();
                    String line = String.format("%s %s %s %s %s %d%n", currUser.getUserID(), username, currUser.getPassword(), em,fullName, 2);
                    file.write(line);
                }else{
                    String line = String.format("%s %s %s %s %d%n", c.getUserID(), c.getUserName(), c.getPassword(), c.getEmail(),fullName, c.getUserRole());
                    file.write(line);
                }

            }
            file.close();
        System.out.println("Updated Successfully :)");
    }

//     public void orderBook(){
//     Book book = new Book();
//     bookList = book.getBooksfromFile();
//     getUserID()
//     book.setQuantityInStock(book.getQuantityInStock()-orderq);

    
// }
}

