import org.example.evotingapp.data.model.ElectoralOfficer;
import org.example.evotingapp.data.repository.VoterRepository;
import org.example.evotingapp.dtos.request.RegisterUserRequest;
import org.example.evotingapp.service.ElectoralOfficerService;
import org.example.evotingapp.service.ElectoralOfficerServiceImplementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

class ElectoralOfficerTest {

    ElectoralOfficer electoralOfficer;
    ElectoralOfficerService electoralOfficerService;
    RegisterUserRequest registerUserRequest;

    VoterRepository voterRepository;

    @BeforeEach
    void setup(){
        registerUserRequest = new RegisterUserRequest();
        electoralOfficer = ElectoralOfficer.createOfficer();
        electoralOfficerService = new ElectoralOfficerServiceImplementation(voterRepository);
    }

    @Test
    void userCanBeRegistered(){
        electoralOfficer.SetFields("adewole","officer1","1234","sabo");
        registerUserRequest.setAddress("321, sabo yaba");
        registerUserRequest.setFirstName("adedotun");
        registerUserRequest.setUsername("notdotun");
        registerUserRequest.setPassword("notme");
        registerUserRequest.setAge(18);
        electoralOfficerService.registerUser(registerUserRequest);
        assertEquals(1,voterRepository.count());

    }

}