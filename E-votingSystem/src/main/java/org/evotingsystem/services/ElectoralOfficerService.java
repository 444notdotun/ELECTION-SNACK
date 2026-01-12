package org.evotingsystem.services;

import org.evotingsystem.dtos.request.LoginRequest;
import org.evotingsystem.dtos.request.SignupRequest;
import org.evotingsystem.dtos.response.LoginResponse;
import org.evotingsystem.dtos.response.SignUpResponse;

public interface ElectoralOfficerService {
    SignUpResponse registerVoter(SignupRequest request);
    LoginResponse login(LoginRequest loginRequest);
}
