package com.lowes.api.repo;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.lowes.api.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCassandraRepository<User, Integer> {

	@AllowFiltering
	Flux<User> findByAgeLessThan(int age);

	@AllowFiltering
	Mono<User> findByAddress(String address);

}
