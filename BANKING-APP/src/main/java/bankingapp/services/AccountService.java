package bankingapp.services;

import bankingapp.dtos.request.DepositRequest;
import bankingapp.dtos.request.TransferRequest;
import bankingapp.dtos.request.WithdrawRequest;
import bankingapp.dtos.response.DepositResponse;
import bankingapp.dtos.response.TransferResponse;
import bankingapp.dtos.response.WithdrawResponse;

public interface AccountService {
    TransferResponse transfer (TransferRequest transferRequest);
    DepositResponse deposit(DepositRequest depositRequest);
    WithdrawResponse withdraw(WithdrawRequest withdrawRequest);
}
