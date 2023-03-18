package com.stackroute.emailappservice.service;

import com.stackroute.emailappservice.model.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    public int otp;
    private long otpTimestamp;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")

    private String sender;


    // Map to store generated OTPs
    private final Map<String, Integer> generatedOtp = new HashMap<>();


    @Override
    public String sendEmail(EmailData emailData) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(emailData.getReceiver());
            helper.setSubject(emailData.getSubject());
            helper.setText(emailData.getMessageBody(), true);

            if (emailData.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(emailData.getAttachment()));
                helper.addAttachment(file.getFilename(), file);
            }

            javaMailSender.send(message);
            return "Mail Sent to " + emailData.getReceiver();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sending mail failed...";
        }
    }

    @Override

    public String generateOtpAndSendEmail(String receiverEmail) {
        try {
            // generate a 4-digit OTP
            otp = (int) (Math.random() * 9000) + 1000;
            otpTimestamp = System.currentTimeMillis();
            // create a new EmailData object
            EmailData emailData = new EmailData();
            emailData.setReceiver(receiverEmail);
            emailData.setSubject("Your OTP");
            emailData.setMessageBody("Your OTP is " + otp);
            // send the email
            sendEmail(emailData);

        } catch (Exception e) {
            e.printStackTrace();
            return "Sending OTP failed...";
        }
        return "OTP sent to " + receiverEmail;
    }

    @Override
    public int checkOtp(int Otp) {
        if (System.currentTimeMillis() - otpTimestamp > 2 * 60 * 1000)
        {

            otp=0;
            System.out.println(Otp);
//            return false;
        }
//         check;
        System.out.println(otp);
//        check = otp == Otp;
        return otp;
    }

//    @Override
//    public String checkOtp(String Otp) {
//        if (System.currentTimeMillis() - otpTimestamp > 2 * 60 * 1000)
//        {
//            System.out.println(Otp);
////            return false;
//        }
////         check;
//        System.out.println(otp);
////        check = otp == Otp;
//        return otp+"";
    }




