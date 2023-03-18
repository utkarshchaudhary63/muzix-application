package com.stackroute.emailappservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Otp {
    private String email;
    private int otp;
    private LocalDateTime timestamp;
}
