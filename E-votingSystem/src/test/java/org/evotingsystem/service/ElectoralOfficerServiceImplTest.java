package org.evotingsystem.service;

import org.evotingsystem.data.repository.ElectoralOfficerRepository;
import org.evotingsystem.data.repository.VoterRepository;
import org.evotingsystem.dtos.request.SignupRequest;
import org.evotingsystem.services.ElectoralOfficerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ElectoralOfficerServiceImplTest {
    @Autowired
    ElectoralOfficerService electoralOfficerService;
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    ElectoralOfficerRepository electoralOfficerRepository;

    SignupRequest request;

    @BeforeEach
    public void setUp(){
        voterRepository.deleteAll();
        request = new SignupRequest();
        request.setAddress("road 7");
        request.setSex("male");
        request.setAge(20);
        request.setEmail("newObject@Gmail.com");
        request.setPassword("000");
        request.setName("newObject");
    }

    @Test
    public void ElectoralOfficerCanSignUp(){
        assertEquals("SignUpVoter","REGISTERED SUCCESSFULLY",electoralOfficerService.registerVoter(request).getMessage());
        assertEquals("repocount",1L,voterRepository.count());
        
    }
//    @Test
//    public void ElectoralOfficerCanRegisterVoter() {
//        assertEquals("SignUpVoter","REGISTERED SUCCESSFULLY",userService.signup(request).getMessage());
//        assertEquals("repocount",1L,voterRepository.count());
//    }
//    @Test
//    public void UserCanSignInAsElectoralOfficer(){
//
//    }

}
