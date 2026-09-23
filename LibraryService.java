import java.util.List;

public class LibraryService {

    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public String addBook(
            int id,
            String title,
            String author,
            String category) {

        if (id <= 0) {
            return "Book ID must be greater than zero.";
        }

        if (isEmpty(title)) {
            return "Book title cannot be empty.";
        }

        if (isEmpty(author)) {
            return "Author name cannot be empty.";
        }

        if (isEmpty(category)) {
            return "Category cannot be empty.";
        }

        if (repository.findById(id) != null) {
            return "Book ID already exists.";
        }

        Book book = new Book(
                id,
                title.trim(),
                author.trim(),
                category.trim()
        );

        repository.add(book);

        return "Book added successfully.";
    }

    // READ
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // SEARCH BY ID
    public Book getBookById(int id) {
        return repository.findById(id);
    }

    // UPDATE
    public String updateBook(
            int id,
            String title,
            String author,
            String category) {

        Book book = repository.findById(id);

        if (book == null) {
            return "Book not found.";
        }

        if (isEmpty(title)) {
            return "Book title cannot be empty.";
        }

        if (isEmpty(author)) {
            return "Author name cannot be empty.";
        }

        if (isEmpty(category)) {
            return "Category cannot be empty.";
        }

        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setCategory(category.trim());

        return "Book updated successfully.";
    }

    // DELETE
    public String deleteBook(int id) {

        if (repository.delete(id)) {
            return "Book deleted successfully.";
        }

        return "Book not found.";
    }

    // SEARCH
    public List<Book> searchBooks(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllBooks();
        }

        return repository.searchByTitle(keyword.trim());
    }

    // FILTER
    public List<Book> filterByCategory(String category) {

        if (category == null || category.trim().isEmpty()) {
            return getAllBooks();
        }

        return repository.searchByCategory(category.trim());
    }

    // BORROW
    public String borrowBook(int id) {

        Book book = repository.findById(id);

        if (book == null) {
            return "Book not found.";
        }

        if (!book.isAvailable()) {
            return "Book is already borrowed.";
        }

        book.setAvailable(false);

        return "Book borrowed successfully.";
    }

    // RETURN
    public String returnBook(int id) {

        Book book = repository.findById(id);

        if (book == null) {
            return "Book not found.";
        }

        if (book.isAvailable()) {
            return "Book is already available.";
        }

        book.setAvailable(true);

        return "Book returned successfully.";
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}