package bankingapp.dtos.request;

import lombok.Data;

@Data
public class TransferRequest {
private String accountNumber;
private String password;
private String bankName;
private String phoneNumber;

}
