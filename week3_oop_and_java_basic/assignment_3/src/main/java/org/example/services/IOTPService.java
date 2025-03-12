package org.example.services;

public interface IOTPService {

     String generateOTP();
     boolean verifyOTP(String generatedOTP, String userOTP);
}
