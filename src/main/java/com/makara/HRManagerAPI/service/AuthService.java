package com.makara.HRManagerAPI.service;

import com.makara.HRManagerAPI.dto.response.AuthResponse;
import com.makara.HRManagerAPI.dto.request.LoginRequest;
import com.makara.HRManagerAPI.dto.request.RegisterRequest;
import com.makara.HRManagerAPI.exception.UsernameAlreadyExistsException;
import com.makara.HRManagerAPI.model.Role;
import com.makara.HRManagerAPI.model.User;
import com.makara.HRManagerAPI.model.UserPrincipal;
import com.makara.HRManagerAPI.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponse login(LoginRequest request){

        Authentication auth =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );

        if(auth.isAuthenticated()){
            UserPrincipal userPrincipal =
                (UserPrincipal) auth.getPrincipal();

            User user = userPrincipal.getUser();
            String token = jwtService.generateToken(user);
            return new AuthResponse(
                    "Login successful", token
            );
        }

        throw new RuntimeException(
                "Invalid credentials"
        );
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);

        return "User registered successfully";
    }


}