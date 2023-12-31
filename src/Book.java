import java.io.*;
import java.util.*;

class Book{
    private String bookID;
    private String title;
    private String mainAuthor;
    private int genre;
    private int quantityInStock;
    private double bookPrice; //tmb


    public Book(){}

    public Book(String id, String titleBook, String author, int genreCat, int stock,double price){
        bookID = id;
        title = titleBook;
        mainAuthor = author;
        genre = genreCat;
        quantityInStock = stock;
        bookPrice = price; //tmb
    }

    public double getBookPrice() { //tmb
        return bookPrice;
    }
    public void setBookPrice(double bookPrice) { //tmb
        this.bookPrice = bookPrice;
    }

    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMainAuthor() {
        return mainAuthor;
    }
    public void setMainAuthor(String mainAuthor) {
        this.mainAuthor = mainAuthor;
    }
    public int getGenre() {
        return genre;
    }
    public void setGenre(int genre) {
        this.genre = genre;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void displayBook(int role){ //Testing since need to check with Sarveish.
        String genreStr ="";
        switch (genre) {
            case 1:
                genreStr = "Romance";
                break;
            case 2:
                genreStr = "Mystery";
                break;
            case 3:
                genreStr = "Fantasy";
                break;
            case 4:
                genreStr = "Comedy";
                break;
            case 5:
                genreStr = "Thriller";
                break;
            default:
                break;
        }
        String title = getTitle().replaceAll("_", " ");
        String author = getMainAuthor().replaceAll("_", " ");
        if (role == 1) {
            System.out.printf("║%-8s║%-30s║%-30s║%-22s║%-24d║%-20.2f║%n", getBookID(), title, author, genreStr, getQuantityInStock(), getBookPrice());
        } else {
            System.out.printf("║%-8s║%-30s║%-30s║%-22s║%-20.2f║%n", getBookID(), title, author, genreStr, getBookPrice());
        }
    }

    public Vector<Book> getBooksfromFile() throws FileNotFoundException {
        Vector<Book> books = new Vector<Book>();
        Scanner sc = new Scanner(new File("booksDatabase.txt"));
        
        while(sc.hasNext()){
            String bookId = sc.next();
            String title = sc.next();
            String mainAuthor = sc.next();
            int genre = sc.nextInt();
            int quantityInStock = sc.nextInt();
            double bookPrice = sc.nextDouble();
            Book book = new Book(bookId, title, mainAuthor, genre, quantityInStock,bookPrice);
            books.add(book);
        }
        return books; 
    }

    public void addBooksIntoFile(Vector<Book> bk) throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("============ADD BOOK============");
        System.out.print("\nEnter Book ID: ");
        String id = scan.nextLine();

        Vector<Book> bkList = new Vector<Book>();
        bkList = getBooksfromFile();

        for(Book c:bkList){
            while (c.getBookID().equals(id)) {
                System.out.println("Book ID already exists. Please choose a different Book ID.");
                System.out.print("\nEnter a new Book ID : ");
                id = scan.nextLine();
            }
        }

        System.out.print("\nEnter Title: ");
        String ttl = scan.nextLine();

        System.out.print("\nEnter Main Author: ");
        String mainAuthor = scan.nextLine();
        int genreOption;
        do{
            System.out.print("\nEnter new book genre (1-Romance , 2-Mystery , 3-Fantasy , 4-Comedy , 5-Thriller) : ");
            genreOption = scan.nextInt();
            if(genreOption <1 || genreOption>5){
                System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
            }
        }while(genreOption <1 || genreOption>5);


        int quantityInStock; 
        do{
            System.out.print("\nEnter Quantity in Stock: ");
            quantityInStock = scan.nextInt();
            if (quantityInStock >= 0) {
                break;
            } else {
                System.out.println("Stock quantity cannot be lower than 0. Please enter a valid stock quantity.");
            }
        }while(quantityInStock<=0);

        double bookPrice;
        do{
            System.out.print("\nEnter Book Price (RM): ");
            bookPrice = scan.nextDouble();
            if (bookPrice >= 0) {
                break;
            } else {
                System.out.println("Invalid price entered. Please try again.");
            }
        }while(bookPrice<=0);



        Book c = new Book(id, ttl, mainAuthor, genreOption, quantityInStock,bookPrice);
        PrintWriter outputFile = new PrintWriter(new FileWriter("booksDatabase.txt",true));
        String title = c.getTitle().replaceAll(" ", "_");
        String author = c.getMainAuthor().replaceAll(" ", "_");
        outputFile.write(c.getBookID()+ " "+title+ " "+author+ " "+c.getGenre()+ " "+c.getQuantityInStock()+" "+c.getBookPrice()+"\n");
        outputFile.close();
        bk.add(c);
    }

