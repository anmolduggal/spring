package com.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.consume.detail.details;

@Component
public class RestService implements CommandLineRunner{
public static void callRestService() {
	RestTemplate restTemplate=new RestTemplate();
	details detail=restTemplate.getForObject("http://localhost:8080/company/note/2", details.class);
	System.out.println("Employee name is "+detail.getName());
}
@Override
public void run(String... args)throws Exception{
callRestService();	
}
}
