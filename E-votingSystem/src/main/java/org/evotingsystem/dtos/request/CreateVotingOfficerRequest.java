package org.evotingsystem.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CreateVotingOfficerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String email;
    @NotBlank
    private int age;
    @NotBlank
    private String sex;
    @NotBlank
    private String password;
}
