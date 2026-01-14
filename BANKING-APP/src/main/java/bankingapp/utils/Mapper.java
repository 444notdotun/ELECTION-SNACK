package bankingapp.utils;

import bankingapp.data.models.Account;
import bankingapp.data.models.AtmCard;
import bankingapp.data.models.Transaction;
import bankingapp.dtos.request.*;
import bankingapp.dtos.response.*;


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

    public static WithdrawResponse mapWithdrawToResponse(WithdrawRequest withdrawRequest) {
        Transaction transaction = new Transaction("","-","-",withdrawRequest.getAmount());
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setMessage("SUCCESS");
        withdrawResponse.setTransaction(transaction);
        return withdrawResponse;
    }

    public static  WithdrawRequest mapTransferOfSameBankRequestToWithdrawRequest(TransferRequest transferRequest){
        WithdrawRequest withdrawRequest =  new WithdrawRequest();
        withdrawRequest.setBankName(transferRequest.getSenderBankName());
        withdrawRequest.setAmount(transferRequest.getAmount());
        withdrawRequest.setAccountNumber(transferRequest.getSenderAccountNumber());
        return withdrawRequest;
    }

    public static TransferResponse mapTransferOfSameBankResponseToTransferResponse(TransferRequest transferRequest) {
        TransferResponse transferResponse = new TransferResponse();
        Transaction transaction = new Transaction("",transferRequest.getSenderAccountNumber(),transferRequest.getReceiverAccountNumber(),transferRequest.getAmount());
        transferResponse.setMessage("success");
        transferResponse.setTransaction(transaction);
        return transferResponse;
    }

    public static DepositRequest mapTransferOfSameBankResponseToDepositRequest(TransferRequest transferRequest) {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setBankName(transferRequest.getReceiverBankName());
        depositRequest.setAccountNumber(transferRequest.getReceiverAccountNumber());
        depositRequest.setAmount(transferRequest.getAmount());
        return depositRequest;
    }
}
