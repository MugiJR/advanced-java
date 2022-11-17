package sockets;


import serialization.Book;
import serialization.BookStore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var PORT = 12345;
        var bookStore = new BookStore();
        bookStore.addBook(new Book("a","au",12,12));

        try (
                var ss = new ServerSocket(PORT);
        ) {
            while (true) {
                var s = ss.accept();
                new Thread(() -> handleClient(s, bookStore)).start();
            }
        }
    }

    private static void handleClient(Socket s, BookStore bookStore) {
        try(
                // InputStream should come first
                var ois = new ObjectInputStream(s.getInputStream());
                // OutputStream should come next after InputStream
                var oos = new ObjectOutputStream(s.getOutputStream());
                )
        {
            var bookName = (String) ois.readObject();
            Book book = bookStore.getBook(bookName);

            oos.writeObject(book != null);
            if (book != null) oos.writeObject(book);

            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

