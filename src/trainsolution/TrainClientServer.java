package trainsolution;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static trainsolution.Train.*;


public class TrainClientServer {


    public static enum ServerAction {LOAD, SAVE};

    static int PORT = ThreadLocalRandom.current().nextInt(2000, 20000);

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        new Thread(() -> {
            try {
                startServer(PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(10);
        runClient("trainfile.dat",3,20,10,  10, 12);
        runClient("trainfile.dat",3,20,10,  6, 12);
    }

    public static void startServer(int PORT) throws IOException {
        try (
                var ss = new ServerSocket(PORT);
        ){
            while(true) {
                var s = ss.accept();
                handleClient(s);
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
            else if (serverAction == ServerAction.LOAD) {
                var trains = loadAndSendTrains(fileName);
                oos.writeObject(trains);
                oos.flush();
            }
            else {
                System.out.println("Unknown OPERATION");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception in Handle Client");
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

    private static List<Train> loadAndSendTrains(String filename) throws IOException, ClassNotFoundException {
        try(var ois = new ObjectInputStream(new FileInputStream(filename))) {
            var trainList = (List<Train>) ois.readObject();
            return trainList;
        }
    }

    private static void save(String fileName,List<Train> trains) throws IOException{
        try(var oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(trains);
            oos.flush();
        }
    }



    private static void runClient(String filename, int startHour, int endHour, int maxTrains, int minHour, int maxHour
    ) throws IOException, ClassNotFoundException {
        List<String> trains =makeHourlies.apply(startHour, endHour).collect(Collectors.toList());

            sendToServerSave(ServerAction.SAVE, filename, maxTrains, minHour, maxHour, trains);
            var trainList = sendToServerLoad(ServerAction.LOAD, filename);
            for (Train train : trainList) {
                System.out.println("Received ~ " + train);
            }
    }

    private static void sendToServerSave(ServerAction serverAction, String filename, int maxTrains, int minHour,
                                         int maxHour, List<String> trains) throws IOException {
        try(
                Socket s = new Socket("127.0.0.1",PORT);
                var oos = new ObjectOutputStream(s.getOutputStream())){
            oos.writeObject(serverAction);
            oos.writeObject(filename);
            oos.write(maxTrains);
            oos.write(minHour);
            oos.write(maxHour);
            oos.writeObject(trains);
        }
    }

    private static List<Train> sendToServerLoad( ServerAction serverAction, String filename) throws IOException, ClassNotFoundException {
        try (
                Socket s = new Socket("localhost", PORT);
                var oos = new ObjectOutputStream(s.getOutputStream());
                var ois = new ObjectInputStream(s.getInputStream());
        ) {
            oos.writeObject(serverAction);
            oos.writeObject(filename);
            var trains = (List<Train>) ois.readObject();
            return trains;
        }
    }

}
