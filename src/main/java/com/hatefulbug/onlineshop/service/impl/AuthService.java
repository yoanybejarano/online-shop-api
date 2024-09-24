package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.request.LoginRequest;
import com.hatefulbug.onlineshop.response.LoginResponse;
import com.hatefulbug.onlineshop.security.AuthUser;
import com.hatefulbug.onlineshop.security.JwtService;
import com.hatefulbug.onlineshop.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((AuthUser) auth.getPrincipal());
        claims.put("fullName", user.getUsername());

        var jwtToken = jwtService.generateToken(claims, (AuthUser) auth.getPrincipal());
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }


}
