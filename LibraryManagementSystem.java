
import java.util.*;
 
class Book  
{
    String title;
    String author;
    int year;
    String isbn;
    String status;
 
    Book(String title, String author, int year, String isbn) 
    {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.status = "Available";
    }//Constructor
    //The setter method of the program
 
     public void displayDetails() 
    {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publication Year: " + year);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + status);
        System.out.println();
    }//Method to display the details of the Book
 
    public String getStatus() 
    {
        return status;
    }//Getter method
 
    public void checkOutBook(String userName, String contactDetails) 
    {
        if (status.equals("Available")) 
        {
            status = "Checked Out";
            System.out.println("The book has been successfully checked out by" + userName + " (" + contactDetails + ")");
        } 
        else 
        {
            System.out.println("The book is already checked out.Sorry!");
        }
    }//Method to check out a book
 
    public void returnBook() {
        status = "Available";
        System.out.println("The book is returned successfully.");
    }//Method to return the book
}
 
 
class Library 
{
     Book[] catalog;
     int bookCount;
 
    Library(int maxSize) 
    {
        catalog = new Book[maxSize];
        bookCount = 0;
    }//Constructor
 
    void addBook(String title, String author, int year, String isbn) 
    {
        if (bookCount < catalog.length) 
        {
            catalog[bookCount] = new Book(title, author, year, isbn);
            bookCount++;
            System.out.println("Book added to the catalog.");
            System.out.println();
        }
        else 
        {
            System.out.println("Cannot add more books. Catalog is full.");
        }
    }//Method to add book to the catalog
    public void searchBook(String query) 
    {
    String title, author;
    Scanner sc = new Scanner(System.in);
    boolean found = false;
    System.out.print("Enter the Title of the Book: ");
    title = sc.nextLine();
    System.out.print("Enter the Author of the Book: ");
    author = sc.nextLine();

    for (Book book : catalog) {
        if (book != null && (book.title.equalsIgnoreCase(title) || book.author.equalsIgnoreCase(author))) {
            book.displayDetails();
            found = true;
        }
    }
    if (!found) 
        {
        System.out.println("No matching books found.");
        }
    }//Method to search the book in the catalog
 
    public void displayCatalog() 
    {
        if (bookCount > 0) 
            {
                System.out.println("Library Catalog:");
                for (Book book : catalog)
                {
                    if (book != null) 
                    {
                         book.displayDetails();
                    }
                }
            } 
            else 
            {
                System.out.println("Library catalog is empty.");
            }
    }//Method to display the catalog
}
 
 
//Main class of the program
class LibraryManagementSystem
{
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library(100);
        int ch;
        while (true) {
            
            System.out.println("\nLibrary Management System\n1. Add a Book\n2. Search for a Book\n3. Check Out a Book\n4. Return a Book\n5. Display Book Catalog\n6. Exit");
            System.out.print("Enter your choice: ");
            ch= sc.nextInt();
            sc.nextLine(); //To consume the newline character that is added after the character that is given as input by the user.It might result in an error if this step is avoided.
 
            switch (ch) 
            {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    lib.addBook(title, author, year, isbn);
                    break;
                case 2:
                    System.out.print("Enter the Title of the Book or the Author to search: ");
                    String searchQuery = sc.nextLine();
                    lib.searchBook(searchQuery);
                    break;
 
                case 3:
                    System.out.print("Enter the Title of the Book or the Author of the book to check out: ");
                    String checkoutQuery = sc.nextLine();
                    System.out.print("Enter your Name: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter your Contact Details: ");
                    String contactDetails = sc.nextLine();
                    for (Book book : lib.catalog) 
                    {
                    if (book != null && (book.title.equalsIgnoreCase(checkoutQuery) || book.author.equalsIgnoreCase(checkoutQuery))) 
                        {
                            book.checkOutBook(userName, contactDetails);
                            break;
                        }
                     }
                    break;

                case 4:
                    System.out.print("Enter the Title of the Book or Author of the book to return: ");
                    String returnQuery = sc.nextLine();
                    for (Book book : lib.catalog) 
                    {
                    if (book != null && (book.title.equalsIgnoreCase(returnQuery) || book.author.equalsIgnoreCase(returnQuery))) 
                        {
                    book.returnBook();
                    break;
                        }
                    }
                    break; 
                case 5:
                    lib.displayCatalog();
                    break;
                case 6:
                    System.out.println("Exiting from the Library Management System.\nThank you!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Wrong choice! Enter a number between 1 and 6.");
            }
        }
    }
}