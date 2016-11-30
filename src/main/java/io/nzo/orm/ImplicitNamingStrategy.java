package io.nzo.orm;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitCollectionTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;

public class ImplicitNamingStrategy extends ImplicitNamingStrategyLegacyJpaImpl
{
	private static final long serialVersionUID = 1L;

	@Override
	public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source)
	{
		
		System.out.println( source.getOwningEntityNaming() );
		System.out.println( source.getOwningPhysicalTableName() );
		
		return super.determineCollectionTableName(source);
	}
	
}