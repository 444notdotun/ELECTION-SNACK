package bankingapp;

import bankingapp.data.models.Bank;
import bankingapp.data.models.BankDetails;
import bankingapp.data.models.Nibbs;
import bankingapp.data.repository.AccountRepository;
import bankingapp.data.repository.BankRepository;
import bankingapp.data.repository.NibbsRepo;
import bankingapp.dtos.request.CreateAccountRequest;
import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.RequestAtm;
import bankingapp.dtos.response.CreateAccountResponse;
import bankingapp.services.BankService;
import bankingapp.services.NibbsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.util.AssertionErrors.assertEquals;
@ActiveProfiles("test")
@SpringBootTest
public class BankTest {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    BankService bankService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    NibbsRepo  nibbsRepo;

Nibbs  nibbs;
    CreateAccountRequest createAccountRequest;
    InquireBvnRequest  inquireBvnRequest;
    InquireBvnRequest inquireBvnRequest2;
    RequestAtm  requestAtm;

    @BeforeEach
    public void  setUp() {
        nibbsRepo.deleteAll();
        bankRepository.deleteAll();
        accountRepository.deleteAll();

        createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setEmail("customer1@gmail.com");
        createAccountRequest.setPassword("password");
        createAccountRequest.setName("John Doe");
        createAccountRequest.setBvn("00001");
        createAccountRequest.setBankName("FCMB");
        inquireBvnRequest = new InquireBvnRequest();
        inquireBvnRequest.setPhoneNumber("123456789");
        inquireBvnRequest2 =  new InquireBvnRequest();
        inquireBvnRequest2.setPhoneNumber("123456789");
        nibbs = Nibbs.getInstance();
        for(BankDetails details: BankDetails.values()) {
            Bank bank = new Bank(details.getBankcode(),details.name());
            bankRepository.save(bank);
        }
        nibbsRepo.save(nibbs);
        requestAtm = new RequestAtm();
        requestAtm.setPassword("0000");
        requestAtm.setBankName(createAccountRequest.getBankName());
        requestAtm.setAccountNumber("0000000012");


    }


    @Test
    public void accountCanBeCreatedByBank(){
        assertEquals("bvnGenerate","Success",bankService.inquireUserBvn(inquireBvnRequest).getMessage());
       assertEquals("message","WELCOME TO "+ createAccountRequest.getBankName()+ " "+ createAccountRequest.getName() , bankService.createAccount(createAccountRequest).getMessage());
    }

    @Test
    public void bankCaninquireBvnForUserAndReturningUserCAnRetrieveBvn(){
        assertEquals("bvnGenerate","Success",bankService.inquireUserBvn(inquireBvnRequest).getMessage());

        assertEquals("bvnGenerate","BVN RETRIEVED SUCCESSFULLY",bankService.inquireUserBvn(inquireBvnRequest2).getMessage());

    }


    @Test
    public void BankCanIssueAtmCard(){
        assertEquals("bvnGenerate","Success",bankService.inquireUserBvn(inquireBvnRequest).getMessage());
        assertEquals("message","WELCOME TO "+ createAccountRequest.getBankName()+ " "+ createAccountRequest.getName() , bankService.createAccount(createAccountRequest).getMessage());
        assertEquals("atmcardrequest","success",bankService.requestAtmCard(requestAtm).getMessage());


    }

}