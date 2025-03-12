package org.example.services;

import org.example.models.PaymentMethod;
import org.example.models.Transaction;
import org.example.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentService implements IPaymentService{
    private List<Transaction> transactions=new ArrayList<>();
    @Override
    public boolean processPayment(User user, double amount, PaymentMethod paymentMethod) {
        if(paymentMethod.deductBalance(amount)){
            System.out.println("Thanh toán thành công qua "+ paymentMethod.getClass().getSimpleName());
            Transaction transaction=new Transaction(user,paymentMethod,amount, LocalDateTime.now());
            recordTransaction(transaction);
            return true;
        }
        System.out.println("Thanh toán thất bại");
        return false;
    }

    @Override
    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
