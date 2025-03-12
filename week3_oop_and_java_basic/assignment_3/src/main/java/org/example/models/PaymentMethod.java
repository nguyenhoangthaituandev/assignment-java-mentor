package org.example.models;

public abstract class PaymentMethod {
    protected double balance;

    public PaymentMethod(double balance) {
        this.balance = balance;
    }

    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }

    public boolean deductBalance(double amount) {
        if (hasSufficientBalance(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
