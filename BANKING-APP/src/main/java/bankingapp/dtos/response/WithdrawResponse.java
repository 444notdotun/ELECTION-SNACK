package bankingapp.dtos.response;

import bankingapp.data.models.Transaction;
import lombok.Data;

@Data
public class WithdrawResponse {
    private Transaction transaction;
    private String message;
}
