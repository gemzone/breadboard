package io.nzo.booth;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		System.out.println("application.sources(BoothApplication.class);");
		return application.sources(BoothApplication.class);
	}

}
