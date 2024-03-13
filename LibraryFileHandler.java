
    import java.io.*;
   

    public class LibraryFileHandler {
        private static final String BOOKS_FILE = "books.txt";
        private static final String USERS_FILE = "users.txt";
        private static final String BORROW_FILE = "borrow.txt";
        Library library= new Library();

        public static void saveDataToFile(Library library) {
            try {
                // Save books data to a text file (overwrite mode)
                FileWriter booksWriter = new FileWriter(BOOKS_FILE, true);
                for (Book book : library.booksList) {
                    booksWriter.write(book.toString()+ System.lineSeparator());
                   System.out.println();
                }
                booksWriter.close();
        
                // Save users data to a text file (overwrite mode)
                FileWriter usersWriter = new FileWriter(USERS_FILE, true);
                for (User user : library.userList) {
                    usersWriter.write(user.toString()+ System.lineSeparator());
                    System.out.println();
                }
                usersWriter.close();
        
                // Save borrow data to a text file (overwrite mode)
                FileWriter borrowWriter = new FileWriter(BORROW_FILE, true);
                for (borrow borrow : library.borrowList) {
                    borrowWriter.write(borrow.toString()+ System.lineSeparator());
                    System.out.println();
                }
                borrowWriter.close();
        
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        

        public static Library loadDataFromFile() {
            Library library = new Library();
        
            try {
                // Load books data from the text file
                BufferedReader booksReader = new BufferedReader(new FileReader(BOOKS_FILE));
                String line;
                while ((line = booksReader.readLine()) != null) {
                    String[] bookData = line.split(",");
                    if (bookData.length != 5) {
                        // Incorrect data format, handle appropriately
                        System.out.println("");
                        continue;
                    }
                    int bookID = Integer.parseInt(bookData[0]);
                    String title = bookData[1];
                    String author = bookData[2];
                    String genre = bookData[3];
                    boolean availability = Boolean.parseBoolean(bookData[4]);
        
                    Book newBook = new Book(bookID, title, author, genre,availability);
                    newBook.availability = availability; // Set the availability
                    library.booksList.add(newBook);
                }
                booksReader.close();
        
                // Load users data from the text file
                BufferedReader usersReader = new BufferedReader(new FileReader(USERS_FILE));
                while ((line = usersReader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length != 3) {
                        // Incorrect data format, handle appropriately
                        System.out.println("");
                        continue;
                    }
                    int userId = Integer.parseInt(userData[0]);
                    String name = userData[1];
                    String contact = userData[2];
        
                    User newUser = new User(userId, name, contact);
                    library.userList.add(newUser);
                }
                usersReader.close();
                BufferedReader borrowReader = new BufferedReader(new FileReader(BORROW_FILE));
            while ((line = borrowReader.readLine()) != null) {
                String[] borrowData = line.split(",");
                if (borrowData.length != 2) {
                    // Incorrect data format, handle appropriately
                    System.out.println("");
                    continue;
                }
                int checkid = Integer.parseInt(borrowData[0]);
                String bookTitle = borrowData[1];

                // Create a new instance of borrow with parameters
                borrow borrowedBook = new borrow(checkid, bookTitle);

                // Add the borrowed book information to borrowList
                library.borrowList.add(borrowedBook);

                // Mark the book as unavailable
              
                }
                borrowReader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return library;
            } 
        
        }
        
