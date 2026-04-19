package Ticketing_System;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PERSIB TICKETING SYSTEM ===");
        
        System.out.print("Masukan Nama: ");
        String name = input.nextLine();
        
        System.out.println("\nPilih Kategori Tiket:");
        System.out.println("1. Regular");
        System.out.println("2. VIP");
        System.out.println("3. Corporate");
        System.out.print("Pilihan: ");
        int ticketChoice = Integer.parseInt(input.nextLine());

        Ticket ticket = null;
        
        if (ticketChoice == 1) {
            ticket = new RegularTicket();
            
        } else if (ticketChoice == 2) {
            ticket = new VIPTicket();
            
        } else if (ticketChoice == 3) {
            ticket = new CorporateTicket();
        }
        
        if (ticket == null) {
            System.out.println("Pilihan tiket tidak valid!");
            return;
        }
        
        System.out.println("\nPilih Metode Pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. E-Wallet");
        System.out.println("3. Transfer");
        System.out.print("Pilihan: ");
        int payChoice = Integer.parseInt(input.nextLine());

        String paymentType = "";
        if (payChoice == 1) paymentType = "cash";
        else if (payChoice == 2) paymentType = "ewallet";
        else if (payChoice == 3) paymentType = "transfer"; // ✅ FIX

        PaymentStrategy payment = PaymentFactory.createPayment(paymentType);
        
        if (payment == null) {
            System.out.println("Metode pembayaran tidak valid!");
            return;
        }
        
        Order order = new Order(name, ticket, payment);
        
        System.out.println("\nTambah Add-on?");
        System.out.println("1. Merchandise");
        System.out.println("2. Food");
        System.out.println("3. Meet & Greet");
        System.out.println("0. Tidak");
        System.out.print("Pilihan: ");
        int addonChoice = Integer.parseInt(input.nextLine());

        if (addonChoice == 1) {
            order.addAddOn(new Merchandise());
        } else if (addonChoice == 2) {
            order.addAddOn(new Food());
        } else if (addonChoice == 3) {
            order.addAddOn(new MeetAndGreet());
        }
        
        order.showDetail();

        input.close();
    }
}