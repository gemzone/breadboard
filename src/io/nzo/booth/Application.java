package io.nzo.booth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application 
{
	//@Value("${spring.mvc.view.prefix}")
    //private static String prefix;
	
	public static void main(String[] args) 
	{
		System.out.println("SpringApplication.run(BoothApplication.class, args);");	//  + prefix);
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
		return builder.build();
	}

//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception 
//	{
//		return args -> { 
//			Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//			log.info(quote.toString());
//		};
//	}
	
}
