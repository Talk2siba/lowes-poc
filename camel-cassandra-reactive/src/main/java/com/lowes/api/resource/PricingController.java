package com.lowes.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowes.api.model.Price;
import com.lowes.api.repo.PricingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PricingController {
	
	
	@Autowired
	PricingRepository pricingRepository;
	
	
	

	@PostMapping("/postPrice")
	public Mono<Price> bookShow(@RequestBody Price price) {
		Mono<Price> response = pricingRepository.save(price);
		return response.log();
	}
	@GetMapping("/getAlll")
	public Flux<Price> getPrice(){		
	return	pricingRepository.findAll();
	}

}
