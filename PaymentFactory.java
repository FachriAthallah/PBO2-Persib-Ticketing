package Ticketing_System;

public class PaymentFactory {
    public static PaymentStrategy createPayment(String type) {
        if (type.equalsIgnoreCase("cash")) {
            return new CashPayment();
        } else if (type.equalsIgnoreCase("ewallet")) {
            return new EWalletPayment();
        } else if (type.equalsIgnoreCase("transfer")) {
            return new TransferPayment();
        } else {
            return null;
        }
    }
}