public class waitingQueue {
    public Customer[] customersWaiting;
    private int front;
    private int rear;
    private int size;

    public waitingQueue(int customerCount){//Constructor to create new Waiting Queue
        customersWaiting = new Customer[customerCount];
        front = -1;
        rear = -1;
        size = customerCount;
    }
    public void enqueue(String firstName, String secondName, int requiredBurger){//Method to add customers to the
        if ((front == 0 && rear == size - 1) || (front == rear + 1)){
            System.out.println("Waiting Queue is Full.");
        }
        else{
            if (front == -1){
                front = 0;
            }
            rear = (rear + 1) % this.size;
            customersWaiting[rear] = new Customer(firstName,secondName,requiredBurger);
        }
    }
    public Customer dequeue() {
        if (front == -1) {
            throw new IllegalStateException("Waiting Queue is empty.");
        }
        Customer customer = customersWaiting[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % this.size;
        }
        System.out.println("Customer has been added to the Cashier Queue");
        return customer;
    }



}
