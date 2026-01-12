package org.evotingsystem.data.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class Election {
    @Id
    private String id;
    @NotBlank(message = "FIELD CAN NOT BE BLANK")
    private String name;
    private List<Voter> candidates;
    private List<Voter> Voted;
    private List<Integer> poll;
    private Status electionStatus;
    private String electionResult;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate stopDate;
    private LocalTime  stopTime;
    public Election(String name){
        this.name = name;
        this.electionStatus = Status.INACTIVE;
        candidates=new ArrayList<>();
        Voted = new ArrayList<>();
        poll = new ArrayList<>();

    }
}
