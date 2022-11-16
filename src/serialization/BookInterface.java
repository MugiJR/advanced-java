package serialization;

public interface BookInterface {

    // Puts some predefined books into the map, to serve as initial data.
    void addSomeBooks();

    // Writes the books into the given file using serialization.
    // Returns whether the save operation was successful.
    boolean saveBooks(String fileName);

    // Loads the books from the given file.
    // Returns whether the load operation was successful.
    boolean loadBooks(String fileName);

}
