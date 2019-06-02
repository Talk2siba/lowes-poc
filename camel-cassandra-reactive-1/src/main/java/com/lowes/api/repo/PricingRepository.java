package com.lowes.api.repo;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.lowes.api.model.Price;

public interface PricingRepository extends ReactiveCassandraRepository<Price, Integer>{

}
