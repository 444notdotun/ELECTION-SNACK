package bankingapp.services;

import bankingapp.dtos.request.DepositRequest;
import bankingapp.dtos.request.SignUpRequest;
import bankingapp.dtos.request.TransferRequest;
import bankingapp.dtos.request.WithdrawRequest;
import bankingapp.dtos.response.DepositResponse;
import bankingapp.dtos.response.TransferResponse;
import bankingapp.dtos.response.UserSignUpResponse;
import bankingapp.dtos.response.WithdrawResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserService userService;



    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
       return userService.transfer(transferRequest);
    }

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        return  userService.deposit(depositRequest);
    }

    @Override
    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        return userService.withdraw(withdrawRequest);
    }

    @Override
    public UserSignUpResponse signUp(SignUpRequest signUpRequest) {
        return null;
    }
}
