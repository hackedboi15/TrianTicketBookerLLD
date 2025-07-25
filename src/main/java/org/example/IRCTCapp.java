package org.example;

import java.util.*;

public class IRCTCapp {
    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new IRCTCapp().start();

    }

    public void start()
    {
        while (true){
            System.out.println("  ---------WELCOME TO iRCTC App  -------------");
            if (!userService.isLoggin()){
                System.out.println("1 -> Register");
                System.out.println("2 -> Login");
                System.out.println("3-> Exit");
                System.out.println("Enter choice");

                int choice= sc.nextInt();

                switch (choice)
                {
                    case 1 -> register();
                    case 2-> login();
                    case 3-> exitApp();
                    default -> System.out.println("Invalid choice");
                }
            }
            else
            {
                showUserMenu();
            }

        }
    }

    public void register()
    {
        System.out.println("Enter usename");
        String username= sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        System.out.println("Enter Full name");
        sc.nextLine();
        String fullName = sc.nextLine();
        System.out.println("Enter Contact");
        String contact = sc.next();

        userService.registerUser(username,password,fullName,contact);

    }
    public void login(){
        System.out.println("Enter username ");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        userService.loginUser(username,password);
    }
    private void showUserMenu()
    {
        while (userService.isLoggin())
        {
            System.out.println("---USer menu ----- ");
            System.out.println("1 -> SearchTrains");
            System.out.println("2 -> BookTicket");
            System.out.println("3-> View my tickets");
            System.out.println("4-> Cancel tickets");
            System.out.println("5-> View ALl trains");
            System.out.println("6-> Logout");
            System.out.println("Enter choice");
            int choice =sc.nextInt();

            switch (choice)
            {
                case 1->searchTrain();
                case 2->bookTicket();
                case 3->viewMyTickets();
                case 4->cancelTicket();
                case 5->bookingService.listAllTrains();
                case 6->userService.logOut();
                default -> System.out.println("Invalid choice");
            }

        }
    }

    private void searchTrain(){
        System.out.println("Enter Source station");
        String source = sc.next();
        System.out.println("Enter Destinatiom station");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty())
        {
            System.out.println("No train found bw"+ source+ "and " + destination);
        }
        System.out.println("Train found");
        for (Train train:trains){
            System.out.println(train);
        }
        System.out.println("Do u want to book ticket? (y/n)");
        String choice = sc.next();
        if(choice.equalsIgnoreCase("y")){
            System.out.println("Enter trainId to book");
            int trainId = sc.nextInt();
            System.out.println("Enter no of seats to book");
            int seats = sc.nextInt();

            Ticket ticket = bookingService.bookTicket(userService.getCurrUser(),trainId,seats);
            if (ticket!=null)
            {
                System.out.println("Booking successful");
                System.out.println(ticket);
            }
        }
        else {
            System.out.println("retutning to user menu.....");
        }

    }

    private void bookTicket()
    {
        System.out.println("Enter Source station");
        String source = sc.next();
        System.out.println("Enter Destinatiom station");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty())
        {
            System.out.println("No train "+ source+ "and " + destination);
            return;
        }
        System.out.println("Train Available for booking");
        for (Train train:trains){
            System.out.println(train);
        }
        System.out.println("Enter trainId to book");
        int trainId = sc.nextInt();
        System.out.println("Enter no of seats to book");
        int seats = sc.nextInt();

        Ticket ticket = bookingService.bookTicket(userService.getCurrUser(),trainId,seats);
        if (ticket!=null)
        {
            System.out.println("Booking successful");
            System.out.println(ticket);
        }


    }
    private void viewMyTickets(){
        List<Ticket> ticketByUser = bookingService.getTicketByUser(userService.getCurrUser());
        if (ticketByUser.isEmpty())
        {
            System.out.println("No tkt boked");

        }else
        {
            System.out.println("Your tkts");
            for (Ticket ticket:ticketByUser){
                System.out.println((ticket));
            }
        }

    }

    private void cancelTicket(){
        System.out.println("Enter ticketId to cancel");
        int ticketId = sc.nextInt();
        bookingService.cancelTicket(ticketId,userService.getCurrUser());
    }

    private void exitApp(){
        System.out.println("Thanks for using IRCTC app");
        System.exit(0);
    }

}
