package Ticketing_System;

public class RegularTicket implements Ticket {
    
    @Override
    public String getType() {
        return "Regular";
    }

    @Override
    public double getPrice() {
        return 150000;
    }

    @Override
    public String getBenefit() {
        return "Akses Tribun Standar";
    }
}