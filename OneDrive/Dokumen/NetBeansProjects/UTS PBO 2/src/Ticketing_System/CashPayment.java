package Ticketing_System;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran cash sebesar Rp " + amount);
    }

    @Override
    public String getMethod() {
        return "cash";
    }
}