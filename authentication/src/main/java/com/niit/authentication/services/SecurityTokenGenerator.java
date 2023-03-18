package com.niit.authentication.services;

import com.niit.authentication.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String, String> tokenGeneration(User user);
}
