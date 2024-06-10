package Simulation;

import Participants.Client;
import GUI.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class SimulationManager implements Runnable {

    private GUI GUI;
    private int simulationInterval;
    private int nrOfClients;
    private int nrOfQueues;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private Scheduler scheduler;
    private List<Client> clientList;
    int numberClients;
    int peakTime;
    int totalNrClienti = 0;

    public SimulationManager(GUI GUI) {
        this.GUI = GUI;
        this.simulationInterval = Integer.parseInt(GUI.getSimIntervalTextField());
        this.nrOfClients = Integer.parseInt(GUI.getnrOfClientsTextField());
        this.numberClients = Integer.parseInt(GUI.getnrOfClientsTextField());
        this.nrOfQueues = Integer.parseInt(GUI.getnrOfQueuesTextField());
        this.minArrivalTime = Integer.parseInt(GUI.getMinArrivalTimeTextField());
        this.maxArrivalTime = Integer.parseInt(GUI.getMaxArrivalTimeTextField());
        this.minServiceTime = Integer.parseInt(GUI.getMinServiceTimeTextField());
        this.maxServiceTime = Integer.parseInt(GUI.getMaxServiceTimeTextField());
        this.scheduler = new Scheduler(nrOfQueues);
        generateClients();
    }

    public void generateClients() {
        clientList = new ArrayList<>();
        for (int i = 0; i < nrOfClients; i++) {
            int serviceTime = (int) (Math.random() * ((maxServiceTime - minServiceTime) + 1)) + minServiceTime;
            int arrivalTime = (int) (Math.random() * ((maxArrivalTime - minArrivalTime) + 1)) + minArrivalTime;
            clientList.add(new Client(i + 1, serviceTime, arrivalTime));
        }
        Collections.sort(clientList);
    }

    public boolean endSimulation(int nrOfClients) {
        boolean endSim = false;
        if (nrOfClients == 0) {
            for (int i = 0; i < nrOfQueues; i++) {
                if (scheduler.getQueues().get(i).getClients().isEmpty()) {
                    endSim = true;
                } else {
                    endSim = false;
                    break;
                }
            }
        }
        return endSim;
    }

    public void displayQueueEvolution(int timer) {
        String display;
        display = "Time: " + timer + "\n";
        for (int i = 0; i < nrOfQueues; i++) {
            display += scheduler.getQueues().get(i).toString();
        }
        GUI.textArea.setSimArea(display);
    }

    public double averageWaitingTime() {
        double average;
        int sum = 0;
        for (int i = 0; i < nrOfQueues; i++) {
            sum += scheduler.getQueues().get(i).getTotalWaitindTime().get();
        }
        average = (double) sum / numberClients;

        return average;
    }

    public int totalNrOfClients() {
        int nrClients = 0;
        for (int i = 0; i < nrOfQueues; i++) {
            nrClients += scheduler.getQueues().get(i).getClients().size();
        }
        return nrClients;
    }

    public double averageServiceTime() {
        double average;
        int sum = 0;
        for (int i = 0; i < nrOfQueues; i++) {
            sum += scheduler.getQueues().get(i).getTotalServiceTime().get();
        }
        average = (double) sum / numberClients;

        return average;
    }

    @Override
    public void run() {
        int timer = 0;
        
        try (PrintWriter writer = new PrintWriter(new File("log.txt"))) {
            for (int i = 0; i < clientList.size(); i++) {
                writer.println("( " + clientList.get(i).getId() + ", " + clientList.get(i).getServiceTime() + " , " + clientList.get(i).getArrivalTime() + " )");
            }
        } catch (IOException e) {
        }

        while (timer <= simulationInterval || endSimulation(nrOfClients)) {
            if (endSimulation(nrOfClients))
                break;
            for (int i = 0; i < nrOfClients; i++) {
                if (clientList.get(i).getArrivalTime() == timer) {
                    try {
                        scheduler.dispatchClient(clientList.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clientList.remove(clientList.get(i));
                    nrOfClients--;
                    i--;
                }
            }
            try {
                for (int i = 0; i < nrOfQueues; i++) {
                    if (scheduler.getQueues().get(i).getClients().isEmpty()!=true) {
                        if (scheduler.getQueues().get(i).getClients().element().getServiceTime() == 0) {
                            scheduler.getQueues().get(i).getClients().remove();
                        }
                    }
                }
            } catch (NoSuchElementException exception) {
            }
            int c = totalNrOfClients();
            if (c > totalNrClienti) {
                totalNrClienti = c;
                peakTime = timer;
            }
            displayQueueEvolution(timer);
            timer++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double averageWaitingTime = averageWaitingTime();
        double averageServiceTime = averageServiceTime();
        GUI.textArea.showMessage("Average waiting time = " + String.format("%.2f", averageWaitingTime) + "\nAverage service time = " + String.format("%.2f", averageServiceTime) + "\nPeak hour = " + peakTime);

    }
}
