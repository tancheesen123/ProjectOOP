public class Book {
    private String bookID;
    private String title;
    private String mainAuthor;
    private String genre;
    private int quantityInStock;

    public Book(String bookID, String title, String mainAuthor, String genre, int quantityInStock) {
        this.bookID = bookID;
        this.title = title;
        this.mainAuthor = mainAuthor;
        this.genre = genre;
        this.quantityInStock = quantityInStock;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getMainAuthor() {
        return mainAuthor;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMainAuthor(String mainAuthor) {
        this.mainAuthor = mainAuthor;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void displayBook() {
        System.out.println("Book ID: " + bookID +"Title: " + title+ "Main Author: " + mainAuthor+ "Genre: " + genre+ "Quantity in Stock: " + quantityInStock+"\n");
    }

}
