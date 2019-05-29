package de.mobile.repository;

import de.mobile.domain.ad.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdRepository extends MongoRepository<Ad, String> {

}
