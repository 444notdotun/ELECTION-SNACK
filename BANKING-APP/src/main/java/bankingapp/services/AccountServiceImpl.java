package bankingapp.services;

import bankingapp.data.models.Account;
import bankingapp.data.models.Bank;
import bankingapp.data.repository.AccountRepository;
import bankingapp.data.repository.BankRepository;
import bankingapp.dtos.request.DepositRequest;
import bankingapp.dtos.request.TransferRequest;
import bankingapp.dtos.request.WithdrawRequest;
import bankingapp.dtos.response.DepositResponse;
import bankingapp.dtos.response.TransferResponse;
import bankingapp.dtos.response.WithdrawResponse;
import bankingapp.exception.AccountValidationException;
import bankingapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BankRepository bankRepository;

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
       isSameBank(transferRequest);
    }

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        validateAccount(depositRequest);
        Account account=accountRepository.findByPhoneNumber(depositRequest.getPhoneNumber());
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(depositRequest.getAmount())));
        DepositResponse depositResponse=Mapper.mapDepositToResponse(depositRequest);
        depositResponse.getTransaction().setReceiverAccount(account.getAccountNumber());
        depositResponse.getTransaction().setSenderAccount(account.getAccountNumber());
        account.getTransactions().add(depositResponse.getTransaction());
        accountRepository.save(account);
        Optional<Bank> bank=bankRepository.findByBankName(account.getBankName());
        bank.get().getAccounts().replace(account.getAccountNumber(),account);
        bankRepository.save(bank.get());
        return depositResponse;
    }


    @Override
    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
       Bank bank= bankRepository.findByBankName(withdrawRequest.getBankName()).get();

    }

    private void validateAccount(DepositRequest depositRequest) {
        if(accountRepository.findByPhoneNumber(depositRequest.getPhoneNumber()) != null){
            throw  new AccountValidationException("INVALID ACCOUNT");
        }
    }

    private boolean isSameBank(TransferRequest transferRequest) {
        return transferRequest.getBankName().equals(accountRepository.findByAccountNumber(transferRequest.getAccountNumber()).getBankName());
    }
}
