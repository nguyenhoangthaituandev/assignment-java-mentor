package org.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private User user;
    private PaymentMethod paymentMethod;
    private double amount;
    private LocalDateTime time;
    private boolean isRefunded;
    private boolean isSuccess;
    private boolean isFraudDetection;
    private boolean isPending;

    public Transaction(User user, PaymentMethod paymentMethod, double amount, LocalDateTime time) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.time = time;
        this.isRefunded=false;
        this.isSuccess=true;
        this.isFraudDetection=false;

    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
        isPending=!success;
    }

    public boolean isFraudDetection() {
        return isFraudDetection;
    }

    public void setFraudDetection(boolean fraudDetection) {
        isFraudDetection = fraudDetection;
        if (isFraudDetection) {
            this.isPending = false;
        }
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
