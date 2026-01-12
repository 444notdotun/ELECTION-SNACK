package org.evotingsystem.services;

import org.evotingsystem.dtos.request.LoginRequest;
import org.evotingsystem.dtos.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
