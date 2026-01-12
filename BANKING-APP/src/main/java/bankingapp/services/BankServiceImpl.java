package bankingapp.services;

import bankingapp.data.models.Account;
import bankingapp.data.models.AtmCard;
import bankingapp.data.models.Bank;
import bankingapp.data.repository.AccountRepository;
import bankingapp.data.repository.BankRepository;
import bankingapp.data.repository.NibbsRepo;
import bankingapp.dtos.request.CreateAccountRequest;
import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.RequestAtm;
import bankingapp.dtos.response.CreateAccountResponse;
import bankingapp.dtos.response.InquireBvnResponse;
import bankingapp.dtos.response.RequestAtmCardResponse;
import bankingapp.exception.BankValidationException;
import bankingapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    BankRepository bankRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NibbsService nibbsService;
    @Autowired
    NibbsRepo  nibbsRepo;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        validateBvn(createAccountRequest);
       Account account= Mapper.mapRequestToAccount(createAccountRequest);
        validateBank(createAccountRequest);
        Optional<Bank> bank = bankRepository.findByBankName(createAccountRequest.getBankName());
        bank.get().setCount(bank.get().getCount()+1);
        account.setBankName(bank.get().getBankName());
        account.setAccountNumber(generateAccountNumber(bank.get()));
        bank.get().getAccounts().put(account.getAccountNumber(), account);
        bankRepository.save(bank.get());
        accountRepository.save(account);
        return Mapper.mapAccountToResponse(account);
    }

    @Override
    public InquireBvnResponse inquireUserBvn(InquireBvnRequest inquireBvnRequest) {
        return nibbsService.createBvn(inquireBvnRequest);
    }

    @Override
    public RequestAtmCardResponse requestAtmCard(RequestAtm requestAtm) {
        validateBank(requestAtm);
        validateAccount(requestAtm);
        AtmCard atmCard=Mapper.mapAtmCardToResponse(requestAtm);
        atmCard.setAccount(bankRepository.findByBankName(requestAtm.getBankName()).get().getAccounts().get(requestAtm.getAccountNumber()));
        atmCard.setAtmDigit(generateAtmDigit());
        Bank bank=bankRepository.findByBankName(requestAtm.getBankName()).get();
        bankRepository.findByBankName(requestAtm.getBankName()).get().getAccounts().get(requestAtm.getAccountNumber()).setAtmCard(atmCard);
        Account account=accountRepository.findByAccountNumber(requestAtm.getAccountNumber());
        account.setAtmCard(atmCard);
        accountRepository.save(account);
        bank.getAtmCards().put(atmCard.getAccount().getAccountNumber(), atmCard);
        bankRepository.save(bank);
        return Mapper.mapAtmCardToResponse(atmCard);
    }

    private void validateAccount(RequestAtm requestAtm) {
        if(!bankRepository.findByBankName(requestAtm.getBankName()).get().getAccounts().containsKey(requestAtm.getAccountNumber())){
            throw new BankValidationException("Account number not found");
        }
    }

    private  void validateBvn( CreateAccountRequest  createAccountRequest){
    if(!nibbsRepo.findFirstBy().getBvns().containsValue(createAccountRequest.getBvn())){
        throw new  BankValidationException("Bvn not found");
    }
    }

    private void validateBank(RequestAtm requestAtm) {
        if(bankRepository.findByBankName(requestAtm.getBankName()).isEmpty()){
            throw new BankValidationException("bank does not exist");
        }
    }
    private void validateBank(CreateAccountRequest createAccountRequest) {
        if(bankRepository.findByBankName(createAccountRequest.getBankName()).isEmpty()){
            throw new BankValidationException("bank does not exist");
        }
    }

//    private validate

    private String generateAccountNumber(Bank bank) {
        String bankCount =String.format("%09d",bank.getCount());
        String bankSerial = bank.getBankCode()+bankCount;
        char[] newSerial= bankSerial.toCharArray();
        int sum = (newSerial[0]-'0')*3+(newSerial[1]-'0')*7+(newSerial[2]-'0')*3+(newSerial[3]-'0')*3+
                (newSerial[4]-'0')*7+(newSerial[5]-'0')*3+(newSerial[6]-'0')*3
                +(newSerial[7]-'0')*7+(newSerial[8]-'0')*3+(newSerial[9]-'0')*3+(newSerial[10]-'0')*7+
                (newSerial[11]-'0')*3;
        sum %= 10;
        int newSum=10-sum;
        if (sum == 10){
           return bankSerial+0;
        }
        return  bankCount+newSum;
    }

    private String generateAtmDigit(){
        SecureRandom secureRandom =  new SecureRandom();
        return String.valueOf(secureRandom.nextLong(999999999,1999999999));
    }


}
