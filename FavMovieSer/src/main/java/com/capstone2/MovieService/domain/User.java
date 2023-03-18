package com.capstone2.MovieService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class User {
    @Id
    private String email;
    @Transient
    private String password;
    private int amount;
    private String subscriptionPlan;
   private List<Details>users;
}

