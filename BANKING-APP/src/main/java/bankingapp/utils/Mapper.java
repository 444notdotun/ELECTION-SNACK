package bankingapp.utils;

import bankingapp.data.models.Account;
import bankingapp.data.models.AtmCard;
import bankingapp.data.models.Transaction;
import bankingapp.data.repository.BankRepository;
import bankingapp.dtos.request.CreateAccountRequest;
import bankingapp.dtos.request.DepositRequest;
import bankingapp.dtos.request.RequestAtm;
import bankingapp.dtos.response.CreateAccountResponse;
import bankingapp.dtos.response.DepositResponse;
import bankingapp.dtos.response.RequestAtmCardResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {

    public static Account mapRequestToAccount(CreateAccountRequest createAccountRequest) {
        createAccountRequest.setPassword(Hasher.hashPassword(createAccountRequest.getPassword()));
        return new Account(createAccountRequest.getName(),createAccountRequest.getEmail(),createAccountRequest.getPassword(),createAccountRequest.getBvn(),createAccountRequest.getPhoneNumber());
    }

    public static CreateAccountResponse mapAccountToResponse(Account account) {
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setAccountNumber(account.getAccountNumber());
        createAccountResponse.setMessage("WELCOME TO "+account.getBankName()+" "+account.getName());
        return createAccountResponse;
    }

    public static AtmCard mapAtmCardToResponse(RequestAtm requestAtm) {
        requestAtm.setPassword(Hasher.hashPassword(requestAtm.getPassword()));
        return new AtmCard(requestAtm.getPassword(),requestAtm.getBankName());
    }
    public static RequestAtmCardResponse  mapAtmCardToResponse(AtmCard atmCard) {
        RequestAtmCardResponse requestAtmCardResponse = new RequestAtmCardResponse();
        requestAtmCardResponse.setAtmDigit(atmCard.getAtmDigit());
        requestAtmCardResponse.setMessage("success");
        return requestAtmCardResponse;
    }

    public static DepositResponse mapDepositToResponse(DepositRequest depositRequest) {
        Transaction transaction = new Transaction(depositRequest.getDescription(),"-","-",depositRequest.getAmount());
        DepositResponse  depositResponse = new DepositResponse();
        depositResponse.setTransaction(transaction);
        depositResponse.setMessage("success");
        return  depositResponse;
    }
}
