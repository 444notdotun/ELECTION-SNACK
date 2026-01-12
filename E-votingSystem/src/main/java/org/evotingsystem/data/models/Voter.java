package org.evotingsystem.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class Voter extends User{
    @Id
    private String votersId;
    private VoterStatus voterStatus;
    private List<Election> electionList;
    public Voter(String name, String address, String email, int age, String Sex, String password) {
        super(name, address, email, age, Sex, password);
        this.voterStatus=VoterStatus.YET_TO_VOTE;
        this.electionList=new ArrayList<Election>();
    }
}
