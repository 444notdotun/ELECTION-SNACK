package bankingapp.dtos.response;

import lombok.Data;

@Data
public class RequestAtmCardResponse {
    private String atmDigit;
    private String message;
}
