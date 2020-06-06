
package airline;

import java.util.Scanner;

public class Airline {
    static Scanner scanner = new Scanner(System.in);
    data d = new data();
    void availableSeats(int flightNumber) {
        System.out.println(d.getAvailableSeats(flightNumber));
    }
    void bookFlight() {
        int flightNumber = 0;
        System.out.println("\nEnter the Flight Number(e.g 1,2,etc.) : ");
        flightNumber = scanner.nextInt();
        String username = "";
        System.out.println("\nEnter the username : ");
        username = scanner.next();
        System.out.println(d.bookTicket(flightNumber, username));
        
    }
    void scheduleFlight(){
        int flightNumber = 0;
        System.out.println("\nEnter the new Flight Number : ");
        flightNumber = scanner.nextInt();
        int numberOfSeats = 0;
        System.out.println("\nEnter the number of seats : ");
        numberOfSeats = scanner.nextInt();
        System.out.println(d.addFlight(flightNumber, numberOfSeats));
    }
    public static void main(String args[]) {
        int choice = 0;
        Airline airline = new Airline();
        do{
            System.out.println("\n\n----------MENU-----------");
            System.out.println("1. Check Available Seats");
            System.out.println("2. Book a Flight");
            System.out.println("3. Schedule a Flight");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice (e.g. : 1,2,3.) : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //Available Seats
                    int flightNumber = 0;
                    System.out.println("\nEnter the Flight Number(e.g 1,2,etc.) : ");
                    flightNumber = scanner.nextInt();
                    airline.availableSeats(flightNumber);
                    break;
                case 2:
                    //Book Flight
                    airline.bookFlight();
                    break;
                case 3:
                    //Schedule Flight
                    airline.scheduleFlight();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while(choice != 4);
    }
}
