package Ticketing_System;

public class MeetAndGreet implements AddOnService {

    @Override
    public String getName() {
        return "Meet & Greet Pemain";
    }

    @Override
    public double getPrice() {
        return 1500000;
    }
}