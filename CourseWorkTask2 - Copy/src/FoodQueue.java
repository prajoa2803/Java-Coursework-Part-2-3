import java.util.ArrayList;

public class FoodQueue {// Assign the attributes for FoodQueue class

    private int totalSlots;
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    private int income;

    public FoodQueue(int totalSlots){//Constructor to get Queue details
        this.totalSlots = totalSlots;
        this.customers = new ArrayList<>();
        this.income = income;
    }

    public void setIncome(int income) {//Method to set income restored from file
        this.income = income;
    }

    public String getCustomerFirstName(int index){//Method to get first name of a customer in a specific slot
        try{
            return this.customers.get(index).getFirstName();
        }catch (Exception e){
            return "";
        }
    }
    public String getCustomerSecondName(int index){//Method to get second name of a customer in a specific slot
        try{
            return this.customers.get(index).getSecondName();
        }catch (Exception e){
            return "";
        }
    }
    public int getCustomerBurgersRequired(int index){//Method to get required burgers by a customer in a specific slot
        try{
            return this.customers.get(index).getBurgersRequired();
        }catch (Exception e){
            return 0;
        }
    }

    public int getIncome() {//Method to get Income of each Queue
        return this.income;
    }

    public int getTotalSlots() {//Getter to get the total slots available in each queue
        return this.totalSlots;
    }
    public int getQueueLength(){//Method to get the size of the arraylist at current situation
        return this.customers.size();
    }
    public void addCustomer(Customer customer){//Add customer to the particular Queue's arraylist
        this.customers.add(customer);
    }
    public String getCustomerName(int index){//Get the  Full Name of a customer in specific index
       try{
           return this.customers.get(index).getFullName();
       }catch (Exception e){
           return "";
       }
    }
    public int burgersSold(){//Get the number of burgers sold for a customer who has been served
        return this.customers.get(0).getBurgersRequired();
    }
    public void removeCustomer(int index){//Remove a served or unserved customer from a particular slot
        this.customers.remove(index);
    }

    public String slotStatus(int index){//To get status of the queues
        String value = "";
        try {
            if (!this.customers.get(index).equals(null)) {
                value =  "O";
            }
        }
        catch (Exception e){
            value =  "X";
        }
        return value;
    }
    public String emptySlots(int index){//Method to filter empty slots available
        String value = "";
        try {
            if (!this.customers.get(index).equals(null)) {
                value =  " ";
            }
        }
        catch (Exception e){
            value =  "X";
        }
        return value;
    }

    public void addIncome(int burgersSold){//Method to add the total income for each queue
        this.income += burgersSold*650;
    }


}
