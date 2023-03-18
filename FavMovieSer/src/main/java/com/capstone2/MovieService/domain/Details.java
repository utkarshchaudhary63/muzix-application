package com.capstone2.MovieService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Details {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String profilePic;
    private int age;
    private long mobileNo;
    private List<Movie> Favourite;
}
