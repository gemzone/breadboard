package io.nzo.breadboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;


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
	
	
	
//	 extends ResourceServerConfigurerAdapter
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().disable();
//		http.authorizeRequests()
//			.anyRequest().permitAll()
//			
//			.and()
//				.logout()
//					.logoutUrl("/sign-out")
//					.logoutSuccessUrl("/")
//					.invalidateHttpSession(true)
//			.and()
//				.formLogin()
//					.loginPage("/sign-in")
//					.loginProcessingUrl("/sign-in/auth")
//					.failureUrl("/sign-in?error=exception")
//					.defaultSuccessUrl("/");
//		
//			
//			// .antMatchers("/authorization-code-test").access("#oauth2.hasScope('read')");
//	}


//	@Bean
//	public TokenStore JdbcTokenStore(DataSource dataSource) {
//		return new JdbcTokenStore(dataSource);
//	}
}
