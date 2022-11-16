package serialization;

public class BookStoreTest {
    public static void main(String[] args) {
        BookStore bookStore = new BookStore();
        bookStore.addSomeBooks();
        System.out.println("Are books saved into the file - "+bookStore.saveBooks("books.txt"));
        System.out.println("Are books loaded from the file - "+bookStore.loadBooks("books.txt"));
    }
}
