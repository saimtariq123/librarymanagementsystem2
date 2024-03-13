import java.io.Serializable;

public class Book implements Serializable  {
    public int bookID;
    String title;
    String author;
    String genre;
    boolean availability = true;
    public Book(int bookID, String title, String author, String genre,boolean availability) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = true; // Set initial availability, adjust if needed
    }
    public Book(){

    }
    @Override //to keep the data stored in file in string
    public String toString() {
        return bookID + "," + title + "," + author + "," + genre + "," + availability;
    }
  
}