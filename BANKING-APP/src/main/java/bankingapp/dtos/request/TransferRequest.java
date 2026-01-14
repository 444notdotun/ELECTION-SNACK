package bankingapp.dtos.request;

import lombok.Data;

@Data
public class TransferRequest {
private String receiverAccountNumber;
private  String receiverBankName;
private String senderPassword;
private String senderAccountNumber;
private  String senderBankName;
private int amount;
}
