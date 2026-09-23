import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    // Create
    public void add(Book book) {
        books.add(book);
    }

    // Read all
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    // Find by ID
    public Book findById(int id) {

        for (Book book : books) {

            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    // Delete
    public boolean delete(int id) {

        Book book = findById(id);

        if (book != null) {
            books.remove(book);
            return true;
        }

        return false;
    }

    // Search by title
    public List<Book> searchByTitle(String keyword) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(book);
            }
        }

        return result;
    }

    // Search by category
    public List<Book> searchByCategory(String category) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {

            if (book.getCategory()
                    .equalsIgnoreCase(category)) {

                result.add(book);
            }
        }

        return result;
    }
}