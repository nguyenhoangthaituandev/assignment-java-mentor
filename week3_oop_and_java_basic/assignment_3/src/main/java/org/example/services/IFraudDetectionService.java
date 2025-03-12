package org.example.services;

import org.example.models.Transaction;
import org.example.models.User;

import java.util.List;

public interface IFraudDetectionService {
    boolean isFrauDectection(User user, List<Transaction> transactions);
    void  callMessage(User user);
}
