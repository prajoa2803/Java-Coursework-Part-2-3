import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FoodQueue A = new FoodQueue(2);
        FoodQueue B = new FoodQueue(3);
        FoodQueue C = new FoodQueue(5);
        Scanner scan = new Scanner(System.in);
        int burgerStock = 50;
        int mainLooping = 1;
        System.out.print("Enter the Maximum Number of Customers can be added to the Waiting List: ");
        int max = scan.nextInt();
        waitingQueue wait = new waitingQueue(max);
        while (mainLooping == 1) {

            menu();//Prompt Menu

            System.out.print("Enter a option from the above menu: ");
            String option = scan.next();
            option = option.toUpperCase();

            //To Perform task that user require
            switch (option) {
                //To View all queues' status
                case ("100"):
                case ("VFQ"):
                    slotDiagram(A,B,C);
                    backToMenu();
                    break;
                //To View empty slots in queue
                case ("101"):
                case ("VEQ"):
                    emptySlotDiagram(A,B,C);
                    backToMenu();
                    break;
                //To add a customer to a queue
                case ("102"):
                case ("ACQ"):
                    addCustomerToQueue(A,B,C,wait);
                    break;
                //To remove a customer from specific slot
                case ("103"):
                case ("RCQ"):
                    slotDiagram(A,B,C);
                    removeCustomerFromQueue(A,B,C,wait);
                    break;
                //To remove served customer from the slot
                case ("104"):
                case ("PCQ"):
                    slotDiagram(A,B,C);
                    burgerStock = removeServedCustomerFromQueue(A,B,C,burgerStock,wait);
                    break;
                //To sort Customers in Alphabetical order
                case ("105"):
                case ("VCS"):
                    customerAlphabeticalOrder(A,B,C);
                    backToMenu();
                    break;
                //To Store Data into text file
                case("106"):
                case("SPD"):
                    storeData(A,B,C,burgerStock);
                    System.out.println("Data has been Added Successfully");
                    break;
                //To retrieve data from text file and update the program with previously saved data
                case ("107"):
                case("LPD"):
                    restoreBackUp(A,B,C,burgerStock);
                    break;

                //To view remaining burgers in stock
                case ("108"):
                case ("STK"):
                    System.out.println(burgerStock + " burgers are remaining.");
                    backToMenu();
                    break;
                // To Add burgers to the stock
                case ("109"):
                case ("AFS"):
                    burgerStock = addBurgerStock(burgerStock);
                    break;
                    //To View Income of queue
                case ("110"):
                case("IFQ"):
                    queueIncome(A,B,C);
                    break;
                //To Exit the Main Program
                case ("999"):
                case ("EXT"):
                    mainLooping = 0;
                    break;

                default:
                    System.out.println("Enter Valid Option.");
            }
        }
    }
    public static void menu(){
        System.out.println("100 or VFQ: View all Queues.\n101 or VEQ: View all Empty Queues.\n" +
                "102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue.\n" +
                "104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order.\n" +
                "106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n" +
                "108 or STK: View Remaining burgers Stock.\n109 or AFS: Add burgers to Stock.\n110 or IFQ: View Income of Queue\n999 or EXT: Exit the Program");
    }
    public static void slotDiagram(FoodQueue A, FoodQueue B, FoodQueue C) {//To print occupied and unoccupied slots in a diagram method
        System.out.println("*******************\n*     Cashiers    *\n*******************");
        System.out.println("   A     B     C");
        System.out.println("1  " + A.slotStatus(0) + "     " + B.slotStatus(0) + "     " + C.slotStatus(0));
        System.out.println("2  " + A.slotStatus(1) + "     " + B.slotStatus(1) + "     " + C.slotStatus(1));
        System.out.println("3        " + B.slotStatus(2) + "     " + C.slotStatus(2));
        System.out.println("4              " + C.slotStatus(3));
        System.out.println("5              " + C.slotStatus(4));
    }
    public static void emptySlotDiagram(FoodQueue A, FoodQueue B, FoodQueue C) {//To print unoccupied slots in a diagram method
        System.out.println("*******************\n*     Cashiers    *\n*******************");
        System.out.println("   A     B     C");
        System.out.println("1  " + A.emptySlots(0) + "     " + B.emptySlots(0) + "     " + C.emptySlots(0));
        System.out.println("2  " + A.emptySlots(1) + "     " + B.emptySlots(1) + "     " + C.emptySlots(1));
        System.out.println("3        " + B.emptySlots(2) + "     " + C.emptySlots(2));
        System.out.println("4              " + C.emptySlots(3));
        System.out.println("5              " + C.emptySlots(4));
    }
    public static String shortQueue(FoodQueue A, FoodQueue B, FoodQueue C){//To Find Shortest queue of all
        String shortest = "";
        int aLength;
        int bLength;
        int cLength;
        if (A.getQueueLength() < A.getTotalSlots()){
            aLength = A.getQueueLength();
        }
        else{
            aLength = 10;//Given a random number as the length will not exceed 5
        }
        if (B.getQueueLength() < B.getTotalSlots()){
            bLength = B.getQueueLength();
        }
        else{
            bLength = 11;//Given a random number as the length will not exceed 5
        }
        if (C.getQueueLength() < C.getTotalSlots()){
            cLength = C.getQueueLength();
        }
        else{
            cLength = 12;//Given a random number as the length will not exceed 5
        }
        if(!(aLength == 10) || !(bLength == 11) || !(cLength == 12)) {
            if (aLength > bLength) {
                if (bLength > cLength) {
                    shortest = "C";
                } else {
                    shortest = "B";
                }
            } else {
                if (aLength > cLength) {
                    shortest = "C";
                } else {
                    shortest = "A";
                }
            }
        }else{
            shortest = "N";
        }
        return shortest;
    }
    public static void addCustomerToQueue(FoodQueue A, FoodQueue B, FoodQueue C, waitingQueue waiting){//Method to Add customers to the shortest queue
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                String shortest = shortQueue(A, B, C);
                System.out.println("Enter Customer Details or Enter 1 to Return to Main Menu");
                System.out.print("First Name: ");
                String firstName = scan.next();
                if (firstName.equals("1")) {
                    break;
                } else {
                firstName = firstName.toUpperCase();
                System.out.print("Second Name: ");
                String secondName = scan.next();
                secondName = secondName.toUpperCase();
                System.out.print("Number of Burgers: ");
                int burgersRequired = scan.nextInt();

                    if (shortest.equals("A")) {
                        A.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        System.out.println(firstName + " " + secondName + " has been Added.");
                    } else if (shortest.equals("B")) {
                        B.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        System.out.println(firstName + " " + secondName + " has been Added.");
                    } else if (shortest.equals("C")) {
                        C.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        System.out.println(firstName + " " + secondName + " has been Added.");


                    } else {
                        waiting.enqueue(firstName,secondName,burgersRequired);
                        System.out.println("Cashier Queues are full, Added to the waiting queue");
                    }

                    break;
                }
            }
            catch(Exception e){
                    System.out.println("Enter Valid Details");
                }
            }
        }
    public static void removeCustomerFromQueue(FoodQueue A, FoodQueue B, FoodQueue C, waitingQueue waiting) {//To remove customer from a specific slot
        while (true) {
            try {
                if (A.getQueueLength() == 0 && B.getQueueLength() == 0 && C.getQueueLength() == 0) {
                    System.out.println("All Slots are Unoccupied");
                    break;
                } else {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter the Queue customer needed to be removed or Enter 1 to Return to Main Menu (eg :- A, B, C): ");
                    String queueName = scan.next();
                    queueName = queueName.toUpperCase();
                    System.out.println("Enter the Row customer needed to be removed (eg :- 1 - 5): ");
                    int row = scan.nextInt();
                    if (queueName.equals("A")) {
                        System.out.println(A.getCustomerName(row - 1) + " has been removed");
                        A.removeCustomer(row - 1);
                        try{
                            Customer customer = waiting.dequeue();
                            A.addCustomer(customer);
                            System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                        }catch (IllegalStateException e){
                            System.out.println("Waiting Queue is Empty");
                        }
                        break;
                    } else if (queueName.equals("B")) {
                        System.out.println(B.getCustomerName(row - 1) + " has been removed");
                        B.removeCustomer(row - 1);
                        try{
                            Customer customer = waiting.dequeue();
                            B.addCustomer(customer);
                            System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                        }catch (IllegalStateException e){
                            System.out.println("Waiting Queue is Empty");
                        }
                        break;
                    } else if (queueName.equals("C")) {
                        System.out.println(C.getCustomerName(row - 1) + " has been removed");
                        C.removeCustomer(row - 1);
                        try{
                            Customer customer = waiting.dequeue();
                            C.addCustomer(customer);
                            System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                        }catch (IllegalStateException e){
                            System.out.println("Waiting Queue is Empty");
                        }
                        break;
                    } else if (queueName.equals("1")) {
                        break;
                    } else{
                        System.out.println("Enter Valid Slot");
                    }
                }
            }
            catch(Exception e){
                    System.out.println("Enter Valid Slot");
            }
        }
    }
    public static int removeServedCustomerFromQueue(FoodQueue A, FoodQueue B, FoodQueue C, int burgerStock, waitingQueue waiting) {//method to remove served customer
        while (true) {
            try {
                if (A.getQueueLength() == 0 && B.getQueueLength() == 0 && C.getQueueLength() == 0) {
                    System.out.println("All Slots are Unoccupied");
                    break;
                } else {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter the Queue customer needed to be removed or Enter 1 to Return to Main Menu(eg :- A, B, C): ");
                    String queueName = scan.next();
                    queueName = queueName.toUpperCase();
                    if (queueName.equals("A")) {
                        if(burgerStock >= A.burgersSold()) {
                            System.out.println(A.getCustomerName(0) + " has been served and removed");
                            A.addIncome(A.burgersSold());
                            burgerStock = burgerStock - A.burgersSold();
                            A.removeCustomer(0);
                            try{
                                Customer customer = waiting.dequeue();
                                A.addCustomer(customer);
                                System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                            }catch (IllegalStateException e){
                                System.out.println("Waiting Queue is Empty");
                            }
                        }else{
                            System.out.println("Not enough Burgers");
                        }
                        break;
                    } else if (queueName.equals("B")) {
                        if(burgerStock >= B.burgersSold()) {
                            System.out.println(B.getCustomerName(0) + " has been served and removed");
                            B.addIncome(B.burgersSold());
                            burgerStock = burgerStock - B.burgersSold();
                            B.removeCustomer(0);
                            try{
                                Customer customer = waiting.dequeue();
                                B.addCustomer(customer);
                                System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                            }catch (IllegalStateException e){
                                System.out.println("Waiting Queue is Empty");
                            }
                        }else{
                            System.out.println("Not enough Burgers");
                        }
                        break;
                    } else if (queueName.equals("C")) {
                        if(burgerStock >= C.burgersSold()) {
                            System.out.println(C.getCustomerName(0) + " has been served and removed");
                            C.addIncome(C.burgersSold());
                            burgerStock = burgerStock - C.burgersSold();
                            C.removeCustomer(0);
                            try{
                                Customer customer = waiting.dequeue();
                                C.addCustomer(customer);
                                System.out.println("Customer from Waiting Queue has been added to the Cashier Queue");
                            }catch (IllegalStateException e){
                                System.out.println("Waiting Queue is Empty");
                            }
                        }else{
                            System.out.println("Not enough Burgers");
                        }
                        break;
                    }else if(queueName.equals("1")){
                        break;
                    }
                    else {
                        System.out.println("Enter Valid Slot");
                    }

                }
            }catch (Exception e){
                System.out.println("Enter Valid Slot");
            }
        }
        if (burgerStock <=  10){
            System.out.println("Only "+ burgerStock +" burgers left");
        }
        return burgerStock;
    }
    public static void customerAlphabeticalOrder(FoodQueue A, FoodQueue B, FoodQueue C) {//To sort customers' name in Alphabetical order
        String customerNameAlphabeticalArr[] = new String[10];
        customerNameAlphabeticalArr [0]= A.getCustomerName(0);
        customerNameAlphabeticalArr [1]= A.getCustomerName(1);
        customerNameAlphabeticalArr [2]= B.getCustomerName(0);
        customerNameAlphabeticalArr [3]= B.getCustomerName(1);
        customerNameAlphabeticalArr [4]= B.getCustomerName(2);
        customerNameAlphabeticalArr [5]= C.getCustomerName(0);
        customerNameAlphabeticalArr [6]= C.getCustomerName(1);
        customerNameAlphabeticalArr [7]= C.getCustomerName(2);
        customerNameAlphabeticalArr [8]= C.getCustomerName(3);
        customerNameAlphabeticalArr [9]= C.getCustomerName(4);

        for(int a = 0; a < customerNameAlphabeticalArr.length-1;a++){
            int minIndex = a;
            for (int b = a + 1; b < customerNameAlphabeticalArr.length; b++) {
                String firstInOrder = compareStrings(customerNameAlphabeticalArr[b], customerNameAlphabeticalArr[minIndex]);
                if (customerNameAlphabeticalArr[b].equals(firstInOrder)) {
                    minIndex = b;
                }
            }
                    String temp = customerNameAlphabeticalArr[a];
                    customerNameAlphabeticalArr[a] = customerNameAlphabeticalArr[minIndex];
                    customerNameAlphabeticalArr[minIndex] = temp;


        }

        for(int k = 0; k < customerNameAlphabeticalArr.length; k++){
            if (!customerNameAlphabeticalArr[k].equals("")){
                System.out.println(customerNameAlphabeticalArr[k]);
            }
        }
    }
    public static String compareStrings(String str1, String str2){//To compare two strings and find the Alphabetical order of them

        String firstInOrder = "";
        int i = 0;
        try{
            while(true) {
                if (str1.charAt(i) == str2.charAt(i)) {
                    i++;
                } else if (str1.charAt(i) > str2.charAt(i)) {
                    firstInOrder = str2;
                    break;
                } else {
                    firstInOrder = str1;
                    break;
                }
            }
        }
        catch(Exception e){
            firstInOrder = str1;
        }
        return firstInOrder;
    }
    public static void storeData(FoodQueue A, FoodQueue B, FoodQueue C, int burgerStock){//to store current data to a text file
        File file = new File("storingFile.txt");

        while(true) {
            if (file.exists() ) {
                try {
                    FileWriter myWriter = new FileWriter("storingFile.txt");
                    for (int i = 0; i < 2; i++){
                        myWriter.write(A.getCustomerFirstName(i)+"\n"+A.getCustomerSecondName(i)+"\n"+A.getCustomerBurgersRequired(i)+"\n");
                    }
                    myWriter.write(A.getIncome()+"\n");

                    for (int i = 0; i < 3; i++){
                        myWriter.write(B.getCustomerFirstName(i)+"\n"+B.getCustomerSecondName(i)+"\n"+B.getCustomerBurgersRequired(i)+"\n");
                    }
                    myWriter.write(B.getIncome()+"\n");
                    for (int i = 0; i < 5; i++){
                        myWriter.write(C.getCustomerFirstName(i)+"\n"+C.getCustomerSecondName(i)+"\n"+C.getCustomerBurgersRequired(i)+"\n");
                    }
                    myWriter.write(C.getIncome()+"\n");
                    myWriter.write(burgerStock+"\n");
                    myWriter.close();

                    break;
                }
                catch (IOException e){
                    System.out.println("An error occurred.");
                }

            } else {
                try {
                    if(file.createNewFile()) {
                            System.out.println();
                     }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }


            }
        }
    }

    public static int restoreBackUp(FoodQueue A, FoodQueue B, FoodQueue C, int currentburgerStock) {
        int burgerStock = 0;
        if (A.getQueueLength() == 0 && B.getQueueLength() == 0 && C.getQueueLength() == 0) {
            System.out.print("Enter 1 to restore backed-up data or enter any key to go back to the menu: ");
            Scanner scan = new Scanner(System.in);
            String execute = scan.next();
            if (execute.equals("1")) {
                try {
                    File readFile = new File("storingFile.txt");
                    Scanner file_reader = new Scanner(readFile);

                    for (int i = 0; i < 2; i++) {
                        String firstName = file_reader.nextLine();
                        String secondName = file_reader.nextLine();
                        int burgersRequired = Integer.parseInt(file_reader.nextLine());
                        if (!(burgersRequired == 0)) {
                            A.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        }
                    }

                    A.setIncome(Integer.parseInt(file_reader.nextLine()));

                    for (int i = 0; i < 3; i++) {
                        String firstName = file_reader.nextLine();
                        String secondName = file_reader.nextLine();
                        int burgersRequired = Integer.parseInt(file_reader.nextLine());
                        if (!(burgersRequired == 0)) {
                            B.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        }

                    }
                    B.setIncome(Integer.parseInt(file_reader.nextLine()));

                    for (int i = 0; i < 5; i++) {
                        String firstName = file_reader.nextLine();
                        String secondName = file_reader.nextLine();
                        int burgersRequired = Integer.parseInt(file_reader.nextLine());
                        if (!(burgersRequired == 0)) {
                            C.addCustomer(new Customer(firstName, secondName, burgersRequired));
                        }

                    }
                    C.setIncome(Integer.parseInt(file_reader.nextLine()));
                    burgerStock = (Integer.parseInt(file_reader.nextLine()));
                    file_reader.close();

                    System.out.println("The data from the file has been updated.");

                } catch (
                        IOException e) {
                    System.out.println("The file was not found.");
                }
            }
        } else {
            System.out.println("Current Cashier Queues are not empty");
            burgerStock = currentburgerStock;
        }
        return burgerStock;
    }

    public static int addBurgerStock(int stock){//To add burgers to the stock
        Scanner scan = new Scanner(System.in);
        int add = 50 - stock;
        while(true) {
            System.out.println("Only " + add + " burgers can be added");

            try {
                System.out.print("Enter the number of burgers needed to be added or Enter -1 to return back to Main menu: ");
                int addedBurger = scan.nextInt();
                if (addedBurger <= add && addedBurger > 0 ) {
                    stock = stock + addedBurger;
                    System.out.println(stock + " burgers are remaining");
                    break;
                }
                if (addedBurger == -1){
                    break;
                }
                else {
                    System.out.println("Exceeding the maximum limit of Burger Stock");
                }

            }
            catch (Exception e){
                System.out.println("Invalid Entry, Enter a number.");
                scan.next();
            }
        }
        return stock;
    }
    public static void queueIncome(FoodQueue A, FoodQueue B, FoodQueue C){//Method to print income by each queue
        System.out.println("Income from Queue A is "+A.getIncome());
        System.out.println("Income from Queue B is "+B.getIncome());
        System.out.println("Income from Queue C is "+C.getIncome());
    }
    public static void backToMenu(){//To get Back to main menu
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter any Character to Go back to Main Menu: ");
        char character = scan.nextLine().charAt(0);
        System.out.println("");

    }
}