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
    private LocalDate stopDate;
    private LocalTime  stopTime;

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

    public List<Integer> getPoll() {
        return poll;
    }

    public void startElection(){
        this.startDate= LocalDate.now();
        this.startTime= LocalTime.now();
        this.electionStatus=true;
    }

    public void stopElection(){
        this.stopDate= LocalDate.now();
        this.stopTime= LocalTime.now();
        this.electionStatus=false;
    }

    public void castVote(int choice){
       int castingVote = poll.get(choice-1);
       poll.add(choice-1,++castingVote);
    }

    public String electionResult(){
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<candidates.size();i++){
            Voter voter = candidates.get(i);
            result.append(String.format("CANDIDATE NAME==> %s\t VOTES ==> %s%n",voter.getName(),poll.get(i)));
        }
        return result.toString();
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
