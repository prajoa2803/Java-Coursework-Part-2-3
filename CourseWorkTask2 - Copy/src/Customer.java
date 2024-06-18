public class Customer {//Assign the attributes to Customer class
    private String firstName;
    private String secondName;
    private int burgersRequired;
    public Customer(String firstName, String secondName,int burgersRequired){//Constructor to Get Customer Details
        this.firstName = firstName;
        this.secondName = secondName;
        this.burgersRequired = burgersRequired;
    }
    public String getFullName(){//Getter method to get Customer's Full Name
        return (firstName + " " + secondName);
    }

    public String getFirstName() {//Getter method to get Customer's First Name
        return firstName;
    }

    public String getSecondName() {//Getter method to get Customer's Second Name
        return secondName;
    }

    public int getBurgersRequired() {//Getter method to get required burgers by Customer
        return burgersRequired;
    }
}
