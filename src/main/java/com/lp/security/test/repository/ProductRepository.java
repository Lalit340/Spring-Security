package com.lp.security.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lp.security.test.entity.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
