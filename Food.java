package Ticketing_System;

public class Food implements AddOnService {

    @Override
    public String getName() {
        return "Makanan & Minuman";
    }
    
    @Override
    public double getPrice() {
        return 30000;
    }   
}