package org.example.services;

import org.example.models.Transaction;
import org.example.models.User;

import java.util.List;

public interface IRefundService {
    List<Transaction> getRefundableTransaction(User user,TransactionService transactionService);
    boolean processRefund(User user, Transaction transaction);
}
