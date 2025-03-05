package org.example.services;

import org.example.models.User;

public interface IPaymentService {
    boolean processPayment(User user, double amount);
}
