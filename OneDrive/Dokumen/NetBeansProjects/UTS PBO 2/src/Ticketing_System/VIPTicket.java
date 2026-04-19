package Ticketing_System;

public class VIPTicket implements Ticket {
    
    @Override
    public String getType() {
        return "VIP"; 
    }

    @Override
    public double getPrice() {
        return 200000;
    }

    @Override
    public String getBenefit() {
        return "Tempat Duduk yang Nyaman";
    }
}