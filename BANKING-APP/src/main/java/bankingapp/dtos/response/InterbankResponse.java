package bankingapp.dtos.response;

import bankingapp.data.models.Transaction;
import lombok.Data;

@Data
public class InterbankResponse {
    private String message;
    private Transaction  transaction;
}
