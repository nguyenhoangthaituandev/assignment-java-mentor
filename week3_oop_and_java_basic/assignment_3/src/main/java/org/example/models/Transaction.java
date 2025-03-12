package org.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private User user;
    private PaymentMethod paymentMethod;
    private double amount;
    private LocalDateTime time;
    private boolean isRefunded;

    public Transaction(User user, PaymentMethod paymentMethod, double amount, LocalDateTime time) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.time = time;
        this.isRefunded=false;
    }
    public Transaction(User user, PaymentMethod paymentMethod, double amount, LocalDateTime time, boolean isRefunded) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.time = time;
        this.isRefunded=isRefunded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "user=" + user +
                ", paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                ", time=" + time +
                ", isRefunded=" + isRefunded +
                '}';
    }
}
