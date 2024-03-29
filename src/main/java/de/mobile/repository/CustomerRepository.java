package de.mobile.repository;

import de.mobile.domain.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findById(String id);
    Customer findByEmail(String email);
    boolean existsByEmail(String email);
}
