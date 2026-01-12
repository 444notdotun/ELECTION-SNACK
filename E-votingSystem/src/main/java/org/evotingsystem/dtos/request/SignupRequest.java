package org.evotingsystem.dtos.request;

import lombok.Data;
import org.evotingsystem.data.models.Status;

@Data
public class SignupRequest {
    private String name;
    private String address;
    private String email;
    private int age;
    private String sex;
    private String password;
    private Status status;
}
