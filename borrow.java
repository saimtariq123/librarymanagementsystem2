

public class borrow {
    int checkid;
    String bookTitle;


    public borrow(int checkid, String bookTitle){
        this.checkid = checkid;
        this.bookTitle = bookTitle;
        
    }

    @Override //to keep the data stored in file in string
    public String toString() {
        return  checkid + "," + bookTitle;
    }
}
