package org.example.services;

import org.example.models.PaymentMethod;
import org.example.models.Transaction;
import org.example.models.User;

import java.util.List;

public interface ITransactionService {
     boolean processTransaction(User user, double amount, PaymentMethod paymentMethod);
}
