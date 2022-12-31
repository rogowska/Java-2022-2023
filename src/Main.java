
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("How many bees:");
        Scanner scanner = new Scanner(System.in);
        int beesNum = scanner.nextInt();
        System.out.println("The output file name:");
        String fileName = scanner.next();
        Random rand = new Random();
        Beehive hive = new Beehive();
        ToFile resultsArr = new ToFile();
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < beesNum; i++) {
            es.execute(new Thread((new Bee(fileName, resultsArr, hive, i, rand.nextBoolean()))));
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        es.shutdownNow();

    }
}
