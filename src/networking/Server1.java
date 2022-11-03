package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Scanner;

// To create a server, we need ServerSocket()
public class Server1 {
    public static void main(String[] args) throws IOException {
        var PORT = 12345;
        try(var ss = new ServerSocket(PORT)){
            while(true) {
                talkToClient(ss);
            }
        }
    }

    private static void talkToClient(ServerSocket ss) throws IOException {
        try(
                var s = ss.accept();
                var sc = new Scanner(s.getInputStream());
                var pw = new PrintWriter(s.getOutputStream());
        ){
            // Echo Server
            String line = sc.nextLine();
            pw.println(line);
        }
    }
}
