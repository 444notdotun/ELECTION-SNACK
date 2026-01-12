package bankingapp.dtos.request;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String bankName;
    private String phoneNumber;
    private String amount;
}
