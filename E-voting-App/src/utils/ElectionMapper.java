package utils;

import org.example.evotingapp.data.model.Voter;
import org.example.evotingapp.dtos.request.RegisterUserRequest;
import org.example.evotingapp.dtos.response.RegisterUserResponse;





public class ElectionMapper {
    public static Voter MapRequestToRegisterVoter(RegisterUserRequest request){
        return new Voter(request.getFirstName(), request.getPassword(), request.getAddress(), request.getUsername(), request.getAge());
    }
    public static RegisterUserResponse MapVoterToResponse(Voter voter){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setVotersId(voter.getVotersId());
        response.setMessage("REGISTERED SUCCESSFULLY");
        response.setRegistrationId(voter.getRegistrationNUmber());
        return response;
    }

}

