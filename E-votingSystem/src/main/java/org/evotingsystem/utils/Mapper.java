package org.evotingsystem.utils;

import org.evotingsystem.data.models.ElectoralOfficer;
import org.evotingsystem.data.models.Voter;
import org.evotingsystem.dtos.request.CreateVotingOfficerRequest;
import org.evotingsystem.dtos.request.SignupRequest;

public class Mapper {
    public static Voter mapRequestToVoter(SignupRequest signupRequest){
        return new Voter(signupRequest.getName(),signupRequest.getAddress(),signupRequest.getEmail(),signupRequest.getAge(),signupRequest.getSex(),signupRequest.getPassword());
    }
    public static ElectoralOfficer mapRequestToOfficer(CreateVotingOfficerRequest createVotingOfficerRequest) {
        return new ElectoralOfficer(createVotingOfficerRequest.getName(), createVotingOfficerRequest.getAddress(), createVotingOfficerRequest.getEmail(), createVotingOfficerRequest.getAge(), createVotingOfficerRequest.getSex(), createVotingOfficerRequest.getPassword());
    }

}
