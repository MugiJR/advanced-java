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
            int booksRetrivedFromFile = load(fileName);
            return booksRetrivedFromFile == bookMap.size();
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private void save(String fileName) throws IOException{
        try(var oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            for (Map.Entry<String,Book> entry : bookMap.entrySet())
//                oos.writeObject(entry.getValue());
            oos.writeObject(bookMap);
            oos.flush();
        }
    }


    private int load(String fileName) throws IOException, ClassNotFoundException {
        try(var ois = new ObjectInputStream(new FileInputStream(fileName))) {
                var obj = (HashMap<String, Book>)ois.readObject();
//                for (Map.Entry<String,Book> entry : bookMap.entrySet())
//                {
//                    System.out.println(entry.getValue().toString());
//                }
                return obj.size();
        }
    }


}
