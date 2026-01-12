package bankingapp.services;

import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.response.InquireBvnResponse;

public interface NibbsService{
    InquireBvnResponse createBvn(InquireBvnRequest inquireBvnRequest);
}
