package org.example.models;

public class CreditCard extends PaymentMethod {
    private double creditLimit;
    private double currentDebt;

    public CreditCard(double creditLimit) {
        this.currentDebt = 0;
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean processPayment(double amount) {
        if(currentDebt+amount>creditLimit){
            System.out.printf("Hạn mức tín dụng không đủ!");
            return false;
        }
        currentDebt+=amount;
        return true;
    }
}
