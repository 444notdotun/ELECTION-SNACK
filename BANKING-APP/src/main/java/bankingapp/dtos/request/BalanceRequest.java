package bankingapp.dtos.request;

import lombok.Data;

@Data
public class BalanceRequest {
    private String accountNumber;
    private String password;
    private String bankName;
}
