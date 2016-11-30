package io.nzo.booth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BoothApplication 
{
	//@Value("${spring.mvc.view.prefix}")
    //private static String prefix;
	
	public static void main(String[] args) 
	{
		System.out.println("SpringApplication.run(BoothApplication.class, args);");
		SpringApplication.run(BoothApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
		return builder.build();
	}
}
