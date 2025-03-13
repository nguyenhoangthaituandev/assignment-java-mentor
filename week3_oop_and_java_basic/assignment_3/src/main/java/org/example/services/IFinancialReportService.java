package org.example.services;

import org.example.models.User;

public interface IFinancialReportService {
    void generateReport(User user,TransactionService transactionService);
}
