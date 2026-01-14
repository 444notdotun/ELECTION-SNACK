package bankingapp.dtos.request;

import lombok.Data;

@Data
public class InterBankRequest {
    private String senderAccount;
    private String receiverAccount;
    private int amount;
}
