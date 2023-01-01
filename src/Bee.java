import java.io.*;
import java.util.Random;


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

    void leavesThrough1() throws InterruptedException {
        hive.if1EntranceFree = false;
        Thread.sleep(1000);
        this.inOrOut = false;
        System.out.println("Bee " + this.ID + " leaves the beehive through entrance no 1");
        hive.if1EntranceFree = true;
    }

    void leavesThrough2() throws InterruptedException {
        hive.if2EntranceFree = false;
        Thread.sleep(1000);
        this.inOrOut = false;
        System.out.println("Bee " + this.ID + " leaves the beehive through entrance no 2");
        hive.if2EntranceFree = true;
    }

    void entersThrough1() throws InterruptedException {
        hive.if1EntranceFree = false;
        Thread.sleep(1000);
        this.inOrOut = true;
        System.out.println("Bee " + this.ID + " enters the beehive through entrance no 1");
        hive.if1EntranceFree = true;
    }

    void entersThrough2() throws InterruptedException {
        hive.if2EntranceFree = false;
        Thread.sleep(1000);
        this.inOrOut = true;
        System.out.println("Bee " + this.ID + " enters the beehive through entrance no 2");
        hive.if2EntranceFree = true;
    }


    public void run() {
        try {
            while (true) {
                if (this.ifIn()) {
                    //in beehive
                    Random r = new Random();
                    int minInTime = 1000;
                    int maxInTime = 5000;
                    int inTime = r.nextInt(maxInTime - minInTime) + minInTime;
                    Thread.sleep(inTime);
                    System.out.println("Bee " + this.ID + " comes near entrance 1");
                    if (this.hive.getIf1EntranceFree()) {

                        synchronized (this) {
                            leavesThrough1();
                        }
                    } else {
                        System.out.println("Bee " + this.ID + " comes near entrance 2");
                        if (this.hive.getIf2EntranceFree()) {
                            synchronized (this) {
                                leavesThrough2();
                            }

                        } else {
                            Random rand = new Random();
                            Boolean whereWait = rand.nextBoolean();
                            if (whereWait) {
                                System.out.println("Bee " + this.ID + " waits near entrance 2");
                                float timeBefore = System.nanoTime();
                                synchronized (this) {
                                    leavesThrough2();
                                }
                                SumWaitingTime += System.nanoTime() - timeBefore;
                            } else {
                                System.out.println("Bee " + this.ID + " waits near entrance 1");
                                float timeBefore = System.nanoTime();
                                synchronized (this) {
                                    leavesThrough1();
                                }
                                SumWaitingTime += System.nanoTime() - timeBefore;
                            }
                        }


                    }

                } else {
                    //outside the beehive
                    Random r = new Random();
                    int minOutTime = 5000;
                    int maxOutTime = 10000;
                    int OutTime = r.nextInt(maxOutTime - minOutTime) + minOutTime;
                    Thread.sleep(OutTime);
                    System.out.println("Bee " + this.ID + " comes near entrance 1");
                    if (this.hive.getIf1EntranceFree()) {
                        synchronized (this) {
                            entersThrough1();
                        }
                    } else {
                        System.out.println("Bee " + this.ID + " comes near entrance 2");
                        if (this.hive.getIf2EntranceFree()) {
                            synchronized (this) {
                               entersThrough2();
                            }
                        } else {
                            Random rand = new Random();
                            Boolean whereWait = rand.nextBoolean();
                            if (whereWait) {
                                System.out.println("Bee " + this.ID + " waits near entrance 2");
                                float timeBefore = System.nanoTime();
                                synchronized (this) {
                                    entersThrough2();
                                }
                                SumWaitingTime += System.nanoTime() - timeBefore;
                            } else {
                                System.out.println("Bee " + this.ID + " waits near entrance 1");
                                float timeBefore = System.nanoTime();
                                synchronized (this) {
                                   entersThrough1();
                                }
                                SumWaitingTime += System.nanoTime() - timeBefore;
                            }
                        }
                    }
                }
                numOfFlights++;
            }
        } catch (InterruptedException e) {
            String results = "Bee " + ID + " number of flights: " + numOfFlights + " average waiting time[s]: " + SumWaitingTime / numOfFlights / 1_000_000_000;
            System.out.println(results);
            resultsArr.saveToList(results);
            if (ID == 0) {
                try {
                    Thread.sleep(5000);
                    resultsArr.saveToFile(fileName);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Bee " + this.ID + " Sleep exception");
        }

    }


}
