package org.evotingsystem.data.models;

import lombok.Data;
import org.evotingsystem.dtos.request.SignupRequest;
import org.evotingsystem.dtos.response.SignUpResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class ElectoralOfficer extends User{
    @Id
    private String id;
    private int regCount;
    private List<Voter> voterList;
    private List<Election> electionList;
    public ElectoralOfficer(String name, String address, String email, int age, String Sex, String password) {
        super(name, address, email, age, Sex, password);
        this.voterList = new ArrayList<>();
        this.electionList = new ArrayList<>();
    }

    public SignUpResponse signup(SignupRequest request) {
        return null;
    }
}
