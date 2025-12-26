import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Election {
    private String name;
    private List<Voter> candidates;
    private List<Voter> Voted;
    private List<Integer> poll;
    private boolean electionStatus;
    private String electionResult;
    private LocalDate startDate;
    private LocalTime  startTime;

    public Election(String name){
        this.name = name;
        candidates=new ArrayList<>();
        Voted = new ArrayList<>();
        poll = new ArrayList<>();

    }

    public void setCandidate(Voter  candidates) {
        this.candidates.add(candidates);
        this.poll.add(0);
    }

    public void setVoted(Voter voter) {
        this.Voted.add(voter);
    }




    public void startElection(){
        this.startDate= LocalDate.now();
        this.startTime= LocalTime.now();
        this.electionStatus=true;
    }

    public void castVote(int choice){
       int castingVote = poll.get(choice);
       poll.add(choice,++castingVote);
    }

    public String getName() {
        return name;
    }

    public List<Voter> getCandidates() {
        return candidates;
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
