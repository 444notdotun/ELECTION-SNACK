package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Document
@Data
public class Transaction {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String description;
    private String senderAccount;
    private String receiverAccount;
    private int amount;
    public Transaction(String description, String senderAccount, String receiverAccount, int amount) {
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
    }
}
