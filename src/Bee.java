import java.io.*;


public class Bee implements Runnable {

    private final int ID;
    int numOfFlights;
    float SumWaitingTime;
    boolean inOrOut; //in 1; out 0;
    private final Beehive hive;
    ToFile resultsArr;

    String fileName;

    public Bee(String fileName, ToFile resultsArr, Beehive hive, int ID, boolean inOrOut) {
        this.ID = ID;
        this.inOrOut = inOrOut;
        this.numOfFlights = 0;
        this.hive = hive;
        this.resultsArr = resultsArr;
        this.fileName = fileName;
    }

    boolean ifIn() {
        return inOrOut;
    }


    public void run() {
        try {
            while (true) {
                if (this.ifIn()) {
                    Thread.sleep(1000);
                    System.out.println("Bee " + this.ID + " comes near entrance 1");
                    if (this.hive.getIf1EntranceFree()) {

                        synchronized (hive.if1EntranceFree) {
                            hive.if1EntranceFree = false;
                            Thread.sleep(1000);
                            this.inOrOut = false;
                            System.out.println("Bee " + this.ID + " leaves the beehive through entrance no 1");
                            numOfFlights++;
                            hive.if1EntranceFree = true;
                        }
                    } else {
                        System.out.println("Bee " + this.ID + " comes near entrance 2");
                        if (this.hive.getIf2EntranceFree()) {
                            synchronized (hive.if2EntranceFree) {
                                hive.if2EntranceFree = false;
                                Thread.sleep(1000);
                                this.inOrOut = true;
                                System.out.println("Bee " + this.ID + " leaves the beehive through entrance no 2");
                                numOfFlights++;
                                hive.if2EntranceFree = false;
                            }
                        }
                        System.out.println("Bee " + this.ID + " waits near entrance 2");
                        float timeBefore = System.nanoTime();
                        synchronized (hive.if2EntranceFree) {
                            hive.if2EntranceFree = false;
                            Thread.sleep(1000);
                            this.inOrOut = true;
                            System.out.println("Bee " + this.ID + " leaves the beehive through entrance no 2");
                            numOfFlights++;
                            hive.if2EntranceFree = false;
                        }
                        SumWaitingTime += System.nanoTime() - timeBefore;

                    }

                } else {
                    Thread.sleep(5000);

                    System.out.println("Bee " + this.ID + " comes near entrance 1");
                    if (this.hive.getIf2EntranceFree()) {
                        synchronized (hive.if1EntranceFree) {
                            hive.if1EntranceFree = false;
                            Thread.sleep(1000);
                            this.inOrOut = true;
                            System.out.println("Bee " + this.ID + " enters the beehive through entrance no 1");
                            numOfFlights++;
                            hive.if1EntranceFree = false;
                        }
                    } else {
                        if (this.hive.getIf2EntranceFree()) {
                            System.out.println("Bee " + this.ID + " comes near entrance 2");
                            synchronized (hive.if1EntranceFree) {
                                hive.if2EntranceFree = false;
                                Thread.sleep(1000);
                                this.inOrOut = false;
                                System.out.println("Bee " + this.ID + " enters the beehive through entrance no 2");
                                numOfFlights++;
                                hive.if2EntranceFree = false;
                            }
                        } else {
                            System.out.println("Bee " + this.ID + " waits near entrance 2");
                            float timeBefore = System.nanoTime();
                            synchronized (hive.if1EntranceFree) {
                                hive.if2EntranceFree = false;
                                Thread.sleep(1000);
                                this.inOrOut = true;
                                System.out.println("Bee " + this.ID + " enters the beehive through entrance no 2");
                                numOfFlights++;
                                hive.if2EntranceFree = false;
                            }
                            SumWaitingTime += System.nanoTime() - timeBefore;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            String results = "Bee " + ID + " number of flights: " + numOfFlights + " average waiting time: " + SumWaitingTime / numOfFlights;
            System.out.println(results);
            resultsArr.saveToList(results);
            if(ID == 0) {
                try {
                    Thread.sleep(5000);
                    resultsArr.saveToFile(fileName);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch(Exception e)
        {
        System.out.println("Bee " + this.ID + " Sleep exception");
        }

}


}
