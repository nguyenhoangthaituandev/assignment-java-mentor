package org.example.models;

public abstract class PaymentMethod {
    protected double balance;

    public PaymentMethod(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    abstract boolean processPayment(double amount);
}
