package org.example.models;

public abstract class PaymentMethod {
    abstract boolean processPayment(double amount);
}
