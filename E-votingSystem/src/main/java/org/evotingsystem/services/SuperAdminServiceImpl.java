package org.evotingsystem.services;

import org.evotingsystem.data.models.Status;
import org.evotingsystem.data.models.SuperAdmin;
import org.evotingsystem.data.repository.ElectoralOfficerRepository;
import org.evotingsystem.data.repository.SuperAdminRepo;
import org.evotingsystem.dtos.request.CreateVotingOfficerRequest;
import org.evotingsystem.dtos.request.LoginRequest;
import org.evotingsystem.dtos.request.UpdateAdminPasswordRequest;

import org.evotingsystem.dtos.response.CreateElectoralOfficerResponse;
import org.evotingsystem.dtos.response.LoginResponse;
import org.evotingsystem.dtos.response.UpdateAdminPasswordResponse;
import org.evotingsystem.exception.ValidateAdminException;
import org.evotingsystem.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl implements SuperAdminService{
    @Autowired
    SuperAdminRepo superAdminRepo;
//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Autowired
    ElectoralOfficerRepository electoralOfficerRepository;
    @Override
    public void createSuperAdmin() {
        validateSuperAdmin();
        createAdmin();
    }

    @Override
    public UpdateAdminPasswordResponse updateAdminPassword(UpdateAdminPasswordRequest request) {
       validateUpdateAdminPassword(request);
//       request.setNewPassword(passwordEncoder.encode(request.getNewPassword()));
       SuperAdmin admin = superAdminRepo.findFirstBy();
       admin.setPassword(request.getNewPassword());
       superAdminRepo.save(admin);
        UpdateAdminPasswordResponse response = new UpdateAdminPasswordResponse();
        response.setMessage("Admin Password Updated");
        return response;
    }

    @Override
    public CreateElectoralOfficerResponse createOfficer(CreateVotingOfficerRequest createVotingOfficerRequest) {
//        createVotingOfficerRequest.setPassword(passwordEncoder.encode(createVotingOfficerRequest.getPassword()));
        validateOfficer();
        electoralOfficerRepository.save( Mapper.mapRequestToOfficer(createVotingOfficerRequest));
        CreateElectoralOfficerResponse response = new CreateElectoralOfficerResponse();
        response.setMessage("REGISTERED SUCCESSFULLY");
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateLogin();
        validatePassword(loginRequest.getPassword());
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

    private void validateSuperAdmin(){
        if(superAdminRepo.count()>1L){
            throw new ValidateAdminException("ADMIN ALREADY EXIST");
        }
    }
    private void createAdmin(){
        if(superAdminRepo.count()==0L){
            SuperAdmin superAdmin = new SuperAdmin("superadmin","super@gmail.com","0000");
//            passwordEncoder.encode(superAdmin.getPassword());
//            superAdmin.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
            superAdminRepo.save(superAdmin);
        }
    }

    private void validateUpdateAdminPassword(UpdateAdminPasswordRequest request){
        if(!superAdminRepo.findFirstBy().getPassword().equals(request.getOldPassword())){
            throw new ValidateAdminException("PASSWORD DOES NOT MATCH");
        }
    }

    private void validatePassword(String password){
        if(!password.equals(superAdminRepo.findFirstBy().getPassword())){
            throw new ValidateAdminException("PASSWORD DOES NOT MATCH");
        }
    }

    private void validateOfficer(){
        if(electoralOfficerRepository.count()>2L){
            throw new ValidateAdminException("Only two Officers can be created");
        }
    }


}
