package org.example.models;

import org.example.constants.Constant;

public class BankTransfer extends PaymentMethod {


    public BankTransfer(double balance) {
        super(balance);
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount > balance) {
            System.out.println("Tài khoản không đủ ");
            return false;
        }
        balance -= amount + amount * Constant.RATE;
        return true;
    }
}
