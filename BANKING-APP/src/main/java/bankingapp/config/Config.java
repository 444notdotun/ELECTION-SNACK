package bankingapp.config;

import bankingapp.data.models.Bank;
import bankingapp.data.models.BankDetails;
import bankingapp.data.models.Cbn;
import bankingapp.data.models.Nibbs;
import bankingapp.data.repository.BankRepository;
import bankingapp.data.repository.CbnRepo;
import bankingapp.data.repository.NibbsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class Config implements CommandLineRunner {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private NibbsRepo nibbsRepo;

    @Autowired
    private CbnRepo cbnRepo;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("======================CONFIG STARTED");
        Cbn cbn = Cbn.getInstance();
        cbnRepo.save(cbn);
        Nibbs nibbs = Nibbs.getInstance();
        nibbsRepo.save(nibbs);
        for(BankDetails details: BankDetails.values()) {
            Bank bank = new Bank(details.getBankcode(),details.name());
            bankRepository.save(bank);
        }
    }
}
