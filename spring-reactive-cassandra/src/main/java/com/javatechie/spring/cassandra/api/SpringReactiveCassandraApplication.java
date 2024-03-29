package com.javatechie.spring.cassandra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.cassandra.api.model.User;
import com.javatechie.spring.cassandra.api.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SpringReactiveCassandraApplication {
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("/getUsers")
	public Flux<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/getUsersByAge/{age}")
	public Flux<User> getUserByAge(@PathVariable int age) {
		return userRepository.findByAgeLessThan(age);
	}

	@GetMapping("/getUser/{address}")
	public Mono<User> getUser(@PathVariable String address) {
		return userRepository.findByAddress(address);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveCassandraApplication.class, args);
	}
}
