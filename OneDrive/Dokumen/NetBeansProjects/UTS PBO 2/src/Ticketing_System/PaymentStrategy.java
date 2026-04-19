package Ticketing_System;

public interface PaymentStrategy {
    void pay (double amount);
    String getMethod();
}