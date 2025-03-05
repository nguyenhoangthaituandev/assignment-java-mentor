package org.example.models;

import org.example.constants.Constant;
import org.example.constants.UserStatus;

import java.util.List;

public class User {
    private String username;
    private String password;
    private UserStatus status;
    private List<PaymentMethod> paymentMethods;
    private int failedLoginAttempts;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = UserStatus.ACTIVE;
        this.failedLoginAttempts=0;
    }

    public void increaseFailedLoginAttempts() {
        this.failedLoginAttempts++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
