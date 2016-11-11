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