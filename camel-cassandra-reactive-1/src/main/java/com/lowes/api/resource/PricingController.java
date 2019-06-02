package com.lowes.api.resource;

import javax.annotation.PostConstruct;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.lowes.api.camel.PriceProcessor;
import com.lowes.api.model.Price;
import com.lowes.api.repo.PricingRepository;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PricingController extends RouteBuilder {
	@Autowired
	PriceProcessor pocessor;
	
	@Autowired
	PricingRepository pricingRepository;
	
	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8081")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	
	

	@PostMapping("/postPrice")
	public Mono<Price> bookShow(@RequestBody Price price) {
		Mono<Price> response = pricingRepository.save(price);
		Flux<Price> log2 = webClient.post().uri("/postPrice").syncBody(price).retrieve().bodyToFlux(Price.class).log(".......post........");
		System.out.println("...>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return response.log();
		
	}
	@GetMapping("/getAlll")
	public Flux<Price> getPrice(){		
	return	pricingRepository.findAll();
	}
	@Override
	public void configure() throws Exception {
	System.out.println("...............inside configure method.........");	
	// Flux<Price> priceList = webClient.get().uri("/getAlll").retrieve().bodyToFlux(Price.class).log(".............");
	// Disposable subscribe = priceList.subscribe();
	 Price price= new Price();
	 price.setId(223541);
	 price.setName("rambo");
	 price.setPrice(44324);
	// Flux<Price> p=Flux.just(price);
	 webClient.post().uri("/postPrice").body(BodyInserters.fromObject(price)).retrieve().bodyToMono(Price.class).log(".......{{{{{{{{}}}}}}}}}}}}}}}}}}>>>>>>>>>>>>>>...").subscribe();
	//String s= webClient.post().uri("/postPrice").body(Flux.just(price), Price.class).retrieve().bodyToMono(String.class).block();
	//Mono<String> mo = webClient.post().uri("/postPrice").syncBody(p).retrieve().bodyToMono(String.class).log(".........>>>>>>>>>>>>>>>>>>>>>");
	//mo.subscribe();
	System.out.println("..............end..........");
		
	}

}
