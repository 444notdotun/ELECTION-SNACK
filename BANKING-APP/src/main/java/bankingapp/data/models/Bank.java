package bankingapp.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
@Document
@Data
public class Bank {
    @Id
    private String id;
    private Map<String, Account> accounts;
    private Map<String,AtmCard> atmCards;
    private String bankName;
    private String bankCode;
    private int count;

    public Bank(String bankCode,String bankName){
        this.bankCode=bankCode;
        this.bankName=bankName;
        this.accounts=new HashMap<>();
        this.atmCards=new HashMap<>();
        this.count=0;
    }
}
