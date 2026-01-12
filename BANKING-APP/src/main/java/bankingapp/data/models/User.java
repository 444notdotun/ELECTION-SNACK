package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private List<Account> accounts;
    private List<AtmCard> atmCards;
    private String bvn;

    public User(String name, String email, String dateOfBirth, String address,String phoneNumber) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.accounts = new ArrayList<>();
        this.atmCards = new ArrayList<>();
        this.phoneNumber=phoneNumber;
    }
}
