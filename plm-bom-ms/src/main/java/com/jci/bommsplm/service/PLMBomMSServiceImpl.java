package com.jci.bommsplm.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PLMBomMSServiceImpl implements PLMBomMSService {

	@Autowired
	RestTemplate resttemplate;
	// IF Url And ampping is invalide then error() will called

	@Override
	@HystrixCommand(fallbackMethod = "error")
	public String hystrixCircuitBreaker() {
		URI uri = URI.create("http://localhost:8090/recommended");
																	

		return this.resttemplate.getForObject(uri, String.class);
	}

	public String error() {
		System.out.println("fall back is call");
		return "fall back is call ";

	}

}
