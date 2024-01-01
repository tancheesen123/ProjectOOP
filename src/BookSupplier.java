import java.io.*;
import java.time.LocalDate;
import java.util.*;


class BookSupplier extends User{
    private Vector<OrderManagement> orderList;
    public BookSupplier(String id, String name ,String pw, String mail, int roleID,String fName,String lName){
        super(id, name, pw, mail, roleID,fName,lName);
    }

    public BookSupplier(){
    }


    public static void displaySupplier(User u){
        System.out.println("+-----------------+---------------------+---------------------+---------------------+");
        System.out.println("|  Supplier ID    |    Username         |     Full Name       |        Email        |");
        System.out.println("+-----------------+---------------------+---------------------+---------------------+");
    
        System.out.printf("| %-15s | %-19s | %-19s | %-19s |%n", u.getUserID(), u.getUserName(), u.getName().getfName() + " " + u.getName().getlName(), u.getEmail());
    
        System.out.println("+-----------------+---------------------+---------------------+---------------------+");
    
}
    @Override
    protected int viewMenu(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║          Supplier Menu        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. View all Orders            ║");
            System.out.println("║ 2. Approval Order             ║");
            System.out.println("║ 3. Manage Account             ║");
            System.out.println("║ 4. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");

            System.out.print("\n\n Enter the option (1-3) : ");
            option = sc.nextInt();

            if(option < 1 || option > 3){
                System.out.println("Invalid option entered. Please enter a number between 1 and 3. Try Again :)");
            }

        }while(option < 1 || option > 4);
        return option;
    }

    public void updateOrderStatus(Vector<OrderManagement> orderList, User realUser) throws IOException{
        this.orderList = orderList;
        OrderManagement orderApproval = new OrderManagement();
        // Vector<OrderManagement> orders = new Vector<OrderManagement>();
        // orders = orderApproval.getOrderFromFile(realUser.getUserRole());
        LocalDate UpdateAt = LocalDate.now();

        Book books = new Book();
        Vector<Book> bkList = new Vector<Book>();
        bkList = books.getBooksfromFile();
        //declare initial value
        String bookId ="";
        int quantityOrder = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the book ID to edit : ");
        String orderId = sc.nextLine();
        System.out.print("Approve [1] or Reject [2] Press 1 if Approve and 2 if Reject:");
        int optionApproval = sc.nextInt();
        if(optionApproval == 1){
            for(OrderManagement order : orderList){
                if(order.getOrderID().equals(orderId)){
                    order.setOrderStatus("Approved");
                    bookId = order.getBookInfo().getBookID();
                    quantityOrder = order.getQuantityOrder();
                    System.out.println("Order Approved Successfully");
                }
            }
        }else if(optionApproval == 2){
            for(OrderManagement order : orderList){
                if(order.getOrderID().equals(orderId)){
                    order.setOrderStatus("Rejected");
                    System.out.println("Order Rejected Successfully");
                }
            }
        }
        FileWriter file = new FileWriter("booksDatabase.txt",false);
        FileWriter fileOrders = new FileWriter("ordersDatabase.txt",false);
        for(Book bks : bkList){
            if(bks.getBookID().equals(bookId)){
                file.write(bks.getBookID()+ " "+bks.getTitle()+ " "+bks.getMainAuthor()+ " "+bks.getGenre()+ " "+(bks.getQuantityInStock()+quantityOrder)+" "+bks.getBookPrice()+"\n");
            }else{
                file.write(bks.getBookID()+ " "+bks.getTitle()+ " "+bks.getMainAuthor()+ " "+bks.getGenre()+ " "+bks.getQuantityInStock()+" "+bks.getBookPrice()+"\n");
            }
        }
        int i =1;
        for (OrderManagement order : orderList) {
            // if(order.getOrderID().equals(orderId) && order.getOrderStatus().equals("Approved")){
            //     file.write(order.getBookInfo().getBookID()+ " "+
            //     order.getBookInfo().getTitle()+ " "+order.getBookInfo().getMainAuthor()+ " "+
            //     order.getBookInfo().getGenre()+ " "+(order.getBookInfo().getQuantityInStock()+
            //     order.getQuantityOrder())+" "+order.getBookInfo().getBookPrice()+"\n");
            
            // }else{
            //     file.write(order.getBookInfo().getBookID()+ " "+
            //     order.getBookInfo().getTitle()+ " "+order.getBookInfo().getMainAuthor()+ " "+
            //     order.getBookInfo().getGenre()+ " "+order.getBookInfo().getQuantityInStock()+" "+
            //     order.getBookInfo().getBookPrice()+"\n");
            // }
            //data for ordersDatabase.txt
            fileOrders.write("OR0"+i+" "+order.getTotalAmount()+" "+
            order.getBookInfo().getBookID()+ " "+order.getBookInfo().getTitle()+ " "+order.getBookInfo().getMainAuthor()+ " "+order.getBookInfo().getGenre()+ " "+quantityOrder+" "+order.getBookInfo().getQuantityInStock()+" "+order.getBookInfo().getBookPrice()+" "+
            order.getUser().getUserID()+" "+order.getUser().getUserRole()+" "+
            order.getOrderStatus()+" "+order.getOrderDate()+"\n");
            i++;
            // file.write(bks.getBookID()+ " "+bks.getTitle()+ " "+bks.getMainAuthor()+ " "+bks.getGenre()+ " "+(bks.getQuantityInStock()-quantityOrder)+" "+bks.getBookPrice()+"\n");
        }
        fileOrders.close();
        file.close();
        for(OrderManagement order : orderList){
            System.out.println(order.getOrderID()+" "+order.getOrderStatus());
        }
    }

    public static void manageAccount(User currUser) throws IOException{
        Scanner scan = new Scanner(System.in);
        int option;
        String newValue = "";
        do {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            displaySupplier(currUser);
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
                    String line = String.format("%s %s %s %s %s %d%n", currUser.getUserID(), username, currUser.getPassword(), em,fullName, 3);
                    file.write(line);
                }else{
                    String line = String.format("%s %s %s %s %d%n", c.getUserID(), c.getUserName(), c.getPassword(), c.getEmail(),fullName, c.getUserRole());
                    file.write(line);
                }

            }
            file.close();
        System.out.println("Updated Successfully :)");
    };
}