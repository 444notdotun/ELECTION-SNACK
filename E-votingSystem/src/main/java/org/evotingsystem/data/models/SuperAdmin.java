package org.evotingsystem.data.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SuperAdmin  {
    @Id
    private String id;
    @NotBlank
    @Email(message = "invalid")
private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    private Status status;

    public SuperAdmin(String username,String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status=Status.INACTIVE;
    }

}
