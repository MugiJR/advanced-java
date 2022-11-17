package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import serialization.Book;
import serialization.SomeData;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//		var HOST = "localhost";
        var HOST = "127.0.0.1";
        var PORT = 12345;

        try (
                Socket s = new Socket(HOST, PORT);
                // Outputstream should come first
                var oos = new ObjectOutputStream(s.getOutputStream());
                // Inputstream should come next to outputstream
                var ois = new ObjectInputStream(s.getInputStream());
        ) {
                oos.writeObject("a");
                oos.flush();

                var isBookAvailable = (Boolean) ois.readObject();
                if (isBookAvailable) {
                    var book = (Book) ois.readObject();
                    System.out.println(book);
                }
        }
    }
}
