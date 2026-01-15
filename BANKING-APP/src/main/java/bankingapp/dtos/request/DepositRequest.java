package bankingapp.dtos.request;


import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DepositRequest {
    private String accountNumber;
    @Min(value =1,message ="value can not be less than zero and should be greater than zero")
    private int amount;
    private String bankName;
    private String description;
}
