package bankingapp.dtos.response;

import bankingapp.data.models.Transaction;
import lombok.Data;

@Data
public class DepositResponse {
    private Transaction transaction;
    private String message;
}
