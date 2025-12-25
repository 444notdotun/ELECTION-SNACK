package org.example.evotingapp.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "voters")
public class Voter extends User{

    @Id
    private String registrationNUmber;

    private boolean hasVoted;
    private int age;
    private String  votersId;
    private static int count;


    public Voter(String firstName, String username, String password, String address,int age) {
        super(firstName,username,password,address,false);
        this.votersId= generateId();
        this.age =age;
    }

    private String generateId(){
        return "VOT"+count++;
    }

}
