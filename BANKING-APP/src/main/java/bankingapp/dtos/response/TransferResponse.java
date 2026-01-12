package bankingapp.dtos.response;

import bankingapp.data.models.Transaction;
import lombok.Data;

@Data
public class TransferResponse {
    private Transaction transaction;
    private String message;
}
