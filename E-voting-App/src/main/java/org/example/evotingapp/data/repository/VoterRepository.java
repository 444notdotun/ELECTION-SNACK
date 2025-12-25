package org.example.evotingapp.data.repository;

import org.example.evotingapp.data.model.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoterRepository extends MongoRepository<Voter,String> {

}