    public void viewAllBooks(Vector<Book> books,int role) throws FileNotFoundException{ //based on role as Customer no need to see stock quantity what.
        // Vector<Book> books = new Vector<Book>();
        // books = getBooksfromFile(); // double check with sarveish as we might use association here.
        System.out.print("╔════════╦══════════════════════════════╦══════════════════════════════╦══════════════════════╦════════════════════════╗\n");
        if(role == 1){
            System.out.print("║Book ID ║            Title             ║         Main Author          ║         Genre        ║       Stock Quantity        ║       Price (RM)        ║\n");

        }else{
            System.out.print("║Book ID ║            Title             ║         Main Author          ║         Genre        ║    Price (RM)      ║\n");
        }
        System.out.print("╠════════╬══════════════════════════════╬══════════════════════════════╬══════════════════════╬════════════════════════╣\n");
        for(Book bk:books){
            bk.displayBook(role);
        }
        System.out.print("╚════════╩══════════════════════════════╩══════════════════════════════╩══════════════════════╩════════════════════════╝\n");
        System.out.print("Press Enter to continue...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }

    public void updateBookMenu(Vector<Book> books, int role) throws IOException{
        // Vector<Book> books = new Vector<Book>();
        // books = getBooksfromFile(); // double check with sarveish as we might use association here.
        Scanner scan = new Scanner(System.in);
        int option, newOption;
        do {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            viewAllBooks(books,role);
            System.out.println("\n\n");
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║          Update Book          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Book Title                 ║");
            System.out.println("║ 2. Book Main Author           ║");
            System.out.println("║ 3. Book Genre                 ║");
            System.out.println("║ 4. Book Stock Quantity        ║");
            System.out.println("║ 5. Book Price                 ║");
            System.out.println("║ 6. Back                       ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("\nEnter your option (1-5): ");
            option = scan.nextInt();

            if(option < 1 || option >6){
                System.out.println("Invalid option entered. Please enter a number between 1 and 6. Try Again :)");
            }
        } while (option < 1 || option >6);
        scan.nextLine();
        System.out.println("Enter Book ID : "); 
        String  id = scan.nextLine();
        boolean validation  = false;

        for (Book bk : books) {
            if (bk.getBookID().equals(id)) {
                validation = true;
                break;
            }
        }

        while(validation == false){
                System.out.println("Book ID not exists in our database. Please Try Again!");
                System.out.print("\nEnter Book ID : ");
                id = scan.nextLine();
                for (Book bk : books) {
                    if (bk.getBookID().equals(id)) {
                        validation = true;
                        break;
                    }
                }   
        }

        switch (option) {
            case 1:
                System.out.println("Enter new book title : ");
                String newVal = scan.nextLine();
                Boolean val = editBookDetails(books,option, id, newVal);
                break;
            case 2:
                System.out.println("Enter new book author : ");
                newVal = scan.nextLine();
                val = editBookDetails(books,option, id, newVal);
                break;
            case 3:
                do{
                    System.out.println("Enter new book genre (1-Romance , 2-Mystery , 3-Fantasy , 4-Comedy , 5-Thriller) : ");
                    newVal = scan.nextLine();
                    newOption = Integer.parseInt(newVal);
                    if(newOption <1 && newOption>5){
                        System.out.println("Invalid option entered. Please enter a number between 1 and 5. Try Again :)");
                    }
                }while(newOption <1 && newOption>5);
                 val = editBookDetails(books,option, id, newVal);
                break;
            case 4:
                System.out.println("Enter new book stock Quantity : ");
                newVal = scan.nextLine();
                val = editBookDetails(books,option, id, newVal);
                break;
            case 5:
                System.out.println("Enter new book price (RM) : ");
                newVal = scan.nextLine();
                val = editBookDetails(books,option, id, newVal);
                break;
            default:
                break;
        }
    }

    public void removeBookFromFile(Vector<Book> bk2) throws IOException{
        //Sar : wei yang, we can do this by using bk2 param in my opinion..need to check whether our style of association possible ke tak with lect.
        // Vector<Book> books = new Vector<Book>();
        // books = getBooksfromFile(); // double check with sarveish as we might use association here.
        viewAllBooks(bk2,1);
        String bookId = "";
        int index =0;
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter Book ID : "); 
        bookId = scan.nextLine();
        boolean validation  = false;

        for (Book bk : bk2) {
            if (bk.getBookID().equals(bookId)) {
                validation = true;
                break;
            }
        }

        while(validation == false){
                System.out.println("Book ID not exists in our database. Please Try Again!");
                System.out.print("\nEnter Book ID : ");
                bookId = scan.nextLine();
                for (Book bk : bk2) {
                    if (bk.getBookID().equals(bookId)) {
                        validation = true;
                        break;
                    }
                }   
        }
        PrintWriter outputFile = new PrintWriter(new FileWriter("booksDatabase.txt"),false);
        for(Book book:bk2){
            if(book.getBookID().equals(bookId)){
                break;
            }else{
                outputFile.write(book.getBookID()+ " "+book.getTitle()+ " "+book.getMainAuthor()+ " "+book.getGenre()+ " "+book.getQuantityInStock()+" "+book.getBookPrice()+"\n");
            }
            index++;
        }
        bk2.remove(index);
        outputFile.close();
    }

    public Boolean editBookDetails(Vector<Book> books,int category, String id, String value) throws IOException{
        value = value.replaceAll(" ", "_");
        for(Book book:books){
            if(book.getBookID().equals(id)){
                switch (category) {
                    case 1:
                        book.setTitle(value);
                        break;
                    case 2:
                        book.setMainAuthor(value);
                        break;
                    case 3:
                        book.setQuantityInStock(Integer.parseInt(value));
                        break;
                    case 4:
                        book.setGenre(Integer.parseInt(value));
                        break;
                    case 5:
                        book.setBookPrice(Double.parseDouble(value));
                    default:
                        return false;
                }
            }

        }
        if(books !=null){
            FileWriter file = new FileWriter("booksDatabase.txt",false);
            for (Book bk : books) {
                file.write(bk.getBookID()+ " "+bk.getTitle()+ " "+bk.getMainAuthor()+ " "+bk.getGenre()+ " "+bk.getQuantityInStock()+" "+bk.getBookPrice()+"\n");
            }
            file.close();
        }
        return true;
    }


}