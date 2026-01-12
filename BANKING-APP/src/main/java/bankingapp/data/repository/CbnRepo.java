package bankingapp.data.repository;

import bankingapp.data.models.Cbn;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CbnRepo extends MongoRepository<Cbn, String> {
}
