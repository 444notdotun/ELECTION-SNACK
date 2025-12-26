import exception.EligibilityException;
import exception.ValidateAgeException;
import exception.ValidateIdException;
import exception.ValidatePasswordException;

import java.util.ArrayList;
import java.util.List;

public class Voter extends User {
    private String votersId;
    private int age;
    private boolean hasVoted;
    private List<Election> elections;


    public Voter(String username, String password, String name, String address, int age) {
        super(username, password, name, address);
        verifyAge(age);
        this.age = age;
        elections = new ArrayList<>();
    }

    ElectoralOfficer electoralOfficer;



    public void editProfile(String detail, String update) {
        checkStatus();
        detail = detail.toLowerCase();
        switch (detail) {

            case "username" -> {
                setUsername(update);
                IO.println("UPDATED SUCCESSFULLY");
            }
            case "name" -> {
                setName(update);
                IO.println("UPDATED SUCCESSFULLY");
            }
            case "password" -> {
                setPassword(update);
                IO.println("UPDATED SUCCESSFULLY");
            }
            case "address" -> {
                setAddress(update);
                IO.println("UPDATED SUCCESSFULLY");
            }
            case "voterid" -> {
                IO.println("VOTER ID CAN NOT BE UPDATED");
            }
            default -> IO.println("INVALID INPUT");
        }
    }

    private void verifyAge(int age) {
        if (age < 18) throw new ValidateAgeException("YOU ARE LESS THAN 18");
    }

    public void editProfile(String detail, int update) {
        checkStatus();
        detail = detail.toLowerCase();
        if (detail.equals("age")) {
            setAge(update);
            IO.println("UPDATED SUCCESSFULLY");
        }
        else{
            IO.println("INVALID INPUT");
        }
    }




    public void  vote(Election election,String password, int choice){
        ElectoralOfficer.createOfficer();
        checkStatus();
        checkElectionStatus(election);
        checkForElection(election);
        eligibility();
        validatePassword(password);
        election.castVote(choice);
        hasVoted=true;
        election.setVoted(electoralOfficer.findByVoterId(votersId));
        elections.add(election);
    }
    public String getVotersId() {
        return votersId;
    }

    public void setVotersId(String votersId) {
        this.votersId = votersId;
    }

    private void checkForElection( Election election){
        if (!elections.contains(election)) {
            hasVoted = false;
        }
    }

    private void checkElectionStatus(Election election){
        if(!election.isElectionStatus()){
            throw new ValidateIdException("ELECTION HAS NOT STARTED");
        }

    }

    private void validatePassword(String password){
        if(!password.equals(getPassword())){
            throw new ValidatePasswordException("INVALID PASSWORD");
        }
    }
    private void eligibility(){
        if(hasVoted){
            throw new EligibilityException("YOU ALREADY VOTED");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("NAME -> %s%n ADDRESS ->%s%n AGE -> %d%n",getName(),getAddress(),age);
    }
}
