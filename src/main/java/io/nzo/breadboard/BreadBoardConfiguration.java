package io.nzo.breadboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BreadBoardConfiguration
{
	@Value("${breadboard.url}")
	public String ROOT_URL;
	
	@Bean(name = "urlService")
    public UrlService urlService() {
        return () -> ROOT_URL;
    }
	public interface UrlService {
	    String getApplicationUrl();
	}
	
}
