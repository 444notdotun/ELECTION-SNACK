package org.example.evotingapp.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterUserResponse {
    private String message;
    private String votersId;
    private String RegistrationId;
}
