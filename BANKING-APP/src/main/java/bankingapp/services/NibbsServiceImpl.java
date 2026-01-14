package bankingapp.services;

import bankingapp.data.models.Nibbs;
import bankingapp.data.repository.NibbsRepo;
import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.InterBankRequest;
import bankingapp.dtos.response.InquireBvnResponse;
import bankingapp.dtos.response.InterbankResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
public class NibbsServiceImpl implements NibbsService {


    @Autowired
    NibbsRepo nibbsRepo;

    @Transactional
    @Override
    public InquireBvnResponse createBvn(InquireBvnRequest inquireBvnRequest) {
        if(hasnumber(inquireBvnRequest)){
            return returnBvn(inquireBvnRequest);
        }
        return generateNewBvn(inquireBvnRequest);
    }

    @Override
    public InterbankResponse interbank(InterBankRequest interBankRequest) {
        return null;
    }

    @Transactional
    private String generateBvn() {
        Nibbs nibbs = nibbsRepo.findFirstBy();
        nibbs.setCount(nibbs.getCount() + 1);
        nibbsRepo.save(nibbs);
        return String.format("%05d", nibbs.getCount());
    }


    private boolean hasnumber(InquireBvnRequest inquireBvnRequest) {
        return nibbsRepo.findFirstBy().getBvns().containsKey(inquireBvnRequest.getPhoneNumber());
    }
    private InquireBvnResponse generateNewBvn( InquireBvnRequest inquireBvnRequest) {
        String bvn = generateBvn();
        Nibbs nibbs = nibbsRepo.findFirstBy();
        nibbs.getBvns().put(inquireBvnRequest.getPhoneNumber(), bvn);
        nibbsRepo.save(nibbs);
        InquireBvnResponse response = new InquireBvnResponse();
        response.setBvn(nibbs.getBvns().get(inquireBvnRequest.getPhoneNumber()));
        response.setMessage("Success");
        return response;
    }
    private InquireBvnResponse returnBvn(InquireBvnRequest inquireBvnRequest) {
        InquireBvnResponse response = new InquireBvnResponse();
        response.setBvn(nibbsRepo.findFirstBy().getBvns().get(inquireBvnRequest.getPhoneNumber()));
        response.setMessage("BVN RETRIEVED SUCCESSFULLY");
        return response;
    }
}
