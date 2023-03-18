package com.niit.authentication.services;


import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

//    private JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

//        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmail()).isPresent()){
            throw  new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }
    @Override
    public User LoginCheck(String emailId,String password) throws UserNotFoundException {
        if (userRepository.findById(emailId).isPresent()){
            User user = userRepository.findById(emailId).get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        } else {
            throw  new UserNotFoundException();

        }

    }

    @Override
    public User updatePassword(String emailId, User user1) {
       User user=userRepository.findById(emailId).get();
       user.setEmail(user1.getEmail());
           user.setPassword(user1.getPassword());

        userRepository.save(user);
        return user;
    }


}
