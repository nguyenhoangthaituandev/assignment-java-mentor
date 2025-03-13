package org.example.services;

import org.example.models.Transaction;
import org.example.models.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FrauDetectionService implements IFraudDetectionService{
    private final int frauTime=5;
    private final int frauAmount=5000;
    private final Duration frauTimeCountDown=Duration.ofHours(1);


    @Override
    public boolean isFrauDectection(User user, List<Transaction> transactions) {
        LocalDateTime now=LocalDateTime.now();
        long recentLargeTransactions = transactions.stream()
                .filter(t -> t.getAmount() > frauAmount && Duration.between(t.getTime(), now).compareTo(frauTimeCountDown) <= 0)
                .count();

        boolean isFraud=recentLargeTransactions >= frauTime;
        if(isFraud){
            transactions.forEach(t->t.setFraudDetection(isFraud));
        }
        return isFraud;
    }

    @Override
    public void callMessage(User user) {
        System.out.println("Giao dịch của " + user.getUsername() + " bị chặn do dấu hiệu gian lận! Yêu cầu xác minh qua tổng đài.");
    }
}
