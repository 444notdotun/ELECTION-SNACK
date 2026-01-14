package bankingapp.services;

import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.InterBankRequest;
import bankingapp.dtos.response.InquireBvnResponse;
import bankingapp.dtos.response.InterbankResponse;

public interface NibbsService{
    InquireBvnResponse createBvn(InquireBvnRequest inquireBvnRequest);
    InterbankResponse interbank(InterBankRequest interBankRequest);
}
