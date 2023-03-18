package com.capstone2.MovieService.proxy;

import com.capstone2.MovieService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name ="AuthenticationService" ,url ="localhost:8092")
public interface UserMovieProxy
{
        @PostMapping("/user/api/v1/Register")
        public ResponseEntity<?> saveUser(@RequestBody User user);
}
