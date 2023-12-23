import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Admin extends User {

    private String adminID;
    private CustomerManager customers;
    private static BookManager books;

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

    
    public static int viewAdminMenu() throws IOException{
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        int option;
        int bookOption =0 ;
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
            switch(option){
                case 4:
                books = new BookManager();
                //i wanna run the getBooksfromFile() method here.
                books.getBooksfromFile();
                // System.out.println(books.getBooksfromFile());
                // System.out.println(books.getBooksfromFile().get(0).getTitle());
                // System.out.println(books.getBooksfromFile().get(1).getTitle());
                    // System.out.print("\033[H\033[2J");  
                    // System.out.flush();
                do{
                System.out.println("╔═══════════════════════════════╗");
                System.out.println("║ 1. Add book                   ║");
                System.out.println("║ 2. Edit book Details          ║");
                System.out.println("║ 3. Remove book                ║");
                System.out.println("║ 4. Display all book           ║");
                System.out.println("║ 5. Exit                       ║");
                System.out.println("╚═══════════════════════════════╝");
                System.out.print("\n\n Enter the option (1-5) : ");
                bookOption = sc.nextInt();

                switch(bookOption){
                case 1:
                    System.out.println("Enter the book details");
                    System.out.print("Enter the book ID: ");
                    String bookID = sc.next();
                    System.out.print("Enter the book title: ");
                    String title = sc.next();
                    System.out.print("Enter the book main author: ");
                    String mainAuthor = sc.next();
                    System.out.print("Enter the book genre: ");
                    String genre = sc.next();
                    System.out.print("Enter the book quantity in stock: ");
                    int quantityInStock = sc.nextInt();
                    Book b = new Book(bookID, title, mainAuthor, genre, quantityInStock);
                    books.addBooksIntoFile(b);
                    System.out.println("Book added successfully!");
                    System.out.println("Press any key to continue...");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Enter the book Id to Remove: ");
                    String RemoveBookID = sc.next();
                    books.removeBookFromFile(RemoveBookID);
                    break;
                case 4:
                    books.getBooksfromFile();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    bookOption = 5;
                    break;
                default:
                    System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
                    break;

                }

                }while(bookOption != 5);
                break;
            case 5:
                System.out.println("Generating report...");
                break;
                
            }

        }while(option < 1 || option > 7 || option != 7);
        return option;
    }

}
