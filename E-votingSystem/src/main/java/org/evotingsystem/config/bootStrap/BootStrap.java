package org.evotingsystem.config.bootStrap;
import org.evotingsystem.services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BootStrap implements CommandLineRunner {
    @Autowired
    SuperAdminService superAdminService;

    @Override
    public void run(String... args) {
        superAdminService.createSuperAdmin();
    }
}
