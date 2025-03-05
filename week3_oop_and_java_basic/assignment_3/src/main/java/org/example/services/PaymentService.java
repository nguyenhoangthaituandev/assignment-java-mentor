package org.example.services;

import org.example.models.User;

public class PaymentService implements IPaymentService{
    @Override
    public boolean processPayment(User user, double amount) {
        return false;
    }
}
