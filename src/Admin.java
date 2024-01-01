import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Admin extends User {
    private Vector<Customer> customers;
    private static Vector<Book> books;

    public Admin(String id, String name ,String pw, String mail, int roleID, String fName, String lName, Vector<Book> bks){
        super(id, name, pw, mail, roleID,fName,lName);
        customers = new Vector<Customer>();
        //books = new Vector<Book>(); //aggregation
        books = bks;
    }

    public Admin(){}

    public void viewAllCustomers(Customer c) throws FileNotFoundException{
        customers = c.getCustomersfromFile();
        c.viewAllCustomers(customers);
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public static Vector<Book> getBooks() {
        return books;
    }

    //double check with Dr whether what i did is correct in terms of association here.
    public static void manageBookOperation(Book b, int option, int roleID) throws IOException{ 
        switch (option) {
            case 1:
                b.addBooksIntoFile(books);//this method is in book class
                break;
            case 2:
                b.removeBookFromFile(books);
                break;
            case 3:
                b.updateBookMenu(books,roleID);
                break;
            case 4:
                b.viewAllBooks(books,roleID); 
                break;
            default:
                break;
        }
    }

    @Override
    protected int viewMenu(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║           Admin Menu          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. View Books Catalog         ║");
            System.out.println("║ 2. Order Book                 ║");
            System.out.println("║ 3. View Customer Orders       ║");
            System.out.println("║ 4. View Supplier Orders       ║");
            System.out.println("║ 5. View All Orders            ║");
            System.out.println("║ 6. Manage Book                ║");
            System.out.println("║ 7. Generate Report            ║");
            System.out.println("║ 8. View All Customers         ║");
            System.out.println("║ 9. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");

            System.out.print("\n\n Enter the option (1-6) : ");
            option = sc.nextInt();

            if(option < 1 || option > 6){
                System.out.println("Invalid option entered. Please enter a number between 1 and 7. Try Again :)");
            }

        }while(option < 1 || option > 6);
        return option;
    }


}
