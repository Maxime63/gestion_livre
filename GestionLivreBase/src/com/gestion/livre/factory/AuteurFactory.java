package com.gestion.livre.factory;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class AuteurFactory {
	public static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private AuteurFactory() {}
	
	public static PersistenceManagerFactory get(){
		return pmf;
	}
}
