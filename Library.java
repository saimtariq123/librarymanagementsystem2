import java.util.ArrayList;
import java.util.Scanner;

import java.util.Iterator;

public class Library {
    String title1;
    String titleToBorrow;
    int usercheck;
    int bookcheck;
    Scanner sc = new Scanner(System.in);
    ArrayList<Book> booksList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<borrow> borrowList = new ArrayList<>();
    int option = 0;
    Scanner input = new Scanner(System.in);
    boolean flag = true;

  

    // Main method to display the library menu
    void display() {
        while (flag) {
            // Display menu options
            System.out.println("1. Add books");
            System.out.println("2. Add user");
            System.out.println("3. Display books by title or Author");
            System.out.println("4. Returning books");
            System.out.println("5. Borrow books");
            System.out.println("6. Search borrowed books by user ID");
            System.out.println("7. Exit");
            System.out.print("Your option: ");
            option = sc.nextInt();

            // Perform actions based on user input
            switch (option) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                System.out.print("Enter the title or author to search: ");
                title1 = input.next();
                
                 if (!isAlphabetic(title1)) {
                    System.out.println("Please enter a title with alphabetic characters only.");
                    break;
                }
               
                
               
                    BooksByTitleOrAuthor(title1);
                
                break;
                    
                   
                case 4:
                input.nextLine();
                System.out.println("Enter title to return book: ");
                String titleToReturn = input.next();
                input.nextLine();
                System.out.println("Enter user ID: ");
                int userId = 0; // Default value
                if (input.hasNextInt()) {
                    userId = input.nextInt();
                } else {
                    System.out.println("Please enter a valid user ID (numeric value).");
                    // Handle invalid input, such as skipping to the next line or exiting the program
                    break; // Assuming this method is part of a larger method or program
                }
                
                // Check if the title consists of alphabetic characters
                
                if (!isAlphabetic(titleToReturn)) {
                    System.out.println("Please enter a title with alphabetic characters only.");
                    // Handle invalid input, such as skipping to the next iteration or exiting the program
                    break; // Assuming this method is part of a larger method or program
                }
                
                returnBook(userId, titleToReturn);
                
                    break;
                case 5:
                System.out.print("Enter user ID: ");
                int usercheck = 0; // Default value
                if (input.hasNextInt()) {
                    usercheck = input.nextInt();
                } else {
                    System.out.println("Please enter a valid user ID (numeric value).");
                    input.next(); // Consume the invalid input to prevent an infinite loop
                    break; // Exit the switch statement
                }
                
                System.out.println("Enter book ID:");
                 // Default value
                if (input.hasNextInt()) {
                    bookcheck = input.nextInt();
                } else {
                    System.out.println("Please enter a valid book ID (numeric value).");
                    input.next(); // Consume the invalid input to prevent an infinite loop
                    break; // Exit the switch statement
                }
                input.nextLine();
                System.out.print("Enter title to borrow: ");
                String titleToBorrow = input.nextLine();
               // titleToBorrow = titleToBorrow.trim();
                
                // Check if the title consists of alphabetic characters
                
                if (!isAlphabetic(titleToBorrow)) {
                    System.out.println("Please enter a title with alphabetic characters only.");
                    break; // Exit the switch statement
                }
                
                Booktoborrow(usercheck, titleToBorrow);
                break;
                case 6:
                System.out.print("Enter user ID to search for borrowed books: ");
                 
                if (input.hasNextInt()) {
                    usercheck = input.nextInt();
                } else {
                    System.out.println("Please enter a valid user ID (numeric value).");
                    input.next(); // Consume the invalid input to prevent an infinite loop
                    break; // Exit the switch statement
                }
                
                booksborrowedbyID(usercheck);
                break;
                case 7:
                    flag = false;
                    break;
            }
        }
    }
    public void addBooks() {
        Book newBook = new Book();
    
        System.out.println("Enter Book ID: ");
        newBook.bookID =0;
        if (input.hasNextInt()) {
            newBook.bookID = input.nextInt();
        } else {
            System.out.println("Please enter a valid book ID (numeric value).");
            input.next(); // Consume the invalid input to prevent an infinite loop
           return; // Exit the switch statement
        }
        input.nextLine(); // Consume the newline character
    
        System.out.print("Enter Title: ");
        String title = input.nextLine();
        if (!isAlphabetic(title)) {
            System.out.println("Please enter a title with alphabetic characters only.");
            return;
        }
        newBook.title = title;
    
        System.out.print("Enter Author: ");
        String author = input.nextLine();
        if (!isAlphabetic(author)) {
            System.out.println("Please enter an author name with alphabetic characters only.");
            return;
        }
        newBook.author = author;
    
        System.out.print("Enter Genre: ");
        String genre = input.nextLine();
        if (!isAlphabetic(genre)) {
            System.out.println("Please enter a genre with alphabetic characters only.");
            return;
        }
        newBook.genre = genre;
    
        booksList.add(newBook);
    
        // Save data to text file after adding a book
        LibraryFileHandler.saveDataToFile(this);
        System.out.println("Book added successfully!!");
    }
    
     
    
    
   
        public void addUser() {
            User newUser = new User();
        
            System.out.print("Enter User ID: \n");
            newUser.userId =0;
            if (input.hasNextInt()) {
                newUser.userId = input.nextInt();
            } else {
                System.out.println("Please enter a valid user ID (numeric value).");
                input.next(); // Consume the invalid input to prevent an infinite loop
               return; // Exit the switch statement
            }
           
        
            System.out.println("Enter Name: ");
            String name = input.next();
            if (!isAlphabetic(name)) {
                System.out.println("Please enter a valid name with alphabetic characters only.");
                return;
            }
            newUser.name = name;
        
            System.out.println("Enter Contact: ");
            String contact = input.next();
            if (!isNumeric(contact)) {
                System.out.println("Please enter a valid contact number with numeric characters only.");
                return;
            }
            newUser.contact = contact;
        
            userList.add(newUser);
        
            // Save data to text file after adding a user
            LibraryFileHandler.saveDataToFile(this);
            System.out.println("user added successfully!!");
        }
        //to check for numbers
        private boolean isNumeric(String input) {
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (!(ch >= '0' && ch <= '9')) {
                    return false;
                }
            }
            return true;
        }
        
        
        //to search by title or author

    boolean BooksByTitleOrAuthor(String title1) {
        System.out.println("\n");
        Library library1=LibraryFileHandler.loadDataFromFile();
        
       

        boolean found = false;
        for (Book num : library1.booksList) {
            System.out.println();
           
            if (title1.equalsIgnoreCase(num.title)|| title1.equalsIgnoreCase(num.author)) {
                System.out.println("Match found!");
                System.out.println("Book ID: " + num.bookID);
                System.out.println("Title: " + num.title);
                System.out.println("Author: " + num.author);
                System.out.println("Genre: " + num.genre);
                System.out.println("Availability: " + (num.availability ? "Available" : "Not Available"));
                System.out.println();
                found = true;
                
            }
        }

        if (!found) {
            System.out.println("The title  or author was not found.");

        }
        return true;
    }
   //to borrow book

    void Booktoborrow(int usercheck, String titleToBorrow) {
        System.out.println("\n");
        Library library2 = LibraryFileHandler.loadDataFromFile();
    
        boolean found = false;
    
        for (Book num : library2.booksList) {
            System.out.println(num.title);
            if (titleToBorrow.equalsIgnoreCase(num.title) && num.availability) {
                // Create a new instance of borrow with parameters
                borrow borrow1 = new borrow(usercheck, titleToBorrow);
                System.out.println("Book borrowed successfully");
    
                // Add the borrowed book information to borrowList
                library2.borrowList.add(borrow1);
               // library2.booksList.remove(num);
                // Mark the book as unavailable
                num.availability = false;
    
                found = true;
                break; // No need to continue checking once a book is found
            }
        }
    
        if (!found) {
            System.out.println("Book is not available for borrowing");
        }
    
        // Save the updated data to the "borrow.txt" file
        LibraryFileHandler.saveDataToFile(library2);
      
    }
    void booksborrowedbyID(int usercheck){
        boolean found2=false;
        Library library3=LibraryFileHandler.loadDataFromFile();
        for (borrow num : library3.borrowList) {
            System.out.println();
           // System.out.println("Comparing: " + title1 + " with " + num.bookTitle);
            if (usercheck==(num.checkid)) {
                System.out.println("Match found!");
                System.out.println("Book ID: " + num.checkid);
                System.out.println("Title: " + num.bookTitle);
                
                found2 = true;
                
            }
        }

        if (!found2) {
            System.out.println("The title was not found.");

        }
        
    }
    //to return book
    void returnBook(int usercheck, String titleToReturn) {
        Library library = LibraryFileHandler.loadDataFromFile();
        Iterator<borrow> iterator = library.borrowList.iterator();
        boolean found = false;
    
        while (iterator.hasNext()) {
            borrow borrowedBook = iterator.next();
            if (usercheck == borrowedBook.checkid && titleToReturn.equalsIgnoreCase(borrowedBook.bookTitle)) {
                // Mark the book as available
                for (Book book : library.booksList) {
                    if (titleToReturn.equalsIgnoreCase(book.title)) {
                        book.availability = true;
                        break; // No need to continue checking once the book is found
                    }
                }
    
                // Remove the borrowed book information from borrowList
                iterator.remove();
    
                System.out.println("Book returned successfully.");
                found = true;
                break;
            }
        }
    
        if (!found) {
            System.out.println("Book not found in the borrowed list for the specified user ID.");
        }
    
        // Save the updated data to the "borrow.txt" file
        LibraryFileHandler.saveDataToFile(library);
    }
    boolean isAlphabetic(String input) {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            // Skip spaces
            if (ch == ' ') {
                continue;
            }
            if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))) {
                return false;
            }
        }
        return true;
    }
    
    }
