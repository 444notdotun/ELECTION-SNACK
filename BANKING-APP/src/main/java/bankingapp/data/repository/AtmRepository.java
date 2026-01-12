package bankingapp.data.repository;

import bankingapp.data.models.AtmCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends MongoRepository<AtmCard, String> {
}
