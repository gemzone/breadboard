package io.nzo.breadboard;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil 
{
	private static SessionFactory sessionFactory;
	
	static 
	{
	    try 
	    {
	    	
	    	StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
	    	standardServiceRegistryBuilder.loadProperties("hibernate.properties");
	    	standardServiceRegistryBuilder.configure( "hibernate.cfg.xml" );
	    	StandardServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();
	    	
	        Metadata metadata = new MetadataSources(standardRegistry)
//	        		.addResource("io/nzo/breadboard/model/User.hbm.xml")
	        		.getMetadataBuilder()
	        		.build();

	        sessionFactory = metadata.getSessionFactoryBuilder().build();
	    }
	    catch (Throwable th)
		{
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static void shutdown() 
	{
		getSessionFactory().close();
	}
	
}