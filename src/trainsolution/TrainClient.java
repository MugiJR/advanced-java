package trainsolution;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import trainsolution.TrainClientServer.ServerAction;

import static trainsolution.Train.*;

public class TrainClient {


    public static void runClient(int PORT, String filename, int startHour, int endHour, int maxTrains, int minHour, int maxHour
                                 ) throws IOException, ClassNotFoundException {
        var HOST = "127.0.0.1";
        List<String> trains =makeHourlies.apply(startHour, endHour).collect(Collectors.toList());
        try (
                Socket s = new Socket(HOST, PORT);
                var oos = new ObjectOutputStream(s.getOutputStream());
                var ois = new ObjectInputStream(s.getInputStream());
        ) {
            sendToServerSave(ServerAction.SAVE, filename, maxTrains, minHour, maxHour, trains, oos);
            var trainList = sendToServerLoad(ServerAction.LOAD, filename, ois, oos);
            for (Train train : trainList) {
                System.out.println("Received ~ " + train);
            }
        }
    }

    private static void sendToServerSave(ServerAction serverAction, String filename, int maxTrains, int minHour,
            int maxHour, List<String> trains, ObjectOutputStream oos) throws IOException {
        oos.writeObject(serverAction);
        oos.writeObject(filename);
        oos.write(maxTrains);
        oos.write(minHour);
        oos.write(maxHour);
        oos.writeObject(trains);
    }

    private static List<Train> sendToServerLoad( ServerAction serverAction, String filename, ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        oos.writeObject(serverAction);
        oos.writeObject(filename);
        var trains = (List<Train>) ois.readObject();
        return trains;
    }
}
