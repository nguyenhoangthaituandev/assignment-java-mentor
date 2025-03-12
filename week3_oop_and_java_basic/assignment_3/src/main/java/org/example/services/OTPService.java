package org.example.services;

import java.util.Random;

public class OTPService implements IOTPService{
    public static Random random=new Random();
    @Override
    public String generateOTP() {
        return String.valueOf(random.nextInt(1000));
    }

    @Override
    public boolean verifyOTP(String generatedOTP, String userOTP) {
        return generatedOTP.equals(userOTP);
    }
}
