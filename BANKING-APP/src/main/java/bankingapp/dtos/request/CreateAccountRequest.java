package bankingapp.dtos.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String name;
    private String email;
    private String password;
    private String BankName;
    private String bvn;
    private String phoneNumber;
}
