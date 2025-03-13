package org.example.services;

import org.example.models.PaymentMethod;
import org.example.models.Transaction;
import org.example.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.Main.transactionService;

public class RefundServiceImp implements IRefundService{
    private final int refundLimitDays=7;

    @Override
    public List<Transaction> getRefundableTransaction(User user,TransactionService transactionService) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(refundLimitDays);
        return transactionService.getTransactionsForUser(user).stream()
                .filter(t -> t.getTime().isAfter(cutoffDate) && !t.isRefunded())
                .collect(Collectors.toList());
    }

    @Override
    public boolean processRefund(User user, Transaction transaction) {
        if(!getRefundableTransaction(user,transactionService).contains(transaction)){
            System.out.println("Không tìm thấy giao dịch này");
            return false;
        }

        PaymentMethod originalPaymentMethod=transaction.getPaymentMethod();
        if(originalPaymentMethod.refund(transaction.getAmount())){
            transaction.setRefunded(true);
            transactionService.recordTransaction(transaction);
            System.out.println("Hoàn tiền thành công về phương thức ban đầu.");
            return true;
        }
        return false;
    }


}
