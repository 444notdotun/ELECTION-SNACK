package bankingapp.services;

import bankingapp.data.models.Account;
import bankingapp.data.models.Bank;
import bankingapp.data.repository.BankRepository;
import bankingapp.data.repository.NibbsRepo;
import bankingapp.dtos.request.DepositRequest;

import bankingapp.dtos.request.TransferRequest;
import bankingapp.dtos.request.WithdrawRequest;
import bankingapp.dtos.response.DepositResponse;
import bankingapp.dtos.response.TransferResponse;
import bankingapp.dtos.response.WithdrawResponse;
import bankingapp.exception.AccountValidationException;
import bankingapp.exception.BankValidationException;
import bankingapp.utils.Hasher;
import bankingapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    BankRepository bankRepository;
    @Autowired
    NibbsRepo  nibbsRepo;

    @Override
    public TransferResponse transfer(TransferRequest transferRequest){
        WithdrawRequest withdrawRequest = Mapper.mapTransferOfSameBankRequestToWithdrawRequest(transferRequest);
        DepositRequest depositRequest = Mapper.mapTransferOfSameBankResponseToDepositRequest(transferRequest);
        Bank senderBank = bankRepository.findByBankName(withdrawRequest.getBankName()).get();
        Account senderAccount = senderBank.getAccounts().get(withdrawRequest.getAccountNumber());
        validatePassword(senderAccount,transferRequest.getSenderPassword());
        validateWithdrawal(senderAccount,withdrawRequest.getAmount());
        updateSenderAccount(senderAccount, withdrawRequest.getAmount());
        Bank recieverBank = bankRepository.findByBankName(depositRequest.getBankName()).get();
        Account recieverAccount =recieverBank.getAccounts().get(depositRequest.getAccountNumber());
        updateReceiversAccount(recieverAccount, depositRequest.getAmount());
        TransferResponse response= Mapper.mapTransferOfSameBankResponseToTransferResponse(transferRequest);
        recieverAccount.getTransactions().add(response.getTransaction());
        senderAccount.getTransactions().add(response.getTransaction());
        recieverBank.getAccounts().replace(recieverAccount.getAccountNumber(),recieverAccount);
        senderBank.getAccounts().replace(senderAccount.getAccountNumber(),senderAccount);
        bankRepository.save(senderBank);
        bankRepository.save(recieverBank);
        return response;
    }

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        validateBank(depositRequest);
        validateAccount(depositRequest);
        Account account=bankRepository.findByBankName(depositRequest.getBankName()).get().getAccounts().get(depositRequest.getAccountNumber());
        updateReceiversAccount(account,depositRequest.getAmount());
        DepositResponse depositResponse=Mapper.mapDepositToResponse(depositRequest);
        depositResponse.getTransaction().setReceiverAccount(account.getAccountNumber());
        depositResponse.getTransaction().setId(generateTransactionId());
        account.getTransactions().add(depositResponse.getTransaction());
        Optional<Bank> bank=bankRepository.findByBankName(account.getBankName());
        bank.get().getAccounts().replace(account.getAccountNumber(),account);
        bankRepository.save(bank.get());
        return depositResponse;
    }


    @Override
    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        validateBank(withdrawRequest);
        validateAccount(withdrawRequest);
       Account account=bankRepository.findByBankName(withdrawRequest.getBankName()).get().getAccounts().get(withdrawRequest.getAccountNumber());
       validatePassword(account,withdrawRequest.getPassword());
       updateSenderAccount(account,withdrawRequest.getAmount());
       validateWithdrawal(account,withdrawRequest.getAmount());
       Optional<Bank> bank = bankRepository.findByBankName(account.getBankName());
       bank.get().getAccounts().replace(account.getAccountNumber(),account);
       WithdrawResponse withdrawResponse = new WithdrawResponse();
       bankRepository.save(bank.get());
       return  withdrawResponse;
    }

    private void validateWithdrawal(Account account, int amount){
        if(BigDecimal.valueOf(amount).compareTo(account.getBalance())>0){
            throw new BankValidationException("ACCOUNT IS LESS THAN  AMOUNT");
        }
    }
    private void updateReceiversAccount (Account account,int amount){
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
    }

    private void updateSenderAccount(Account account,int amount){
        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
    }
    private void validateBank(DepositRequest depositRequest) {
        if(bankRepository.findByBankName(depositRequest.getBankName()).isEmpty()){
            throw new BankValidationException("INVALID BANK");
        }
    }

    private void validateAccount(DepositRequest depositRequest) {
        if(bankRepository.findByBankName(depositRequest.getBankName()).get().getAccounts().get(depositRequest.getAccountNumber()) == null){
            throw  new AccountValidationException("INVALID ACCOUNT");
        }
    }
    private void validateBank(WithdrawRequest withdrawRequest) {
        if(bankRepository.findByBankName(withdrawRequest.getBankName()).isEmpty()){
            throw new BankValidationException("INVALID BANK");
        }
    }
    private void validateAccount(WithdrawRequest withdrawRequest) {
        if(bankRepository.findByBankName(withdrawRequest.getBankName()).get().getAccounts().get(withdrawRequest.getAccountNumber()) == null){
            throw  new AccountValidationException("INVALID ACCOUNT");
        }
    }
    private void validatePassword(Account account,String password){
        if(!Hasher.checkPassword(password,account.getPassword())){
            throw  new AccountValidationException("INVALID PASSWORD");
        }
    }

    private String generateTransactionId(){
        return UUID.randomUUID().toString();
    }
}
