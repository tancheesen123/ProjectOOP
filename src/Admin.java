import java.io.FileNotFoundException;
import java.util.*;

class Admin extends User {

    private String adminID;
    private CustomerManager customers;

    public Admin(String id, String name ,String pw, String mail, int roleID) throws FileNotFoundException{
        super(id, name, pw, mail, roleID);
        adminID = id;
        customers = new CustomerManager(); //composition
    }
    public String getAdminID(){
        return adminID;
    }
    public void setAdminID(String id){
        adminID = id;
    }

    
    public static int viewAdminMenu(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║           Admin Menu          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. View Books Catalog         ║");
            System.out.println("║ 2. View Book Inventory Levels ║");
            System.out.println("║ 3. Manage Orders              ║");
            System.out.println("║ 4. Manage Book                ║");
            System.out.println("║ 5. Generate Report            ║");
            System.out.println("║ 6. Manage Customers           ║");
            System.out.println("║ 7. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");

            System.out.print("\n\n Enter the option (1-7) : ");
            option = sc.nextInt();

            if(option < 1 || option > 7){
                System.out.println("Invalid option entered. Please enter a number between 1 and 7. Try Again :)");
            }

        }while(option < 1 || option > 7);
        return option;
    }

}
