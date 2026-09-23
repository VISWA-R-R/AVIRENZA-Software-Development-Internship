import java.util.List;
import java.util.Scanner;

public class LibraryUI {

    private final Scanner scanner;
    private final LibraryService service;

    public LibraryUI(LibraryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        printHeader();

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    filterBooks();
                    break;

                case 5:
                    updateBook();
                    break;

                case 6:
                    deleteBook();
                    break;

                case 7:
                    borrowBook();
                    break;

                case 8:
                    returnBook();
                    break;

                case 9:
                    running = false;
                    System.out.println();
                    System.out.println("Thank you for using the Library Management System.");
                    System.out.println("Program ended successfully.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice. Please select 1-9.");
            }
        }

        scanner.close();
    }

    private void printHeader() {

        System.out.println();
        System.out.println("======================================================");
        System.out.println("          LIBRARY MANAGEMENT SYSTEM");
        System.out.println("             AVIRENZA TECHNOLOGIES");
        System.out.println("======================================================");
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("==================== MAIN MENU =======================");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Filter by Category");
        System.out.println("5. Update Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Borrow Book");
        System.out.println("8. Return Book");
        System.out.println("9. Exit");
        System.out.println("======================================================");
    }

    // CREATE
    private void addBook() {

        System.out.println();
        System.out.println("================== ADD BOOK ==================");

        int id = readInt("Enter Book ID: ");

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        String result = service.addBook(
                id,
                title,
                author,
                category
        );

        System.out.println();
        System.out.println(result);
    }

    // READ
    private void viewBooks() {

        System.out.println();
        System.out.println("================ ALL BOOKS ===================");

        List<Book> books = service.getAllBooks();

        displayBooks(books);
    }

    // SEARCH
    private void searchBook() {

        System.out.println();
        System.out.println("=============== SEARCH BOOK =================");

        System.out.print("Enter title keyword: ");

        String keyword = scanner.nextLine();

        List<Book> books = service.searchBooks(keyword);

        displayBooks(books);
    }

    // FILTER
    private void filterBooks() {

        System.out.println();
        System.out.println("============= FILTER BY CATEGORY =============");

        System.out.print("Enter category: ");

        String category = scanner.nextLine();

        List<Book> books =
                service.filterByCategory(category);

        displayBooks(books);
    }

    // UPDATE
    private void updateBook() {

        System.out.println();
        System.out.println("=============== UPDATE BOOK =================");

        int id = readInt("Enter Book ID: ");

        Book book = service.getBookById(id);

        if (book == null) {

            System.out.println("Book not found.");

            return;
        }

        System.out.println(
                "Current Title: " + book.getTitle()
        );

        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter New Category: ");
        String category = scanner.nextLine();

        String result = service.updateBook(
                id,
                title,
                author,
                category
        );

        System.out.println();
        System.out.println(result);
    }

    // DELETE
    private void deleteBook() {

        System.out.println();
        System.out.println("=============== DELETE BOOK =================");

        int id = readInt("Enter Book ID: ");

        Book book = service.getBookById(id);

        if (book == null) {

            System.out.println("Book not found.");

            return;
        }

        System.out.println(
                "Book: " + book.getTitle()
        );

        System.out.print(
                "Are you sure you want to delete? (Y/N): "
        );

        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            String result = service.deleteBook(id);

            System.out.println(result);

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // BORROW
    private void borrowBook() {

        System.out.println();
        System.out.println("=============== BORROW BOOK =================");

        int id = readInt("Enter Book ID: ");

        String result = service.borrowBook(id);

        System.out.println(result);
    }

    // RETURN
    private void returnBook() {

        System.out.println();
        System.out.println("=============== RETURN BOOK =================");

        int id = readInt("Enter Book ID: ");

        String result = service.returnBook(id);

        System.out.println(result);
    }

    // DISPLAY RESULTS
    private void displayBooks(List<Book> books) {

        if (books.isEmpty()) {

            System.out.println();
            System.out.println("No books found.");

            return;
        }

        System.out.println();
        System.out.printf(
                "%-6s %-25s %-20s %-15s %-12s%n",
                "ID",
                "TITLE",
                "AUTHOR",
                "CATEGORY",
                "STATUS"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (Book book : books) {

            String status =
                    book.isAvailable()
                            ? "Available"
                            : "Borrowed";

            System.out.printf(
                    "%-6d %-25s %-20s %-15s %-12s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    status
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        System.out.println(
                "Total books: " + books.size()
        );
    }

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();
                scanner.nextLine();

                return value;

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }
}