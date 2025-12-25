
import exception.ValidateDetailsException;
import exception.ValidateStatusException;

public class User {
    private String username;
    private String password;
    private String name;
    private String address;
    private boolean activeStatus;

    public User(String username,String password,String name,String address){
        this.password= password;
        this.username=username;
        this.name=name;
        this.address=address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void login(String username, String password){
        validateDetails(username,password);
        this.activeStatus=true;
    }


    public void  logout(){
        this.activeStatus = false;
    }


    private void validateDetails(String username, String password){
        if(!username.equals(this.username)&&!password.equals(this.password)){
            throw new ValidateDetailsException("INVALID DETAILS");
        }
    }

    protected void checkStatus(){
        if(!getActiveStatus()){
            throw new ValidateStatusException("YOU'RE NOT LOGGED IN!");
        }
    }



}
