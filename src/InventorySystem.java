import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class InventorySystem {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice, userOption;
        User user = new User();
        do{
            System.out.print("\033[H\033[2J");  
            System.out.flush();

            header();

            System.out.println("\n\nRoles :\n\t1. Admin\n\t2. Customer\n\t3. Supplier");
            System.out.print("Enter your role (1-3) : ");
            choice = scan.nextInt();
            if(choice<1 || choice > 3){
                System.out.println("Invalid option entered. Please Try Again! Press any key to continue...");
                scan.nextLine();
                scan.nextLine();
            }
        }while(choice<1 || choice > 3);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        header();
        if(choice == 1 || choice ==3){
            if(choice ==1){
            do{
                scan.nextLine();
                System.out.print("\n\nEnter username : ");
                String uName = scan.nextLine();
                System.out.print("\nEnter password : ");
                String pw = scan.nextLine();
                user = User.login(uName, pw, choice);
                if(user == null){
                    System.out.println("Invalid Credentials Entered. Please Try Again!");
                }
            }while(user == null);
            Book b = new Book();
            User realUser = new Admin(user.getUserID(), user.getUserName(),user.getPassword(),user.getEmail(), user.getUserRole(), user.getName().getfName(), user.getName().getlName(),b.getBooksfromFile());

            if(realUser.getUserRole() == 1){ //admin
                // BookManager bkm = new BookManager();
                do {
                    scan.nextLine();
                    userOption = realUser.viewMenu();
                    switch (userOption) {
                        case 1:
                            b.viewAllBooks(((Admin)realUser).getBooks(),realUser.getUserRole());
                            break;
                        case 2:
                            //manage orders
                            break;
                        case 3:
                            int val = manageMenu(realUser.getUserRole());
                            if(val == 9){
                                
                            }
                            break;
                        case 4:
                            //generate report
                            break;
                        case 5:
                            //manage customers
                            ((Admin) realUser).viewAllCustomers(new Customer());
                            break;
                        case 6:
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                } while (userOption !=7);
                

                //if else/ case.
            }
            }else{ //supplier
            do{
                scan.nextLine();
                System.out.print("\n\nEnter username : ");
                String uName = scan.nextLine();
                System.out.print("\nEnter password : ");
                String pw = scan.nextLine();
                user = User.login(uName, pw, choice);
                if(user == null){
                    System.out.println("Invalid Credentials Entered. Please Try Again!");
                }
            }while(user == null);
                User realUser = new BookSupplier(user.getUserID(), user.getUserName(),user.getPassword(),user.getEmail(), user.getUserRole(), user.getName().getfName(), user.getName().getlName());
              
                if(realUser.getUserRole()==3){
                int value = realUser.viewMenu();
                switch (value) {
                    case 1:
                        //View All Orders - All status
                        break;
                    case 2:
                        //Approval Order - Show Pending, allow them to enter orderID, then approve or reject
                        break;
                    case 3:
                        BookSupplier.manageAccount(realUser);
                        break;
                
                    default:
                        break;
                }
            }
            }
        }else{
            // Customer c = new Customer();
            do{
                System.out.println("Customer Options:\n\t1. Login\n\t2. Register");
                System.out.print("\nEnter the option (1-2) : ");
                choice = scan.nextInt();
                if(choice<1 || choice > 2){
                    System.out.println("Invalid option entered. Please Try Again!");
                    scan.nextLine();
                    scan.nextLine();
                }
            }while(choice<1 || choice > 2);

            if(choice == 1){
                do{
                    scan.nextLine();
                    System.out.print("\n\nEnter username : ");
                    String uName = scan.nextLine();
                    System.out.print("\nEnter password : ");
                    String pw = scan.nextLine();
                    user = User.login(uName, pw, 2);
                    if(user == null){
                        System.out.println("Invalid Credentials Entered. Please Try Again!");
                    }
                }while(user == null);
                Book b = new Book();
                User realUser = new Customer(user.getUserID(), user.getUserName(),user.getPassword(),user.getEmail(), user.getUserRole(), user.getName().getfName(), user.getName().getlName());
                do {
                    scan.nextLine();
                    userOption = realUser.viewMenu();
                    switch (userOption) {
                        case 1:
                            b.viewAllBooks(b.getBooksfromFile(),realUser.getUserRole());
                            break;
                        case 2:
                            //Order books
                            break;
                        case 3:
                            
                            break;
                        case 4:
                            //generate report
                            break;
                        case 5:
                            //manage customers
                            ((Admin) realUser).viewAllCustomers(new Customer());
                            break;
                        case 6:
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                } while (userOption !=7);
                //if else/ case
            }else{
                Customer.registration();
                System.out.println("Successfully Registered..You will be navigated to Login :)");
                scan.nextLine();
            }

        }
scan.close();
    }


    public static int manageMenu(int roleID) throws IOException{
        int option;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("\n\n");
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║          Manage Book          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Add New Book               ║");
            System.out.println("║ 2. Remove Book                ║");
            System.out.println("║ 3. Edit Book Details          ║");
            System.out.println("║ 4. Back                       ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("\nEnter your option (1-4): ");
            option = scan.nextInt();

            if(option < 1 || option >4){
                System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
            }
        } while (option < 1 || option >4);


        if(option == 4){
            return 9;
        }else{
            Book bk = new Book(); //creating a book with empty constructor and dont hold any value to send as param in admin's method.
            Admin.manageBookOperation(bk, option, roleID);
            return option;
        }

    }
    public static void header(){
            System.out.println("******************************************");
            System.out.println("*                                        *");
            System.out.println("*     Welcome to Kedai Buku Kamal!        *");
            System.out.println("*  Your One-Stop Bookstore Experience :) *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
    }
}
