package org.evotingsystem.services;

import org.evotingsystem.data.models.Status;
import org.evotingsystem.data.models.SuperAdmin;
import org.evotingsystem.data.repository.SuperAdminRepo;
import org.evotingsystem.data.repository.VoterRepository;
import org.evotingsystem.dtos.request.LoginRequest;
import org.evotingsystem.dtos.request.SignupRequest;
import org.evotingsystem.dtos.response.LoginResponse;
import org.evotingsystem.dtos.response.SignUpResponse;
import org.evotingsystem.exception.ValidateAdminException;
import org.evotingsystem.exception.ValidateAgeException;
import org.evotingsystem.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ElectoralOfficerServiceImpl implements ElectoralOfficerService{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    SuperAdminRepo superAdminRepo;

    @Override
    public SignUpResponse registerVoter(SignupRequest request) {
        verifyAge(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        voterRepository.save( Mapper.mapRequestToVoter(request));
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("REGISTERED SUCCESSFULLY");
        return signUpResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateLogin();
//        validatePassword(loginRequest.getPassword());
        SuperAdmin superAdmin= superAdminRepo.findFirstBy();
        superAdmin.setStatus(Status.ACTIVE);
        superAdminRepo.save(superAdmin);
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setMessage("LOGGED SUCCESSFULLY");
        return loginResponse;
    }

    private void validateLogin(){
        if(superAdminRepo.findFirstBy().getStatus().equals(Status.ACTIVE)){
            throw new ValidateAdminException("already Logged in");
        }
    }

    private void verifyAge(SignupRequest signupRequest) {
        if (signupRequest.getAge() < 18) throw new ValidateAgeException("YOU ARE LESS THAN 18");
    }
}
