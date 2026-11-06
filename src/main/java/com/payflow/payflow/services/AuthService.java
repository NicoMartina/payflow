package com.payflow.payflow.services;

import com.payflow.payflow.entities.User;
import com.payflow.payflow.repositories.UserRepository;
import com.payflow.payflow.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "you have registered successfully";
    }

    public String login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found."));

        if (passwordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Wrong password!");
    }

}
