package org.example.evotingapp.service;

import org.example.evotingapp.dtos.request.RegisterUserRequest;
import org.example.evotingapp.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Service;

public interface ElectoralOfficerService {

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
}
