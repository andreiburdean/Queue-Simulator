package Simulation;

import Participants.Client;
import Participants.Queue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private int nrQueues;

    public Scheduler(int nrQueues) {
        this.nrQueues = nrQueues;
        queues = new ArrayList<Queue>();
        for (int i = 0; i < nrQueues; i++) {
            Queue queue = createQueue(i + 1);
            queues.add(queue);
            Thread thread = new Thread(queue);
            thread.start();
        }
    }

    private Queue createQueue(int queueNumber) {
        return new Queue(queueNumber);
    }

    public synchronized void dispatchClient(Client client) throws InterruptedException {
        Queue shortestQueue = queues.get(0);
        for (Queue queue : queues) {
            if (queue.getWaitingPeriod().get() < shortestQueue.getWaitingPeriod().get()) {
                shortestQueue = queue;
            }
        }
        shortestQueue.setTotalWaitindTime(shortestQueue.getWaitingPeriod().get());
        shortestQueue.setTotalServiceTime(client.getServiceTime());
        shortestQueue.addClient(client);
    }

    public List<Queue> getQueues() {
        return queues;
    }
}
