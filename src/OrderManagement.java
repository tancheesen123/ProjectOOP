import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OrderManagement {
    private String orderID;
    private Vector<Book> bookInfo;
    private String orderStatus;
    private double totalAmount;
    private User user;
    private String orderDate;
    private Report report;
    private Vector<Integer> bookQuantity; //tmb

    public OrderManagement(String orderID, Vector<Book> bookInfo, String orderStatus, User user, double totalAmount, String orderDate, Report report, Vector<Integer> quantity){
        this.orderID = orderID;
        this.bookInfo = bookInfo;
        this.orderStatus = orderStatus;
        this.user = user;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.report = report;
        bookQuantity = quantity;
    }
    
    public OrderManagement(){
        
    }
    // TAN MUST UPDATE THIS
    public Vector<Book> getOrderFromFile() throws FileNotFoundException {
        Vector<Book> books = new Vector<Book>();
        Scanner sc = new Scanner(new File("Submission/sec01_perdana/Group3/source_code/src/booksDatabase.txt"));
        
        while(sc.hasNext()){
            String bookId = sc.next();
            String title = sc.next();
            String mainAuthor = sc.next();
            int genre = sc.nextInt();
            int quantityInStock = sc.nextInt();
            double price = sc.nextDouble();
            Book book = new Book(bookId, title, mainAuthor, genre, quantityInStock,price);
            books.add(book);
        }
        return books; 
    }

    public void addBooks(Book book, User user){
        if(user.getUserRole() == 1){ //admin
            
        }else if(user.getUserRole() == 2){

        }
    }

    public String getOrderID(){
        return orderID;
    }

    public String getOrderStatus(){
        return orderStatus;
    }   

    public double getTotalAmount(){
        return totalAmount;
    }

    public String getOrderDate(){
        return orderDate;
    }

    public void setOrderID(String orderID){
        this.orderID = orderID;
    }
    //typo
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }

    //typo
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public void generateInvoice(){ //order object need to be sent here to show the order stuff 
        System.out.println("===============================================");
        System.out.println("             KEDAI BUKU KAMAL");
        System.out.println("===============================================");
        System.out.println("Address:      " + "No 12, Jalan Sri Sabah 25, Taman University, 41200 Klang, Selangor");
        System.out.println("Phone Number: " + "03-9973628777");
        System.out.println("-----------------------------------------------");

        System.out.printf("%-20s | %-8s | %-10s\n", "Book Name", "Quantity", "Price");
        System.out.println("-----------------------------------------------");
            for(int i:bookQuantity){
                 System.out.printf("%-20s | %-8d | $%-10.2f\n",bookInfo.get(bookInfo.size()-1).getTitle(),i,bookInfo.get(bookInfo.size()-1).getBookPrice()*i);
            }
            System.out.println("-----------------------------------------------");
            System.out.printf("%-29s $%.2f\n", "Total Amount to Pay (RM):", getTotalAmount());
            System.out.println("==============================================="); 
        //Create new method or continue to add into order file sini.
    }

    // public void calculateTotalAmount(){

    // }

    public User getUser(){
        return user;
    }

    public Vector<Book> getBookInfo(){
        return bookInfo;
        
    }

    public static void getAllCustomerOrder(){
        // read from file ordersDatabase and tunjuk with bookName, quantity and total amount of that user and continue to another user.
    }

}