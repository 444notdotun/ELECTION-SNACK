import exception.OfficerExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Election {
    private String name;
    private List<Voter> candidates;
    private List<Voter> eligibleVoters;
    private List<Voter> Voted;
    private boolean electionStatus;
    private String electionResult;
    private LocalDate startDate;
    private LocalTime  startTime;

    public Election(String name){
        this.name = name;
        candidates=new ArrayList<>();
        eligibleVoters=new ArrayList<>();
        Voted = new ArrayList<>();
        this.startDate= LocalDate.now();
        this.startTime= LocalTime.now();
    }

//    public void inputCandidate(String id){
//       if(electoralOfficer.findByVoterId(id).getAge()>=30) {
//           candidates.add(electoralOfficer.findByVoterId(id));
//       }
//       throw new OfficerExistException("AGE SHOULD BE 30 AND ABOVE TO BE A CANDIDATE");
//    }

    public String getName() {
        return name;
    }

    public List<Voter> getCandidates() {
        return candidates;
    }

    public List<Voter> getEligibleVoters() {
        return eligibleVoters;
    }

    public List<Voter> getVoted() {
        return Voted;
    }

    public boolean isElectionStatus() {
        return electionStatus;
    }

    public String getElectionResult() {
        return electionResult;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
