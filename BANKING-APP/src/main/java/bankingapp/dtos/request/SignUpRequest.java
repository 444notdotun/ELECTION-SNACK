package bankingapp.dtos.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String id;
    private String username;
    private String email;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String password;
    private String bvn;

}
