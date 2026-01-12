package bankingapp.data.repository;

import bankingapp.data.models.Nibbs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NibbsRepo extends MongoRepository<Nibbs, String> {
    Nibbs findFirstBy();
}
