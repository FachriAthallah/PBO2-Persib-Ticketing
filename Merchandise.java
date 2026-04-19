package Ticketing_System;

public class Merchandise implements AddOnService {

    @Override
    public String getName() {
        return "Merchandise Persib";
    }

    @Override
    public double getPrice() {
        return 50000;
    }
}