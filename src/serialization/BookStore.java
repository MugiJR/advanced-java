package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore implements BookInterface{
    Map<String, Book> bookMap = new HashMap<>();

    @Override
    public void addSomeBooks() {
        List<Book> predefinedBooks = List.of(
                new Book("Book1", "author1", 2001,100),
                new Book("Book3", "author2", 2002,110),
                new Book("Book4", "author3", 2003,120)
        );
        predefinedBooks.forEach(book -> bookMap.put(book.getTitle(),book));
        System.out.println("Predefined books have been loaded into the Map");
    }

    @Override
    public boolean saveBooks(String fileName) {
        try {
            save(fileName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean loadBooks(String fileName) {
        try {
            load(fileName);
            return true;
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private void save(String fileName) throws IOException{
        try(var oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Map.Entry<String,Book> entry : bookMap.entrySet())
                oos.writeObject(entry.getValue().toString());
        }
    }


    private void load(String fileName) throws IOException, ClassNotFoundException {
        //Todo - It reads one book only at the moment
        try(var ois = new ObjectInputStream(new FileInputStream(fileName))) {
                   System.out.println(ois.readObject().toString());
        }
    }


}
