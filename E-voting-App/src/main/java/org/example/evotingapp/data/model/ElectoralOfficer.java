package org.example.evotingapp.data.model;

import org.example.evotingapp.exception.OfficerExistException;

import java.util.ArrayList;
import java.util.List;

public class ElectoralOfficer extends User{
    private  List<User> voters= new ArrayList<>();
    private   List<Election> elections= new ArrayList<>();
    public boolean hasBeenCreated;

    private static ElectoralOfficer instance;
    private ElectoralOfficer() {
        super("firstName", "username", "password", "address",false);
    }

    public static ElectoralOfficer createOfficer(){
        if (instance == null) {
            instance = new ElectoralOfficer();
        }
        return instance;
    }


    public void SetFields(String firstName,String userName,String password,String address){
        if(hasBeenCreated){
           throw new OfficerExistException("YOU ALREADY HAVE A OFFICER");
        }
        this.setAddress(address);
        this.setFirstName(firstName);
        this.setPassword(password);
        this.setUsername(userName);
        this.hasBeenCreated =true;
    }
}
