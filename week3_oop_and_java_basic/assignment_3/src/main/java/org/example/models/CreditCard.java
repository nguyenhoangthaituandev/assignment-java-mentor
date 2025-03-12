package org.example.models;

public class CreditCard extends PaymentMethod {
    private double limit;


    public CreditCard(double balance, double limit) {
        super(balance);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasSufficientBalance(double amount) {
        return (balance+limit)>=amount;
    }

    @Override
    public boolean deductBalance(double amount) {
        if(hasSufficientBalance(amount)){
            if(amount>balance){
                limit -=(amount-balance);
                balance=0;
            }else{
                balance-=amount;
            }
            return true;
        }
        return false;
    }
}
