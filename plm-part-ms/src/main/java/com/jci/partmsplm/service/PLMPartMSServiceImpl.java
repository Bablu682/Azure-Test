package com.jci.partmsplm.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PLMPartMSServiceImpl implements PLMPartMSService {
	@Autowired
	RestTemplate resttempalte;
	
	@LoadBalanced
	@Bean
public RestTemplate resttemplat(){
		return new RestTemplate();
	}

	// This URL And Mapping is Invalide then it call error()
	@Override
	@HystrixCommand(fallbackMethod = "error")
	public String PartCircuitBreaker() {
		URI uri = URI.create("http://localhost:8090/recommended");

		return this.resttempalte.getForObject(uri, String.class);
	}

	public String error() {
		System.out.println("fall back is call");
		return "Fallback is call ";

	}

}
