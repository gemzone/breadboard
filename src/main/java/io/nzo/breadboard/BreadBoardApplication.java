package io.nzo.breadboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BreadBoardApplication 
{
	//@Value("${spring.mvc.view.prefix}")
    //private static String prefix;
	
	public static void main(String[] args) 
	{
		System.out.println("SpringApplication.run(BreadBoardApplication.class, args);");
		SpringApplication.run(BreadBoardApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
		return builder.build();
	}
}
