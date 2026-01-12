package bankingapp.dtos.response;

import lombok.Data;

@Data
public class InquireBvnResponse {
    private String bvn;
    private String message;
}
