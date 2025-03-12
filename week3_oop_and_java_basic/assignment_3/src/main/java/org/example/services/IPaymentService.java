package org.example.services;

import org.example.models.PaymentMethod;
import org.example.models.Transaction;
import org.example.models.User;

public interface IPaymentService {
    boolean processPayment(User user, double amount, PaymentMethod paymentMethod);


}
