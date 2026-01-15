package bankingapp.dtos.response;

import lombok.Data;

@Data
public class BalanceResponse {
    private String message;
    private String balance;
}
