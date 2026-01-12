package org.evotingsystem.data.repository;

import org.evotingsystem.data.models.ElectoralOfficer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectoralOfficerRepository extends MongoRepository<ElectoralOfficer,String> {
}
