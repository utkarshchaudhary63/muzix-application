package com.niit.authentication.services;

import com.niit.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator {

    @Override
    public Map<String, String> tokenGeneration(User user)
    {
        String jwtToken=null;
        jwtToken=    Jwts.builder().setSubject(user.getEmail()).
                setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"Security Key").compact();
        Map<String,String>map=new HashMap<>();
        map.put("token",jwtToken);
        map.put("massage","User Logged In Successfully");
        return map;
    }
}

