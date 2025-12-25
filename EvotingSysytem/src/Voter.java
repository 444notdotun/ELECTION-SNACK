import exception.ValidateAgeException;

public class Voter extends User {
    private String VotersId;
    private int age;

    public Voter(String username, String password, String name, String address, int age) {
        super(username, password, name, address);
        verifyAge(age);
        this.age = age;
    }

    public String getVotersId() {
        return VotersId;
    }

    public void setVotersId(String votersId) {
        VotersId = votersId;
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
