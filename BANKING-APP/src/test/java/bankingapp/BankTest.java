package bankingapp;

import bankingapp.data.models.Bank;
import bankingapp.data.models.BankDetails;
import bankingapp.data.models.Nibbs;
import bankingapp.data.repository.BankRepository;
import bankingapp.data.repository.NibbsRepo;
import bankingapp.dtos.request.CreateAccountRequest;
import bankingapp.dtos.request.InquireBvnRequest;
import bankingapp.dtos.request.RequestAtm;
import bankingapp.services.BankService;
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
    NibbsRepo  nibbsRepo;

Nibbs  nibbs;
    CreateAccountRequest createAccountRequest;
    CreateAccountRequest createAccountRequest2;
    CreateAccountRequest createAccountRequest3;

    InquireBvnRequest  inquireBvnRequest;
    InquireBvnRequest inquireBvnRequest2;
    InquireBvnRequest inquireBvnRequest3;
    InquireBvnRequest inquireBvnRequest4;
    RequestAtm  requestAtm;

    @BeforeEach
    public void  setUp() {
        nibbsRepo.deleteAll();
        bankRepository.deleteAll();

        createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setEmail("customer1@gmail.com");
        createAccountRequest.setPassword("password");
        createAccountRequest.setName("John Doe");
        createAccountRequest.setBvn("00001");
        createAccountRequest.setBankName("FCMB");
        createAccountRequest.setPhoneNumber("123456789");
        createAccountRequest2 = new CreateAccountRequest();
        createAccountRequest2.setEmail("customer3@gmail.com");
        createAccountRequest2.setPassword("password");
        createAccountRequest2.setName("not dotun");
        createAccountRequest2.setBvn("00002");
        createAccountRequest2.setBankName("FCMB");
        createAccountRequest2.setPhoneNumber("123456799");
        createAccountRequest3 = new CreateAccountRequest();
        createAccountRequest3.setEmail("customer5@gmail.com");
        createAccountRequest3.setPassword("password");
        createAccountRequest3.setName("olamide");
        createAccountRequest3.setBvn("00003");
        createAccountRequest3.setBankName("FIRSTBANK");
        createAccountRequest3.setPhoneNumber("123456889");
        inquireBvnRequest = new InquireBvnRequest();
        inquireBvnRequest.setPhoneNumber("123456789");
        inquireBvnRequest2 =  new InquireBvnRequest();
        inquireBvnRequest2.setPhoneNumber("123456789");
        inquireBvnRequest3 =  new InquireBvnRequest();
        inquireBvnRequest3.setPhoneNumber("123456799");
        inquireBvnRequest4 =  new InquireBvnRequest();
        inquireBvnRequest4.setPhoneNumber("123456889");
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
        assertEquals("bvnGenerate","Success",bankService.inquireUserBvn(inquireBvnRequest3).getMessage());
        assertEquals("bvnGenerate","Success",bankService.inquireUserBvn(inquireBvnRequest4).getMessage());
        assertEquals("message","WELCOME TO "+ createAccountRequest.getBankName()+ " "+ createAccountRequest.getName() , bankService.createAccount(createAccountRequest).getMessage());
        assertEquals("message","WELCOME TO "+ createAccountRequest3.getBankName()+ " "+ createAccountRequest3.getName() , bankService.createAccount(createAccountRequest3).getMessage());
        assertEquals("message","WELCOME TO "+ createAccountRequest2.getBankName()+ " "+ createAccountRequest2.getName() , bankService.createAccount(createAccountRequest2).getMessage());
        assertEquals("atmcardrequest","success",bankService.requestAtmCard(requestAtm).getMessage());
    }



}