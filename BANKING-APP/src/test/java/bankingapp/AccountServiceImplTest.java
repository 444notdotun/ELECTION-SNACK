package bankingapp;

import bankingapp.data.models.Account;
import bankingapp.data.models.Bank;
import bankingapp.data.repository.BankRepository;
import bankingapp.dtos.request.DepositRequest;
import bankingapp.dtos.request.TransferRequest;
import bankingapp.dtos.request.WithdrawRequest;
import bankingapp.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;
@ActiveProfiles("test")
@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    AccountService  accountService;
    @Autowired
    BankRepository  bankRepository;
    WithdrawRequest withdrawRequest;
    DepositRequest depositRequest;
    TransferRequest transferRequest;
    Account  account;
    Bank bank;
    @BeforeEach
    public void setup() {
        bank= bankRepository.findByBankName("FCMB").get();
       account =bank .getAccounts().get("0000000012");
       account.setBalance(BigDecimal.valueOf(0));
       bankRepository.save(bank);

        withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(1000);
        withdrawRequest.setBankName("FCMB");
        withdrawRequest.setAccountNumber("0000000012");
        withdrawRequest.setPassword("password");
        depositRequest = new DepositRequest();
        depositRequest.setAmount(2000);
        depositRequest.setAccountNumber("0000000012");
        depositRequest.setDescription("deposit");
        depositRequest.setBankName("FCMB");
        transferRequest = new TransferRequest();
        transferRequest.setAmount(500);
        transferRequest.setReceiverAccountNumber("0000000017");
        transferRequest.setSenderAccountNumber(depositRequest.getAccountNumber());
        transferRequest.setSenderPassword("password");
        transferRequest.setSenderBankName("FCMB");
        transferRequest.setReceiverBankName("FIRSTBANK");



    }
    @Test
    public void accountCanDeposit(){
    assertEquals("accountcandeposit","success",accountService.deposit(depositRequest).getMessage());
    assertEquals("balanceChanges",BigDecimal.valueOf(2000),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());
    }

    @Test
    public void accountCanWithdraw(){
        assertEquals("accountcandeposit","success",accountService.deposit(depositRequest).getMessage());
        assertEquals("balanceChanges",BigDecimal.valueOf(2000),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());
        accountService.withdraw(withdrawRequest);
        assertEquals("balanceChanges",BigDecimal.valueOf(1000),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());
    }

    @Test
    public void accountCanTransfer(){
        assertEquals("accountcandeposit","success",accountService.deposit(depositRequest).getMessage());
        assertEquals("balanceChanges",BigDecimal.valueOf(2000),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());
        accountService.withdraw(withdrawRequest);
        assertEquals("balanceChanges",BigDecimal.valueOf(1000),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());
        assertEquals("transfer","success",accountService.transfer(transferRequest).getMessage());
        assertEquals("account remainS 500",BigDecimal.valueOf(500),bankRepository.findByBankName("FCMB").get().getAccounts().get("0000000012").getBalance());

    }


}