package Ticketing_System;

public class TransferPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran via Transfer Bank sebesar Rp " + amount);
    }

    @Override
    public String getMethod() {
        return "Transfer Bank";
    }
}