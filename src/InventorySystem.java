import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Supplier;

public class InventorySystem {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice;
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
            if(user.getUserRole() == 1){
                int value = Admin.viewAdminMenu();
                //if else/ case.
            }else{
                int value = BookSupplier.viewSupplierMenu();
                //if else/ case.
            }
        }else{
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
                int value = Customer.viewCustomerMenu();
                //if else/ case
            }else{
                Customer.registration();
                System.out.println("Successfully Registered..You will be navigated to Login :)");
                scan.nextLine();

            }

        }

    }

    public static void header(){
            System.out.println("******************************************");
            System.out.println("*                                        *");
            System.out.println("*     Welcome to Kedai Buku Ali!         *");
            System.out.println("*  Your One-Stop Bookstore Experience :) *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
    }
}
