package org.example.models;

import org.example.constants.Constant;

public class EWallet extends PaymentMethod {
    public EWallet(double balance) {
        super(balance);
    }

    @Override
    public boolean processPayment(double amount) {
        if(amount>balance || amount> Constant.WALLET_LIMITS){
            System.out.printf("Số dư của quý khách hiện không đủ hoặc vượt quá mức được rút");
            return false;
        }
        balance-=amount;
        return true;
    }
}
