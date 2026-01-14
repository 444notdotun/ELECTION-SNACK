package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Document
@Data
public class Account {
    private String name;
    private String email;
    private BigDecimal balance;
    private String bvn;
    private String password;
    private String accountNumber;
    private AtmCard atmCard;
    private String bankName;
    private String phoneNumber;
    private List<Transaction> transactions;

    public Account(String name, String email, String password ,String bvn,String phoneNumber) {
        this.name = name;
        this.email = email;
        this.balance = BigDecimal.valueOf(0);
        this.password = password;
        this.bvn = bvn;
        this.phoneNumber = phoneNumber;
        this.transactions = new ArrayList<>();

    }

}
