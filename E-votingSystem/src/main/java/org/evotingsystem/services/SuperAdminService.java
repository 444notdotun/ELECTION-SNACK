package org.evotingsystem.services;

import org.evotingsystem.dtos.request.CreateVotingOfficerRequest;
import org.evotingsystem.dtos.request.LoginRequest;
import org.evotingsystem.dtos.request.UpdateAdminPasswordRequest;
import org.evotingsystem.dtos.response.CreateElectoralOfficerResponse;
import org.evotingsystem.dtos.response.LoginResponse;
import org.evotingsystem.dtos.response.UpdateAdminPasswordResponse;
import org.springframework.stereotype.Service;

@Service
public interface SuperAdminService {
    void createSuperAdmin();
    UpdateAdminPasswordResponse updateAdminPassword(UpdateAdminPasswordRequest request) ;
    CreateElectoralOfficerResponse createOfficer(CreateVotingOfficerRequest createVotingOfficerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
