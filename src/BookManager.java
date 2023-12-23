import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class BookManager {
    private static Vector<Book> books;

    public Vector<Book> getBooksfromFile() throws FileNotFoundException {
        Vector<Book> books = new Vector<Book>();
        Scanner sc = new Scanner(new File("booksDatabase.txt"));
        System.out.print("╔═══════════════════════════════════════════════════════════════════╗\n");
        System.out.print("║Book Id ║Title  ║  Main Author  ║   Genre  ║    quantity In Stock  ║\n");
        System.out.print("║════════║═══════║═══════════════║══════════║═══════════════════════║\n");
        while(sc.hasNext()){
            String bookId = sc.next();
            String title = sc.next();
            String mainAuthor = sc.next();
            String genre = sc.next();
            int quantityInStock = sc.nextInt();
            System.out.printf("║%-5s║%-20s║%-20s║%-15s║%-5d║%n", bookId, title, mainAuthor, genre, quantityInStock);

            // if(roleID == userRoleID){
            Book book = new Book(bookId, title, mainAuthor, genre, quantityInStock);
            books.add(book);
            // }
        }
        System.out.print("════════════════════════════════════════════════════════════════════\n");
        return books;
        
    }


    public void addBooksIntoFile(Book c) throws IOException{
        PrintWriter outputFile = new PrintWriter(new FileWriter("booksDatabase.txt",true));
        outputFile.write(c.getBookID()+ " "+c.getTitle()+ " "+c.getMainAuthor()+ " "+c.getGenre()+ " "+c.getQuantityInStock()+"\n");
        outputFile.close();
        // books.add(c);
    }

    // remove book from file
    //the data inside booksDatabase also will be deleted base on the bookId
    public boolean removeBookFromFile(String bookId) throws IOException{
        Vector<Book> books = new Vector<Book>();
        books = getBooksfromFile();
        for(Book book:books){
            if(book.getBookID().equals(bookId)){
                books.remove(book);
                return true;
            }
        }
        books = displayBookDetails();
        
        return false;
    }

    //edit book detail
    //the data inside booksDatabase also will be edited base on the bookId




    public boolean editBookDetail(Book b){
        for(Book book:books){
            if(book.getBookID().equals(b.getBookID())){
                book.setBookID(b.getTitle());
                book.setTitle(b.getMainAuthor());
                book.setGenre(b.getGenre());
                book.setQuantityInStock(b.getQuantityInStock());
                return true;
            }
        }
        return false;
    }

    public Vector<Book> displayBookDetails(){
        return books;
    }

        // Vector<Book> books = new Vector<Book>();
        // books = getBooksfromFile();
        // for(Book book : books){
        //         return book.getTitle();
        // }
        // return null;
}
