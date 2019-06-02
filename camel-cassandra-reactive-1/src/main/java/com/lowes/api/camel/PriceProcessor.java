package com.lowes.api.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
@Component
public class PriceProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("...................inside camel processor.............");
		
	}

}
