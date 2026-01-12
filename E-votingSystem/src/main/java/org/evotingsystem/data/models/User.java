package org.evotingsystem.data.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data

public class User {
    @NotBlank(message = "NAME CAN NOT BE NULL")
    private String name;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    private String address;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    @Email(message = "invalid email")
    @Indexed(unique = true)
    private String email;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    private int age;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    private String sex;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    @Size(min =4,message = "minimum is 4")
    private String password;
    private Status status;
    public User(String name, String address, String email, int age, String sex, String password) {
        this.name = name;
        this.address = address;
        this.age=age;
        this.email=email;
        this.sex=sex;
        this.password=password;
        this.status=Status.INACTIVE;
    }


}
