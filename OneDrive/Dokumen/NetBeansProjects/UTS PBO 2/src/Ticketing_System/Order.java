package Ticketing_System;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private Ticket ticket;
    private List<AddOnService> addons;
    private PaymentStrategy payment;
    
    public Order (String customerName, Ticket ticket, PaymentStrategy payment) {
        this.customerName = customerName;
        this.ticket = ticket;
        this.payment = payment;
        this.addons = new ArrayList<>();
    }
    
    public void addAddOn(AddOnService addon) {
        addons.add(addon);
    }
    
    public double calculateTotal() {
        double total = ticket.getPrice();
        for (AddOnService addon : addons) {
            total += addon.getPrice();
        }
        return total;
    }
    
    public void showDetail() {
        System.out.println("\n=== DETAIL PEMESANAN ===");
        System.out.println("Nama: " + customerName);
        System.out.println("Tiket: " + ticket.getType());
        System.out.println("Benefit: " + ticket.getBenefit());
        
        System.out.println("\nAdd-On");
        if (addons.isEmpty()) {
            System.out.println("Tidak ada");
        } else {
            for (AddOnService addon : addons) {
                System.out.println("- " + addon.getName() + " (Rp " + addon.getPrice() + ")");
            }
        }
        
        double total = calculateTotal();
        System.out.println("\nTotal Harga: Rp " + total);
        
        System.out.println("Metode Pembayaran: " + payment.getMethod());
        payment.pay(total);
    }
}