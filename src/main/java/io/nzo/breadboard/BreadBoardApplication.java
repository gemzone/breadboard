package io.nzo.breadboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

//@EnableResourceServer
//@EnableAuthorizationServer
@SpringBootApplication
public class BreadBoardApplication 
{
	public static void main(String[] args) 
	{
		//System.out.println("SpringApplication.run(BreadBoardApplication.class, args);");
		SpringApplication.run(BreadBoardApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
		return builder.build();
	}
}
