package io.nzo.breadboard;

import java.io.IOException;
import java.util.Map;

import org.apache.catalina.servlets.DefaultServlet;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeException;
import de.neuland.jade4j.template.FileTemplateLoader;
import de.neuland.jade4j.template.JadeTemplate;
import de.neuland.jade4j.template.TemplateLoader;
import io.nzo.servlet.ServletContextManager;

public class JadeConfig extends DefaultServlet 
{
    private static final long serialVersionUID = 1L;
    
    public static JadeConfiguration config = new JadeConfiguration();
    
    
    public JadeConfig()  // tomcat이 실행될때 생성자를 load-on-startup가 1일경우 호출하게된다.
    {
        TemplateLoader loader = new FileTemplateLoader(ServletContextManager.servletContext.getRealPath("WEB-INF/views/"), "UTF-8");
        config.setTemplateLoader(loader);
        config.setMode(Jade4J.Mode.XHTML);  // <input checked="true" />
        config.setPrettyPrint(false);
        config.setCaching(false);
    }
    
    public static JadeTemplate getTemplate(String name)
    {
    	try
    	{
    		return config.getTemplate(name);
    	}
    	catch(JadeException e) 
    	{
    		e.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static String renderTemplate(JadeTemplate template, Map<String, Object> model)
    {
    	return config.renderTemplate(template, model);
    }
}