package com.stackroute.emailappservice.service;

import com.stackroute.emailappservice.model.EmailData;

public interface EmailService {

    // Sends an email with the specified data
    String sendEmail(EmailData emailData);

    // Generates an OTP and sends it to the specified email address
    String generateOtpAndSendEmail(String receiverEmail);

    // Verifies the OTP for the specified email address
//    String checkOtp(String Otp);

     int checkOtp(int Otp);
}