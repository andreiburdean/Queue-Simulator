package Participants;

public class Client implements Comparable<Client> {
    private int id;
    private int serviceTime;
    private int arrivalTime;
    
    public Client(int id, int serviceTime, int arrivalTime) {
    	this.id = id;
    	this.serviceTime = serviceTime;
    	this.arrivalTime = arrivalTime;
    }

    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Client client) {
        int result = Integer.compare(this.arrivalTime, client.arrivalTime);
        if (result != 0) {
            return result;
        }
        return Integer.compare(this.serviceTime, client.serviceTime);
    }
}
