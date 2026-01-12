package bankingapp.dtos.request;

import lombok.Data;

@Data
public class DepositRequest {
    private String phoneNumber;
    private int amount;
    private String description;
}
