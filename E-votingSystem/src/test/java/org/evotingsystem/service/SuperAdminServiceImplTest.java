package org.evotingsystem.service;

import org.evotingsystem.data.repository.SuperAdminRepo;
import org.evotingsystem.dtos.request.UpdateAdminPasswordRequest;
import org.evotingsystem.exception.ValidateAdminException;
import org.evotingsystem.services.SuperAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class SuperAdminServiceImplTest {
    @Autowired
    SuperAdminService superAdminService;
    @Autowired
    SuperAdminRepo  superAdminRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    UpdateAdminPasswordRequest updateAdminPasswordRequest;




    @BeforeEach
    public  void setup() {
        superAdminRepo.deleteAll();
        updateAdminPasswordRequest= new UpdateAdminPasswordRequest();
        updateAdminPasswordRequest.setNewPassword("19652");
        updateAdminPasswordRequest.setOldPassword("0000");

    }

    @Test
    public void SuperadminCanBeCreated() {
        superAdminService.createSuperAdmin();
        assertEquals("superadminCount",1L,superAdminRepo.count());
    }

    @Test
    public void SuperadminCanBeUpdated() {
        superAdminService.createSuperAdmin();
        assertEquals("superadminCount",1L,superAdminRepo.count());
        superAdminService.updateAdminPassword(updateAdminPasswordRequest);
        assertEquals("updatepassword",superAdminRepo.findFirstBy().getPassword(),updateAdminPasswordRequest.getNewPassword());
    }
//    @Test
//    public void SuperadminShouldBeLoggedInToPerformOperations() {
//        superAdminService.createSuperAdmin();
//        assertEquals("superadminCount",1L,superAdminRepo.count());
//        assertThrows(ValidateAdminException.class,()->superAdminService.updateAdminPassword(updateAdminPasswordRequest));
//    }

}