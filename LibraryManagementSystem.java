public class LibraryManagementSystem {

    public static void main(String[] args) {

        BookRepository repository = new BookRepository();

        LibraryService service =
                new LibraryService(repository);

        LibraryUI ui =
                new LibraryUI(service);

        ui.start();
    }
}