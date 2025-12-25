package org.example.evotingapp.service;

import lombok.AllArgsConstructor;
import org.example.evotingapp.data.repository.VoterRepository;
import org.example.evotingapp.dtos.request.RegisterUserRequest;
import org.example.evotingapp.dtos.response.RegisterUserResponse;

import utils.ElectionMapper;


@AllArgsConstructor
public class ElectoralOfficerServiceImplementation implements ElectoralOfficerService{
   private VoterRepository voterRepository;
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        return ElectionMapper.MapVoterToResponse(voterRepository.save(ElectionMapper.MapRequestToRegisterVoter(registerUserRequest)));
    }
}
