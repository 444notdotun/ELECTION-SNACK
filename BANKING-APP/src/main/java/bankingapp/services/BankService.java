package bankingapp.services;

import bankingapp.dtos.request.CreateAccountRequest;
import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.RequestAtm;
import bankingapp.dtos.response.CreateAccountResponse;
import bankingapp.dtos.response.InquireBvnResponse;
import bankingapp.dtos.response.RequestAtmCardResponse;

public interface BankService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    InquireBvnResponse inquireUserBvn(InquireBvnRequest inquireBvnRequest);
    RequestAtmCardResponse requestAtmCard(RequestAtm requestAtm);
}
