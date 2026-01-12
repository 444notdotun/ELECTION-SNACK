package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class AtmCard {
    @Id
    private String id;
    private Account account;
    private String atmDigit;
    private String password;
    private String bankName;

    public AtmCard( String password ,String bankName) {
        this.password = password;
        this.bankName = bankName;
    }
}
