package Ticketing_System;

public class CorporateTicket implements Ticket {

    @Override
    public String getType() {
        return "Corporate";
    }

    @Override
    public double getPrice() {
        return 3000000;
    }

    @Override
    public String getBenefit() {
        return "Full Fasilitas (makan, lounge ekslusif, meet & greet)";
    }
}