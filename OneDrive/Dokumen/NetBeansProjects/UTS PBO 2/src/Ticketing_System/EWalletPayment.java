package Ticketing_System;

public class EWalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran via E-Wallet sebesar Rp " + amount);
    }

    @Override
    public String getMethod() {
        return "E-Wallet";
    }
}