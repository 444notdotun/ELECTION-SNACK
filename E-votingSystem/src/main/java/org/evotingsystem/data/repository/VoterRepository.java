package org.evotingsystem.data.repository;

import org.evotingsystem.data.models.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends MongoRepository<Voter, String> {
}
