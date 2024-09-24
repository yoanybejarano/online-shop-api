package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.request.LoginRequest;
import com.hatefulbug.onlineshop.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest request);
}
