package serialization;

import java.io.*;

public class SaveToFile {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(var oos = new ObjectOutputStream(new FileOutputStream("File.dat"))) {
            oos.writeObject(1212);
            oos.writeObject("hello world from serialization");
        }

        try(var ois = new ObjectInputStream(new FileInputStream("File.dat"))) {
            int num = (Integer)ois.readObject();
            String line = (String)ois.readObject();

            System.out.println("Then number is  "+num+" and line is "+line);
        }
    }
}
