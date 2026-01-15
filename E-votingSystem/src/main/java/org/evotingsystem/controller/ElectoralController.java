package org.evotingsystem.controller;

import org.evotingsystem.dtos.request.SignupRequest;
import org.evotingsystem.dtos.response.SignUpResponse;
import org.evotingsystem.services.ElectoralOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("electoralController")
public class ElectoralController {
    @Autowired
    ElectoralOfficerService electoralOfficerService;
    @PostMapping("/registerVoter")
    public ResponseEntity<?> registerVoter(SignupRequest request){
        SignUpResponse signUpResponse = electoralOfficerService.registerVoter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }


}
