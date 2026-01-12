package bankingapp.dtos.response;

import lombok.Data;

@Data
public class CreateAccountResponse {
    private String message;
    private String accountNumber;
}
