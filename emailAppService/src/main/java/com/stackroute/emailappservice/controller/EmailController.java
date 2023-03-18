package com.stackroute.emailappservice.controller;

import com.stackroute.emailappservice.model.EmailData;
import com.stackroute.emailappservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail-app")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /*
    POST
    http://localhost:5673/mail-app/send-mail
     */
    @PostMapping("/send-mail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailData emailData) {
        return new ResponseEntity<>(emailService.sendEmail(emailData), HttpStatus.OK);
    }

    /*
      POST
      http://localhost:5673/mail-app/send-otp?receiverEmail=user@example.com
       */
    @GetMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestParam String receiverEmail) {
        return new ResponseEntity<>(emailService.generateOtpAndSendEmail(receiverEmail), HttpStatus.OK);
    }
    /*
     GET
     http://localhost:5673/mail-app/check/{Otp}
      */
    @GetMapping("/check/{Otp}")
    public ResponseEntity<?> checkOtp(@PathVariable int Otp) {
        return new ResponseEntity<>(emailService.checkOtp(Otp), HttpStatus.OK);
    }
}

