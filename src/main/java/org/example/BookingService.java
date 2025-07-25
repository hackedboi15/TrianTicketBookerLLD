package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList= new ArrayList<>();

    public BookingService(){
        trainList.add(new Train(101,"Super","gmo","patna",200));
        trainList.add(new Train(102,"Rajdhani","delhi","patna",200));
        trainList.add(new Train(103,"Vande bharat","blr","bom",200));
        trainList.add(new Train(104,"ranchi-patna SF","ranchi","patna",400));
        trainList.add(new Train(105,"MEMU","blr","bom",2000));
    }

    public List<Train> searchTrain(String source,String destination){
        List<Train> res = new ArrayList<>();
        for(Train train:trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination))
            res.add(train);
        }
        return res;
    }

    public Ticket bookTicket(User user,int trainId,int seatCount){
        for (Train train:trainList){
            if (train.getTrainId() == trainId)
            {
                if (train.bookSeats(seatCount))
                {
                    Ticket ticket = new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return  ticket;
                }else
                {
                    System.out.println("Not exttra seat avialable");
                    return null;
                }
            }
        }
        System.out.println("Train id not found");
        return null;
    }

    public List<Ticket> getTicketByUser(User user){
        List<Ticket> res = new ArrayList<>();
        for (Ticket tkt:ticketList){
            if (tkt.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
            {
                res.add(tkt);
            }

        }
        return res;
    }

    public boolean cancelTicket(int ticketId,User user){
        Iterator <Ticket> iterator = ticketList.listIterator();
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
            if (ticket.getTicketId() == ticketId &&
                    ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {

                Train train = ticket.getTrain();
                train.cancelTicket(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticket : "+ ticketId+"Cancled successfully");
            }
        }
        System.out.println("ऊगमकाू लदू िदहल्");
        return false;
    }

    public void listAllTrains(){
        System.out.println("List of all traINS");{
            for (Train train: trainList){
                System.out.println(train);
            }
        }
    }
}
