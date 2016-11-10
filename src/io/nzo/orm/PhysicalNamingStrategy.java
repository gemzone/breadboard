package io.nzo.orm;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl  
{
	private static final long serialVersionUID = 1L;
	
	@Override  
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) 
	{
		
//		System.out.println(context);
//		System.out.println(context.getExtractedDatabaseMetaData());
//		
//		System.out.println( context.getCurrentCatalog() );
//		System.out.println( context.getCurrentSchema() );
//		
//		System.out.println(   addUnderscores("Board")    );
//		System.out.println(   addUnderscores("BoardPost")    );
//		System.out.println(   addUnderscores("BoardPostComment")    );
//		System.out.println(   addUnderscores("Post1")    );
//		System.out.println(   addUnderscores("Post123123")    );
		
		System.out.println(  "getCurrentSchemaCommand: " + context.  );
		
		return Identifier.toIdentifier("gz_" + addUnderscores(name.getText()).toLowerCase(), name.isQuoted());  
    }

	protected static String addUnderscores(String name)
	{
		final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++)
		{
			if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1)))
			{
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase(Locale.ROOT);
	}
}