import java.util.Scanner;


public class User {
    int userId;
    String name;
    String contact;
   
    Scanner input1 = new Scanner(System.in);
    public User(int userId, String name, String contact) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
    }
    public User(){

    }
    @Override //to keep the data stored in file in string
    public String toString() {
        return userId + "," + name + "," + contact;
    }   
}