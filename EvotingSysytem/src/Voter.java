import exception.EligibilityException;
import exception.ValidateAgeException;
import exception.ValidatePasswordException;

public class Voter extends User {
    private String votersId;
    private int age;
    public boolean hasVoted;


    public Voter(String username, String password, String name, String address, int age) {
        super(username, password, name, address);
        verifyAge(age);
        this.age = age;
    }

    ElectoralOfficer electoralOfficer;
    public String getVotersId() {
        return votersId;
    }

    public void setVotersId(String votersId) {
        this.votersId = votersId;
    }

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
        checkStatus();
        eligibility();
        validatePassword(password);
        election.castVote(choice);
        hasVoted=true;
        election.setVoted(electoralOfficer.findByVoterId(votersId));
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
          String menu = String.format("NAME -> %s%n ADDRESS ->%s%n AGE -> %d%n",getName(),getAddress(),age);
          return menu;
    }
}
