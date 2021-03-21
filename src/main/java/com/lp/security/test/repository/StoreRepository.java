package com.lp.security.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lp.security.test.entity.Store;


@Repository
public interface StoreRepository extends MongoRepository<Store, String> {

}
