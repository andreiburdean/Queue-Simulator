package Participants;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private AtomicInteger totalWaitingTime;
    private AtomicInteger totalServiceTime;
    private int ID;

    public Queue(int ID) {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.totalWaitingTime = new AtomicInteger(0);
        this.totalServiceTime = new AtomicInteger(0);
        this.ID = ID;
    }

    public void addClient(Client client) throws InterruptedException {
        this.clients.put(client);
        waitingPeriod.addAndGet(client.getServiceTime());
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public AtomicInteger getTotalWaitindTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitindTime(int time) {
        this.totalWaitingTime.addAndGet(time);
    }

    public AtomicInteger getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(int serviceTime) {
        this.totalServiceTime.addAndGet(serviceTime);
    }

    @Override
    public void run() {
        while (true) {
            Client client = new Client();
            try {
                client = clients.element();
            } catch (NoSuchElementException exception) {

            }
            int n = client.getServiceTime();
            for (int i = 0; i < n; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingPeriod.decrementAndGet();
                client.setServiceTime(client.getServiceTime() - 1);
            }
        }
    }

    @Override
    public String toString() {
        String clientsId = "";
        for (int i = 0; i < clients.size(); i++) {
            Client client = new Client();
            try {
                client = clients.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clientsId += client.getId() + " ";
            clients.add(client);
        }
        return "Queue " + ID + " : " + clientsId + "\n";
    }
}
