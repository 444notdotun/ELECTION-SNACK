package org.example.evotingapp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterUserRequest {
    private String firstName;
    private String username;
    private int age;
    private String password;
    private String address;
}
