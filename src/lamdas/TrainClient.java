package lamdas;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lamdas.TrainClientServer.ServerAction;
import sockets.MoonPhaseHelper;

import static lamdas.Train.*;

public class TrainClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var HOST = "127.0.0.1";
        var PORT = 12345;

        try (
                Socket s = new Socket(HOST, PORT);
                var oos = new ObjectOutputStream(s.getOutputStream());
                var ois = new ObjectInputStream(s.getInputStream());
        ) {
            runClient("trainfile.dat",3,20,10,  10, 12, oos, ois);
        }

    }

    private static void runClient(String filename, int startHour, int endHour, int maxTrains, int minHour, int maxHour,
                                  ObjectOutputStream oss, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        List<String> trains =makeHourlies.apply(startHour, endHour).collect(Collectors.toList());
        sendToServerSave(ServerAction.SAVE, filename, maxTrains, minHour, maxHour, trains, oss);
        sendToServerLoad(ServerAction.LOAD, filename, ois,oss);


    }

    private static void sendToServerSave(
            ServerAction serverAction,
            String filename,
            int maxTrains,
            int minHour,
            int maxHour,
            List<String> trains,
            ObjectOutputStream oos) throws IOException {
        oos.writeObject(serverAction);
        oos.writeObject(filename);
        oos.write(maxTrains);
        oos.write(minHour);
        oos.write(maxHour);
        oos.writeObject(trains);
    }

    private static void sendToServerLoad( ServerAction serverAction, String filename, ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        oos.writeObject(serverAction);
        oos.writeObject(filename);
        //int totalTrainsReceived = ois.read();
        var trains = (List<Train>) ois.readObject();
        for (Train train : trains) {
            System.out.println("Received ~ " + train);
        }

    }
}
