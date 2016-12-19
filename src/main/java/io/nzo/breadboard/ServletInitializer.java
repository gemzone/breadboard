package io.nzo.breadboard;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		System.out.println("application.sources(BreadBoardApplication.class);");
		return application.sources(BreadBoardApplication.class);
	}

}
