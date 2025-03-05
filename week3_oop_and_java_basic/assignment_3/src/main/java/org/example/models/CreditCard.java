package org.example.models;

public class CreditCard extends PaymentMethod {
    private double limit;
    private double debt;

    public CreditCard(double balance, double limit) {
        super(balance);
        this.limit = limit;
        this.debt=0;
    }

    @Override
    public boolean processPayment(double amount) {
        if(debt+amount>limit){
            System.out.println("Hạn mức tín dụng không đủ!");
            return false;
        }
        debt+=amount;
        return true;
    }
}
