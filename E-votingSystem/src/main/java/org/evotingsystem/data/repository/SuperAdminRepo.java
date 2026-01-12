package org.evotingsystem.data.repository;

import org.evotingsystem.data.models.SuperAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepo extends MongoRepository<SuperAdmin,String> {
    SuperAdmin findFirstBy();
}
