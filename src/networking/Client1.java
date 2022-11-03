package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) throws IOException {
        var HOST = "localhost";
        var PORT = 12345;

        try (
            Socket s = new Socket(HOST, PORT);
            var sc = new Scanner(s.getInputStream());
            var pw = new PrintWriter(s.getOutputStream());
        ) {
            pw.println("Echo this line!");
            pw.flush();
            String answer = sc.nextLine();
            System.out.println(answer);
        }
    }
}
