package org.example.evotingapp.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;




@Data
@AllArgsConstructor
public class User {

    private  String firstName;
    private  String username;
    private  String password;
    private  String address;
    private boolean activeStatus;


}
