package com.niit.authentication.controller;

import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.services.SecurityTokenGenerator;
import com.niit.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    private UserService userService;

    private SecurityTokenGenerator securityTokenGenerator;

//    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator){
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
//        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        User result=userService.LoginCheck(user.getEmail(),user.getPassword());
        if(result!=null){
            Map<String,String> map=securityTokenGenerator.tokenGeneration(result);
            return new ResponseEntity<>(map,HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Invalid User",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Register")
    public ResponseEntity<?> saveUser(@RequestBody User user)throws UserAlreadyExistsException {
        User userCreated=userService.addUser(user);
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(user.getEmail());
//        msg.setSubject("Registration");
//        msg.setText("You have successfully registered.Please continue with login.");
//        javaMailSender.send(msg);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PostMapping("/updatePassword/{email}")
    public ResponseEntity<?> subscriber(@PathVariable String email,@RequestBody User user1){

        return new ResponseEntity<>(userService.updatePassword(email,user1),HttpStatus.ACCEPTED);
    }


}
