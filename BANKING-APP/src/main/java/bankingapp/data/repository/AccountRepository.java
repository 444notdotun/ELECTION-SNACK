package bankingapp.data.repository;

import bankingapp.data.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByPhoneNumber(String phoneNumber);
    Account findByAccountNumber(String accountNumber);

}
