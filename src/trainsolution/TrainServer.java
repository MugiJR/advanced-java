package trainsolution;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static trainsolution.Train.*;
import static trainsolution.TrainClientServer.*;

public class TrainServer {

    public static void startServer(int PORT) throws IOException {
        try (
                var ss = new ServerSocket(PORT);
                ){
            while(true) {
                var s = ss.accept();
                new Thread(() -> handleClient(s)).start();
            }
        }
    }

    private static void handleClient(Socket s)  {
        try(
                var ois = new ObjectInputStream(s.getInputStream());
                var oos = new ObjectOutputStream(s.getOutputStream());
                ){
            var serverAction = (ServerAction) ois.readObject();
            var fileName = (String) ois.readObject();
            if (serverAction == ServerAction.SAVE) {
                int maxTrains = ois.read();
                int minHour = ois.read();
                int maxHour = ois.read();
                var trainsText = (List<String>) ois.readObject();
                saveTrains(fileName, maxTrains, minHour, maxHour, trainsText);

            }
            serverAction = (ServerAction) ois.readObject();
            fileName = (String) ois.readObject();
            if (serverAction == ServerAction.LOAD) {
                loadAndSendTrains(fileName, oos);
                oos.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveTrains(String fileName, int maxTrains, int minHour, int maxHour, List<String> trainTexts) {
        var useTrainResult = useTrains11(trainTexts.stream(), isTimeBetween, maxTrains);
        var trainStream = useTrainResult.apply(Map.entry(minHour, maxHour)).collect(Collectors.toList());
        try {
            save(fileName, trainStream);
        } catch (IOException e) {
            System.out.println("Exception in SAVETRAINS() :");
            e.printStackTrace();
        }

    }

    private static void loadAndSendTrains(String filename, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        var trains = load(filename);
        oos.writeObject(trains);
        oos.flush();

    }

    private static void save(String fileName,List<Train> trains) throws IOException{
        try(var oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(trains);
            oos.flush();
        }
    }


    private static List<Train> load(String filename) throws IOException, ClassNotFoundException {
        try(var ois = new ObjectInputStream(new FileInputStream(filename))) {
            var trainList = (List<Train>) ois.readObject();
            return trainList;
        }
    }
}
