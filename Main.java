import java.util.*;

public class Main {
    public static void main(String[] args) {
        new LibraryManagementSystem().start();
    }
}

class Book {
    private int id;
    private String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "Book[ID=" + id + ", Title='" + title + "']";
    }
}

class LibraryManagementSystem {
    private Stack<Book> returnedBooks;
    private Queue<Book> issuedBooks;
    private Scanner scanner;

    public LibraryManagementSystem() {
        returnedBooks = new Stack<>();
        issuedBooks = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void returnBook() {
        System.out.print("Enter Book ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer for Book ID: ");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        Book book = new Book(id, title);
        returnedBooks.push(book);
        System.out.println("Book returned: " + book);
    }

    public void issueBook() {
        if (returnedBooks.isEmpty()) {
            System.out.println("No returned books available to issue!");
            return;
        }
        Book book = returnedBooks.pop(); // LIFO from returned stack
        issuedBooks.add(book);           // add to issued queue
        System.out.println("Book issued: " + book);
    }

    public void showIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No issued books.");
            return;
        }
        System.out.println("Issued Books Queue (front -> rear):");
        int pos = 1;
        for (Book b : issuedBooks) {
            System.out.println(pos++ + ". " + b);
        }
    }

    public void showReturnedBooks() {
        if (returnedBooks.isEmpty()) {
            System.out.println("No returned books.");
            return;
        }
        System.out.println("Returned Books Stack (top -> bottom):");
        for (int i = returnedBooks.size() - 1; i >= 0; i--) {
            System.out.println((returnedBooks.size() - i) + ". " + returnedBooks.get(i));
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Return a Book");
            System.out.println("2. Issue a Book");
            System.out.println("3. Show Returned Books (Stack)");
            System.out.println("4. Show Issued Books (Queue)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1: returnBook(); break;
                case 2: issueBook(); break;
                case 3: showReturnedBooks(); break;
                case 4: showIssuedBooks(); break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}