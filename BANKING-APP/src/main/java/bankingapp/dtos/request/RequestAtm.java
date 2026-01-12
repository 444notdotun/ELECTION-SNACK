package bankingapp.dtos.request;

import lombok.Data;

@Data
public class RequestAtm {
    private  String AccountNumber;
    private String password;
    private String bankName;
}
