package bankingapp.services;

import bankingapp.dtos.request.*;
import bankingapp.dtos.response.*;

public interface BankService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    InquireBvnResponse inquireUserBvn(InquireBvnRequest inquireBvnRequest);
    RequestAtmCardResponse requestAtmCard(RequestAtm requestAtm);
    DepositResponse deposit(DepositRequest depositRequest);
    WithdrawResponse withdraw(WithdrawRequest withdrawRequest);
    TransferResponse transfer (TransferRequest transferRequest);
}
