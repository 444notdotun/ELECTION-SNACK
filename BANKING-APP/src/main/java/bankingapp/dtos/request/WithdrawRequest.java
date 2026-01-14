package bankingapp.dtos.request;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String accountNumber;
    private int amount;
    private String bankName;
    private String password;
}
